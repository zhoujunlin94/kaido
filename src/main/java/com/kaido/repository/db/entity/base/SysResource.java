package com.kaido.repository.db.entity.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统资源表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_resource")
public class SysResource implements Serializable {
    /**
     * 主键  权限id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 权限code
     */
    @Column(name = "resource_code")
    private String resourceCode;

    /**
     * 权限名
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 资源类型 菜单、按钮
     */
    @Column(name = "resource_type")
    private String resourceType;

    /**
     * 父级资源id
     */
    @Column(name = "resource_parent")
    private Integer resourceParent;

    /**
     * 是否有效（0-无效；1-有效）
     */
    @Column(name = "resource_status")
    private Boolean resourceStatus;

    /**
     * 资源图标
     */
    @Column(name = "resource_icon")
    private String resourceIcon;

    /**
     * 资源路径
     */
    @Column(name = "resource_path")
    private String resourcePath;

    /**
     * 资源排序
     */
    @Column(name = "resource_order")
    private Integer resourceOrder;

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