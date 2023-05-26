package net.cocotea.admin.bootstrap.properties;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;

/**
 * satoken配置
 * @date 2022-1-17 16:33:35
 * @author jwss
 */
@Component
public class SatokenProperties {
    /**
     * 路由放行
     */
    @Inject("${sra.sa-token.excludes}")
    private List<String> excludes;

    public List<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }
}
