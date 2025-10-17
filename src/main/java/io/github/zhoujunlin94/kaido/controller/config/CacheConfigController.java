package io.github.zhoujunlin94.kaido.controller.config;

import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.dto.config.CacheConfigDTO;
import io.github.zhoujunlin94.kaido.dto.config.CacheConfigPageQueryDTO;
import io.github.zhoujunlin94.kaido.service.config.CacheConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:51
 * @desc
 */
@Validated
@Tag(name = "B-缓存配置")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cache-cfg")
public class CacheConfigController {

    private final CacheConfigService cacheConfigService;

    @PostMapping("/page")
    @Operation(summary = "分页")
    public PageInfo<CacheConfigDTO> page(@RequestBody CacheConfigPageQueryDTO queryDTO) {
        return cacheConfigService.page(queryDTO);
    }

    @PostMapping("/add")
    @Operation(summary = "新增")
    public boolean add(@Valid @RequestBody CacheConfigDTO cacheConfigDTO) {
        return cacheConfigService.add(cacheConfigDTO);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除")
    public boolean delete(@RequestParam @NotNull(message = "主键不可为空") Integer id) {
        return cacheConfigService.delete(id);
    }

    @PostMapping("/update")
    @Operation(summary = "更新")
    public boolean update(@Valid @RequestBody CacheConfigDTO cacheConfigDTO) {
        Assert.notNull(cacheConfigDTO.getId(), "更新时主键不可为空");
        return cacheConfigService.update(cacheConfigDTO);
    }

}
