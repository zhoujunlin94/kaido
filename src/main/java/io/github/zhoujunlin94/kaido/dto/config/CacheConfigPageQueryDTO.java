package io.github.zhoujunlin94.kaido.dto.config;

import io.github.zhoujunlin94.meet.common.pojo.PageParam;
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
