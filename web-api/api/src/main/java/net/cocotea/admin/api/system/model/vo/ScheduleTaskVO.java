
package net.cocotea.admin.api.system.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Guo wentao
 * @date 2022/8/29
 */
public class ScheduleTaskVO implements Serializable {
    private static final long serialVersionUID = 1651150165407974544L;
    private String id;
    private String name;
    private String methodName;
    private String parameters;
    private Integer type;
    private String className;
    private String cornExpression;
    private String description;
    private Integer concurrentExec;
    private Integer active;
    private Integer sort;
    private LocalDateTime nextExeTime;

    public String getId() {
        return id;
    }

    public ScheduleTaskVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ScheduleTaskVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public ScheduleTaskVO setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getParameters() {
        return parameters;
    }

    public ScheduleTaskVO setParameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ScheduleTaskVO setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ScheduleTaskVO setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getCornExpression() {
        return cornExpression;
    }

    public ScheduleTaskVO setCornExpression(String cornExpression) {
        this.cornExpression = cornExpression;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ScheduleTaskVO setDescription(String description) {
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

    public ScheduleTaskVO setActive(Integer active) {
        this.active = active;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ScheduleTaskVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public LocalDateTime getNextExeTime() {
        return nextExeTime;
    }

    public ScheduleTaskVO setNextExeTime(LocalDateTime nextExeTime) {
        this.nextExeTime = nextExeTime;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleTaskVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters='" + parameters + '\'' +
                ", type=" + type +
                ", className='" + className + '\'' +
                ", cornExpression='" + cornExpression + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", sort=" + sort +
                ", nextExeTime=" + nextExeTime +
                '}';
    }
}