package com.kaido.dto.sa;

import com.kaido.constant.ResourceType;
import com.you.meet.nice.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2023/11/13 21:48
 * @desc
 */
@Data
@ApiModel(description = "系统资源分页参数")
public class SysResourcePageParamDTO extends PageParam {

    @ApiModelProperty(value = "资源code")
    private String resourceCode;

    @ApiModelProperty(value = "资源名")
    private String resourceName;

    @ApiModelProperty(value = "资源类型 菜单、按钮、路由")
    private ResourceType resourceType;

    @ApiModelProperty(value = "父级资源id")
    private Integer resourceParent;

    @ApiModelProperty(value = "资源状态")
    private Boolean resourceStatus;

}
