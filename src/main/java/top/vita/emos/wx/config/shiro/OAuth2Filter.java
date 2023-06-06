package top.vita.emos.wx.config.shiro;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
// 防止ThreadLocal中的数据发生错乱
@Scope("prototype")
public class OAuth2Filter extends AuthenticatingFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ThreadLocalToken threadLocalToke;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${emos.jwt.cache-expire}")
    private int cacheEmpire;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = getRequestToken(req);
        if (StringUtil.isBlank(token)) {
            return null;
        }
        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        // 跨域
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));

        threadLocalToke.clear();

        String token = getRequestToken(req);
        if (StringUtil.isBlank(token)) {
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌");
            return false;
        }
        try {
            jwtUtils.verifierToken(token);
        } catch (TokenExpiredException e) {
            if (redisTemplate.hasKey(token)) {
                redisTemplate.delete(token);
                int userId = jwtUtils.getUserId(token);
                token = jwtUtils.createToken(userId);
                redisTemplate.opsForValue().set(token, userId + "", cacheEmpire, TimeUnit.DAYS);
                threadLocalToke.setToken(token);
            }else{
                resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
                resp.getWriter().print("令牌已过期");
                return false;
            }
        } catch (JWTDecodeException e){
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌");
            return false;
        }
        boolean flag = executeLogin(servletRequest, servletResponse);
        return flag;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
        try{
            resp.getWriter().print(e.getMessage());
        }catch (Exception exception){

        }

        return false;
    }

    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtil.isBlank(token)) {
            token = request.getParameter("token");
        }
        return token;
    }
}
