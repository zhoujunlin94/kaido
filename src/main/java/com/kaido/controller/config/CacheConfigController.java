package com.kaido.controller.config;

import com.github.pagehelper.PageInfo;
import com.kaido.dto.config.CacheConfigDTO;
import com.kaido.dto.config.CacheConfigPageQueryDTO;
import com.kaido.service.config.CacheConfigService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:51
 * @desc
 */
@Validated
@RestController
@RequestMapping("/api/cache-cfg")
public class CacheConfigController {

    @Resource
    private CacheConfigService cacheConfigService;

    @PostMapping("/page")
    public PageInfo<CacheConfigDTO> page(@RequestBody CacheConfigPageQueryDTO queryDTO) {
        return cacheConfigService.page(queryDTO);
    }

    @PostMapping("/add")
    public int add(@Valid @RequestBody CacheConfigDTO cacheConfigDTO) {
        return cacheConfigService.add(cacheConfigDTO);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestParam @NotNull(message = "主键") Integer id) {
        return cacheConfigService.delete(id);
    }

    @PostMapping("/update")
    public boolean update(@Valid @RequestBody CacheConfigDTO cacheConfigDTO) {
        return cacheConfigService.update(cacheConfigDTO);
    }

}
