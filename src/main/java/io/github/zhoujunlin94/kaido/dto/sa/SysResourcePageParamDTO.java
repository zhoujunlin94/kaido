package io.github.zhoujunlin94.kaido.dto.sa;

import io.github.zhoujunlin94.kaido.constant.ResourceType;
import io.github.zhoujunlin94.meet.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2023/11/13 21:48
 * @desc
 */
@Data
@Schema(description = "系统资源分页参数")
public class SysResourcePageParamDTO extends PageParam {

    @Schema(description = "资源code")
    private String resourceCode;

    @Schema(description = "资源名")
    private String resourceName;

    @Schema(description = "资源类型 菜单、按钮、路由")
    private ResourceType resourceType;

    @Schema(description = "父级资源id")
    private Integer resourceParent;

    @Schema(description = "资源状态")
    private Boolean resourceStatus;

}
