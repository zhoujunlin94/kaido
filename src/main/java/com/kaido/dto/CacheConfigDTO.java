package com.kaido.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:39
 * @desc
 */
@Data
public class CacheConfigDTO {

    private Integer id;

    @NotBlank(message = "key不可为空")
    private String key;

    @NotBlank(message = "value不可为空")
    private String value;

    private String desc;

}
