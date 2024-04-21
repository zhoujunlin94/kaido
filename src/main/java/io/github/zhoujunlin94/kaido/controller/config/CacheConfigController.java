package io.github.zhoujunlin94.kaido.controller.config;

import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.dto.config.CacheConfigDTO;
import io.github.zhoujunlin94.kaido.dto.config.CacheConfigPageQueryDTO;
import io.github.zhoujunlin94.kaido.service.config.CacheConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
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
@Api(tags = {"B-缓存配置"})
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
    public boolean add(@Valid @RequestBody CacheConfigDTO cacheConfigDTO) {
        return cacheConfigService.add(cacheConfigDTO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public boolean delete(@RequestParam @NotNull(message = "主键不可为空") Integer id) {
        return cacheConfigService.delete(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新")
    public boolean update(@Valid @RequestBody CacheConfigDTO cacheConfigDTO) {
        Assert.notNull(cacheConfigDTO.getId(), "更新时主键不可为空");
        return cacheConfigService.update(cacheConfigDTO);
    }

}
