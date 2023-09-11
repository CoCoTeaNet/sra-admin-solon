package net.cocotea.admin.api.system.model.dto;

import java.io.Serializable;

/**
 * CoCoTea
 * @date 2022-9-2 20:46:46
 */
public class ScheduleTaskAddParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务对应类名
     */
    private String className;

    private String methodName;

    private String parameters;

    /**
     * Cron表达式
     */
    private String cornExpression;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 是否允许并发执行;0禁止 1允许
     */
    private Integer concurrentExec;

    /**
     * 是否启用;0未启用 1启用
     */
    private Integer active;
    private Integer type;

    private Integer sort;

    public Integer getType() {
        return type;
    }

    public ScheduleTaskAddParam setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public ScheduleTaskAddParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ScheduleTaskAddParam setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public ScheduleTaskAddParam setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getParameters() {
        return parameters;
    }

    public ScheduleTaskAddParam setParameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    public String getCornExpression() {
        return cornExpression;
    }

    public ScheduleTaskAddParam setCornExpression(String cornExpression) {
        this.cornExpression = cornExpression;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ScheduleTaskAddParam setDescription(String description) {
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

    public ScheduleTaskAddParam setActive(Integer active) {
        this.active = active;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ScheduleTaskAddParam setSort(Integer sort) {
        this.sort = sort;
        return this;
    }
}
