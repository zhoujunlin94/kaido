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
 * @date 2023/11/13 22:00
 * @desc
 */
@Data
@ApiModel(description = "系统角色DTO")
public class SysRoleDTO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @NotBlank(message = "角色code为空")
    @ApiModelProperty(value = "角色code")
    private String roleCode;

    @NotBlank(message = "角色名字不能为空")
    @ApiModelProperty(value = "角色名字")
    private String roleName;

    @ApiModelProperty(value = "角色状态")
    private Boolean roleStatus;

    @ApiModelProperty(value = "角色资源")
    private List<Integer> roleResources = Lists.newArrayList();

    public void initCreate() {
        this.id = null;
        this.roleStatus = null;
    }

    public void initAndCheck4Update() {
        Assert.notNull(this.id, "主键不可为空");
        this.roleStatus = null;
    }

}
