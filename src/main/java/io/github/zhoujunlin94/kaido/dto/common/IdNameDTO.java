package io.github.zhoujunlin94.kaido.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoujunlin
 * @date 2023/12/16 21:18
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "id-name数据")
public class IdNameDTO {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "name")
    private String name;

}
