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
@Schema(description = "系统用户分页参数")
public class SysUserPageParamDTO extends PageParam {

    @Schema(description = "账户名")
    private String accountName;

    @Schema(description = "用户真实姓名")
    private String userName;

    @Schema(description = "是否有效")
    private Boolean userStatus;

}
