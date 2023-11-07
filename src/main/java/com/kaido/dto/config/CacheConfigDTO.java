package com.kaido.dto.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:39
 * @desc
 */
@Data
@ApiModel(description = "缓存配置dto")
public class CacheConfigDTO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "缓存key")
    @NotBlank(message = "key不可为空")
    private String key;

    @ApiModelProperty(value = "缓存value")
    @NotBlank(message = "value不可为空")
    private String value;

    @ApiModelProperty(value = "缓存描述")
    private String desc;

}
