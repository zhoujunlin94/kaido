package com.kaido.dto.sa;

import com.kaido.internal.web.dto.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2023/11/13 21:48
 * @desc
 */
@Data
@ApiModel(description = "系统用户分页参数")
public class SysUserPageParamDTO extends PageParam {

    @ApiModelProperty(value = "账户名")
    private String accountName;

    @ApiModelProperty(value = "用户真实姓名")
    private String userName;

    @ApiModelProperty(value = "是否有效")
    private Boolean userStatus;

}
