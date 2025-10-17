package io.github.zhoujunlin94.kaido.dto.sa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author zhoujunlin
 * @date 2023/11/9 21:44
 * @desc
 */
@Data
@Schema(description = "登录参数")
public class LoginParamDTO {

    @NotBlank(message = "账户名不可为空")
    @Schema(description = "账户名")
    private String accountName;

    @NotBlank(message = "用户密码不可为空")
    @Schema(description = "用户密码")
    private String userPassword;

}
