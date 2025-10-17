package io.github.zhoujunlin94.kaido.dto.config;

import io.github.zhoujunlin94.meet.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:29
 * @desc
 */
@Data
@Schema(description = "缓存配置分页参数")
public class CacheConfigPageQueryDTO extends PageParam {

    @Schema(description = "key")
    private String key;

    @Schema(description = "value")
    private String value;

    @Schema(description = "描述")
    private String desc;

}
