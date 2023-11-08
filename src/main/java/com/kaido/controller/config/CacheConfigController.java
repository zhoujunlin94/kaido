package com.kaido.controller.config;

import com.github.pagehelper.PageInfo;
import com.kaido.dto.config.CacheConfigDTO;
import com.kaido.dto.config.CacheConfigPageQueryDTO;
import com.kaido.service.config.CacheConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:51
 * @desc
 */
@Validated
@Api(tags = {"B-缓存配置控制器"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cache-cfg")
public class CacheConfigController {

    private final CacheConfigService cacheConfigService;

    @PostMapping("/page")
    @ApiOperation(value = "分页")
    public PageInfo<CacheConfigDTO> page(@RequestBody CacheConfigPageQueryDTO queryDTO) {
        return cacheConfigService.page(queryDTO);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public int add(@Valid @RequestBody CacheConfigDTO cacheConfigDTO) {
        return cacheConfigService.add(cacheConfigDTO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public boolean delete(@RequestParam @NotNull(message = "主键") Integer id) {
        return cacheConfigService.delete(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新")
    public boolean update(@Valid @RequestBody CacheConfigDTO cacheConfigDTO) {
        return cacheConfigService.update(cacheConfigDTO);
    }

}
