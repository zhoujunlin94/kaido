package com.kaido.dto.sa;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author zhoujunlin
 * @date 2023/11/13 21:29
 * @desc
 */
@Data
@ApiModel(description = "系统用户DTO")
public class SysUserDTO {

    @ApiModelProperty(value = "主键  用户id  更新时必传")
    private Integer id;

    @NotBlank(message = "账户名不能为空")
    @ApiModelProperty(value = "账户名")
    private String accountName;

    @NotBlank(message = "用户真实姓名不能为空")
    @ApiModelProperty(value = "用户真实姓名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String userPassword;

    @ApiModelProperty(value = "是否有效")
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
