package com.kaido.vo.sa;

import com.kaido.constant.ResourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2023年08月03日 21:24
 * @desc
 */
@Data
@ApiModel(value = "资源信息")
public class ResourceVO {

    @ApiModelProperty(value = "权限id")
    private Integer id;

    @ApiModelProperty(value = "权限名")
    private String resourceName;

    @ApiModelProperty(value = "权限描述")
    private String resourceDesc;

    @ApiModelProperty(value = "资源类型 菜单、按钮")
    private ResourceType resourceType;

    @ApiModelProperty(value = "父级资源id")
    private Integer resourceParent;

    @ApiModelProperty(value = "是否有效（0-无效；1-有效）")
    private Boolean resourceStatus;

    @ApiModelProperty(value = "资源图标")
    private String resourceIcon;

    @ApiModelProperty(value = "资源路径")
    private String resourcePath;

    @ApiModelProperty(value = "资源排序")
    private Integer resourceOrder;

    @ApiModelProperty(value = "子资源")
    private List<ResourceVO> children;

}
