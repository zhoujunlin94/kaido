package io.github.zhoujunlin94.kaido.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "id-name数据")
public class IdNameDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "name")
    private String name;

}
