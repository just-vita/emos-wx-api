package top.vita.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class TestForm {
    @NotBlank
    @ApiModelProperty("姓名")
    private String name;
}
