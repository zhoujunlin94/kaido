package com.kaido.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zhoujunlin
 * @date 2023/11/9
 * @desc
 */
@Getter
@RequiredArgsConstructor
public enum ResourceType {
    /**
     * 资源枚举
     */
    ROUTER("路由"),
    MENU("菜单"),
    ;


    private final String desc;

}
