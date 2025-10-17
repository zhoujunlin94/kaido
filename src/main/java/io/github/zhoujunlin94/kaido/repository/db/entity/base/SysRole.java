package io.github.zhoujunlin94.kaido.repository.db.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_role")
public class SysRole implements Serializable {
    /**
     * 主键  角色id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 角色code
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 角色名
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 是否有效（0-无效；1-有效）
     */
    @Column(name = "role_status")
    private Boolean roleStatus;

    /**
     * 是否删除 1已删除 0正常数据
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 创建用户
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}