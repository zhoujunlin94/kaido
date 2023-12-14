package com.kaido.dto.sa;

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
@ApiModel(description = "系统角色分页参数")
public class SysRolePageParamDTO extends PageParam {

    @ApiModelProperty(value = "角色code")
    private String roleCode;

    @ApiModelProperty(value = "角色名字")
    private String roleName;

    @ApiModelProperty(value = "角色状态")
    private Boolean roleStatus;

}
