package net.cocotea.admin.api.system.model.po;

import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CoCoTea
 * Table: sche_job,Remark:计划任务表
 */
@Entity(tableName = "sche_job")
public class ScheduleTask implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id(strategy = "generator", generator = "org.sagacity.sqltoy.plugins.id.impl.UUIDGenerator")
    @Column(name = "ID", length = 32L, type = java.sql.Types.VARCHAR, nullable = false)
    private String id;

    /**
     * 任务名称
     */
    @Column(name = "NAME", length = 90L, type = java.sql.Types.VARCHAR, nullable = false)
    private String name;

    /**
     * 配置类型;0类模式 1函数模式
     */
    @Column(name = "TYPE", type = java.sql.Types.INTEGER, nullable = false)
    private Integer type;

    /**
     * 任务对应类名
     */
    @Column(name = "CLASS_NAME", length = 500L, type = java.sql.Types.VARCHAR)
    private String className;

    /**
     * 方法名
     */
    @Column(name = "METHOD_NAME", length = 200L, type = java.sql.Types.VARCHAR)
    private String methodName;

    /**
     * 参数JSON对象
     */
    @Column(name = "PARAMETERS", length = 900L, type = java.sql.Types.VARCHAR)
    private String parameters;

    /**
     * Cron表达式
     */
    @Column(name = "CORN_EXPRESSION", length = 64L, type = java.sql.Types.VARCHAR, nullable = false)
    private String cornExpression;

    /**
     * 任务描述
     */
    @Column(name = "DESCRIPTION", length = 900L, type = java.sql.Types.VARCHAR)
    private String description;

    /**
     * 是否允许并发执行;0禁止 1允许
     */
    @Column(name = "CONCURRENT_EXEC", type = java.sql.Types.INTEGER, nullable = false)
    private Integer concurrentExec;

    /**
     * 是否启用;0未启用 1启用
     */
    @Column(name = "ACTIVE", type = java.sql.Types.INTEGER, nullable = false)
    private Integer active;

    /**
     * 下一次执行时间
     */
    @Column(name = "NEXT_EXE_TIME", type = java.sql.Types.DATE)
    private LocalDateTime nextExeTime;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", type = java.sql.Types.DATE, nullable = false)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Column(name = "CREATE_BY", length = 32L, type = java.sql.Types.VARCHAR, nullable = false)
    private String createBy;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME", type = java.sql.Types.DATE)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Column(name = "UPDATE_BY", length = 32L, type = java.sql.Types.VARCHAR)
    private String updateBy;

    /**
     * 删除状态;0删除 1未删除
     */
    @Column(name = "DELETE_STATUS", type = java.sql.Types.INTEGER, nullable = false)
    private Integer deleteStatus;

    /**
     * 乐观锁
     */
    @Column(name = "REVISION", type = java.sql.Types.INTEGER)
    private Integer revision;

    /**
     * 排序
     */
    @Column(name = "SORT", type = java.sql.Types.INTEGER)
    private Integer sort;

    public String getId() {
        return id;
    }

    public ScheduleTask setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ScheduleTask setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ScheduleTask setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ScheduleTask setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public ScheduleTask setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getParameters() {
        return parameters;
    }

    public ScheduleTask setParameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    public String getCornExpression() {
        return cornExpression;
    }

    public ScheduleTask setCornExpression(String cornExpression) {
        this.cornExpression = cornExpression;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ScheduleTask setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getConcurrentExec() {
        return concurrentExec;
    }

    public void setConcurrentExec(Integer concurrentExec) {
        this.concurrentExec = concurrentExec;
    }

    public Integer getActive() {
        return active;
    }

    public ScheduleTask setActive(Integer active) {
        this.active = active;
        return this;
    }

    public LocalDateTime getNextExeTime() {
        return nextExeTime;
    }

    public ScheduleTask setNextExeTime(LocalDateTime nextExeTime) {
        this.nextExeTime = nextExeTime;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ScheduleTask setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public ScheduleTask setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public ScheduleTask setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public ScheduleTask setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public ScheduleTask setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
        return this;
    }

    public Integer getRevision() {
        return revision;
    }

    public ScheduleTask setRevision(Integer revision) {
        this.revision = revision;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ScheduleTask setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleTask{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters='" + parameters + '\'' +
                ", cornExpression='" + cornExpression + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", nextExeTime=" + nextExeTime +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", revision=" + revision +
                ", sort=" + sort +
                '}';
    }
}