package com.kaido.dto.sa;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhoujunlin
 * @date 2023/11/9 21:44
 * @desc
 */
@Data
@ApiModel(description = "登录参数")
public class LoginParamDTO {

    @NotBlank(message = "账户名不可为空")
    @ApiModelProperty(value = "账户名")
    private String accountName;

    @NotBlank(message = "用户密码不可为空")
    @ApiModelProperty(value = "用户密码")
    private String userPassword;

}
