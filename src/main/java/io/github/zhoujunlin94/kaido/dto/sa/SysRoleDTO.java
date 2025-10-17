package io.github.zhoujunlin94.kaido.dto.sa;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2023/11/13 22:00
 * @desc
 */
@Data
@Schema(description = "系统角色DTO")
public class SysRoleDTO {

    @Schema(description = "主键")
    private Integer id;

    @NotBlank(message = "角色code为空")
    @Schema(description = "角色code")
    private String roleCode;

    @NotBlank(message = "角色名字不能为空")
    @Schema(description = "角色名字")
    private String roleName;

    @Schema(description = "角色状态")
    private Boolean roleStatus;

    @Schema(description = "角色资源")
    private List<Integer> roleResources = Lists.newArrayList();

    public void initCreate() {
        this.id = null;
        this.roleStatus = null;
    }

    public void initAndCheck4Update() {
        Assert.notNull(this.id, "主键不可为空");
        this.roleStatus = null;
    }

}
