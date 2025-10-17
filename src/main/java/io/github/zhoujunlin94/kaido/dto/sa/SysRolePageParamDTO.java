package io.github.zhoujunlin94.kaido.dto.sa;

import io.github.zhoujunlin94.meet.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2023/11/13 21:48
 * @desc
 */
@Data
@Schema(description = "系统角色分页参数")
public class SysRolePageParamDTO extends PageParam {

    @Schema(description = "角色code")
    private String roleCode;

    @Schema(description = "角色名字")
    private String roleName;

    @Schema(description = "角色状态")
    private Boolean roleStatus;

}
