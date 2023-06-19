package net.cocotea.admin.api.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.solon.integration.SaTokenInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import net.cocotea.admin.common.model.ApiResult;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author jwss
 * @date 2022-1-17 16:12:05
 */
@Configuration
public class WebMvcConfig {
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Bean(index = -100)  //-100，是顺序位（低值优先）
    public SaTokenInterceptor saTokenInterceptor(@Inject("${sra.api.excludes}") List<String> excludes) {
        SaTokenInterceptor interceptor = new SaTokenInterceptor();

        // 指定 [拦截路由] 与 [放行路由]
        interceptor.addInclude("/**");
        for (String url : excludes) {
            logger.info("[saTokenInterceptor]放行接口：{}", url);
            interceptor.addExclude(url);
        }

        // 认证函数: 每次请求执行
        interceptor.setAuth(req -> SaRouter.match("/**", StpUtil::checkLogin));

        // 异常处理函数：每次认证函数发生异常时执行此函数 //包括注解异常
        interceptor.setError(e -> ApiResult.error(e.getMessage()));

        // 前置函数：在每次认证函数之前执行
        interceptor.setBeforeAuth(req -> {
            // ---------- 设置一些安全响应头 ----------
            SaHolder.getResponse()
                    // 服务器名称
                    .setServer("sa-server")
                    // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                    .setHeader("X-Frame-Options", "SAMEORIGIN")
                    // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                    .setHeader("X-XSS-Protection", "1; mode=block")
                    // 禁用浏览器内容嗅探
                    .setHeader("X-Content-Type-Options", "nosniff");
        });

        return interceptor;
    }

}
