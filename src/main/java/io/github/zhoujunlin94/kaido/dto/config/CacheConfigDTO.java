package io.github.zhoujunlin94.kaido.dto.config;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:39
 * @desc
 */
@Data
@Schema(description = "缓存配置dto")
public class CacheConfigDTO {

    @Schema(description = "主键")
    private Integer id;

    @Schema(description = "缓存key")
    @NotBlank(message = "key不可为空")
    private String key;

    @Schema(description = "缓存value")
    @NotBlank(message = "value不可为空")
    private String value;

    @Schema(description = "缓存描述")
    private String desc;

}
