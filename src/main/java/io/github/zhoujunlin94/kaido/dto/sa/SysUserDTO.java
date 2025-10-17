package io.github.zhoujunlin94.kaido.dto.sa;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2023/11/13 21:29
 * @desc
 */
@Data
@Schema(description = "系统用户DTO")
public class SysUserDTO {

    @Schema(description = "主键  用户id  更新时必传")
    private Integer id;

    @NotBlank(message = "账户名不能为空")
    @Schema(description = "账户名")
    private String accountName;

    @NotBlank(message = "用户真实姓名不能为空")
    @Schema(description = "用户真实姓名")
    private String userName;

    @Schema(description = "密码")
    private String userPassword;

    @Schema(description = "是否有效")
    private Boolean userStatus;

    private List<Integer> userRoles = Lists.newArrayList();

    public void initAndCheck4Create() {
        this.id = null;
        this.userStatus = false;
        Assert.hasText(this.userPassword, "用户密码不能为空");
    }

    public void initAndCheck4Update() {
        this.userStatus = null;
        Assert.notNull(this.id, "更新时主键不可为空");
    }

}
