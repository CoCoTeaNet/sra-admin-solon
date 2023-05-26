package net.cocotea.admin.bootstrap.properties;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

/**
 * @date 2022-9-13 14:33:34
 * @author CoCoTea
 */
@Component
public class AopProperties {
    /**
     * 1秒内限制api访问的次数
     */
    @Inject("${sra.aop.visits}")
    private Integer visits;

    public Integer getVisits() {
        return visits;
    }

    public AopProperties setVisits(Integer visits) {
        this.visits = visits;
        return this;
    }
}
