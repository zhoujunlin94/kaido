package com.kaido.dto.config;

import com.kaido.internal.web.dto.PageParam;
import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:29
 * @desc
 */
@Data
public class CacheConfigPageQueryDTO extends PageParam {

    private String key;

    private String value;

    private String desc;

}
