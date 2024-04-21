package io.github.zhoujunlin94.kaido.dto.sa;

import cn.hutool.core.util.StrUtil;
import io.github.zhoujunlin94.kaido.constant.ResourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhoujunlin
 * @date 2023/11/14 21:43
 * @desc
 */
@Data
@ApiModel(description = "系统资源dto")
public class SysResourceDTO {

    @ApiModelProperty(value = "主键 更新时不可为空")
    private Integer id;

    @NotBlank(message = "资源code不能为空")
    @ApiModelProperty(value = "资源code")
    private String resourceCode;

    @NotBlank(message = "资源名不能为空")
    @ApiModelProperty(value = "资源名")
    private String resourceName;

    @NotNull(message = "资源类型不能为空")
    @ApiModelProperty(value = "资源类型 菜单、按钮、路由")
    private ResourceType resourceType;

    @ApiModelProperty(value = "父级资源id")
    private Integer resourceParent = 0;

    @ApiModelProperty(value = "资源状态")
    private Boolean resourceStatus = Boolean.FALSE;

    @ApiModelProperty(value = "资源图标")
    private String resourceIcon = StrUtil.EMPTY;

    @ApiModelProperty(value = "资源路径")
    private String resourcePath = StrUtil.EMPTY;

    @ApiModelProperty(value = "资源排序")
    private Integer resourceOrder = 0;

    public void initCreate() {
        this.id = null;
    }

    public void initAndCheck4Update() {
        Assert.notNull(this.id, "主键不可为空");
    }

}
