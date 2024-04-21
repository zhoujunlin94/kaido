package io.github.zhoujunlin94.kaido.repository.db.entity.base;

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
 * 系统业务配置缓存表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_cache_config")
public class CacheConfig implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 配置key
     */
    @Column(name = "`key`")
    private String key;

    /**
     * 配置值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 配置描述
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 逻辑删除标志位
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 创建人
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