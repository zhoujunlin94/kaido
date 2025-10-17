package io.github.zhoujunlin94.kaido.dto.sa;

import cn.hutool.core.util.StrUtil;
import io.github.zhoujunlin94.kaido.constant.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.util.Assert;


/**
 * @author zhoujunlin
 * @date 2023/11/14 21:43
 * @desc
 */
@Data
@Schema(description = "系统资源dto")
public class SysResourceDTO {

    @Schema(description = "主键 更新时不可为空")
    private Integer id;

    @NotBlank(message = "资源code不能为空")
    @Schema(description = "资源code")
    private String resourceCode;

    @NotBlank(message = "资源名不能为空")
    @Schema(description = "资源名")
    private String resourceName;

    @NotNull(message = "资源类型不能为空")
    @Schema(description = "资源类型 菜单、按钮、路由")
    private ResourceType resourceType;

    @Schema(description = "父级资源id")
    private Integer resourceParent = 0;

    @Schema(description = "资源状态")
    private Boolean resourceStatus = Boolean.FALSE;

    @Schema(description = "资源图标")
    private String resourceIcon = StrUtil.EMPTY;

    @Schema(description = "资源路径")
    private String resourcePath = StrUtil.EMPTY;

    @Schema(description = "资源排序")
    private Integer resourceOrder = 0;

    public void initCreate() {
        this.id = null;
    }

    public void initAndCheck4Update() {
        Assert.notNull(this.id, "主键不可为空");
    }

}
