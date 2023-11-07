package com.kaido.dto.config;

import com.kaido.internal.web.dto.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:29
 * @desc
 */
@Data
@ApiModel(description = "缓存配置分页参数")
public class CacheConfigPageQueryDTO extends PageParam {

    @ApiModelProperty(value = "key")
    private String key;

    @ApiModelProperty(value = "value")
    private String value;

    @ApiModelProperty(value = "描述")
    private String desc;

}
