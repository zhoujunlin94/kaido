package io.github.zhoujunlin94.kaido.vo.sa;

import io.github.zhoujunlin94.kaido.constant.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2023年08月03日 21:24
 * @desc
 */
@Data
@Schema(description = "资源信息")
public class ResourceVO {

    @Schema(description = "权限id")
    private Integer id;

    @Schema(description = "权限名")
    private String resourceName;

    @Schema(description = "权限描述")
    private String resourceDesc;

    @Schema(description = "资源类型 菜单、按钮")
    private ResourceType resourceType;

    @Schema(description = "父级资源id")
    private Integer resourceParent;

    @Schema(description = "是否有效（0-无效；1-有效）")
    private Boolean resourceStatus;

    @Schema(description = "资源图标")
    private String resourceIcon;

    @Schema(description = "资源路径")
    private String resourcePath;

    @Schema(description = "资源排序")
    private Integer resourceOrder;

    @Schema(description = "子资源")
    private List<ResourceVO> children;

}
