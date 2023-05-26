package net.cocotea.admin.bootstrap.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.solon.integration.SaTokenInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.system.properties.FileUploadProperties;
import net.cocotea.admin.bootstrap.properties.SatokenProperties;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jwss
 * @date 2022-1-17 16:12:05
 */
@Configuration
public class WebMvcConfig {
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Inject
    private FileUploadProperties fileUploadProperties;

    @Inject
    private SatokenProperties satokenProperties;

    @Bean(index = -100)  //-100，是顺序位（低值优先）
    public SaTokenInterceptor saTokenInterceptor() {
        return new SaTokenInterceptor()
                // 指定 [拦截路由] 与 [放行路由]
                .addInclude("/**").addExclude("/favicon.ico")

                // 认证函数: 每次请求执行
                .setAuth(req -> {
                    // System.out.println("---------- sa全局认证");
                    SaRouter.match("/**", StpUtil::checkLogin);

                    // 根据路由划分模块，不同模块不同鉴权
                    SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
                })

                // 异常处理函数：每次认证函数发生异常时执行此函数 //包括注解异常
                .setError(e -> {
                    System.out.println("---------- sa全局异常 ");
                    return ApiResult.error(e.getMessage());
                })

                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(req -> {
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
    }

    // @Bean
    // public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
    //     FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    //     FastJsonConfig fastJsonConfig = new FastJsonConfig();
    //     fastJsonConfig.setDateFormat("yyyy-MM-dd hh:mm:ss");
    //     fastJsonConfig.setSerializerFeatures(
    //             SerializerFeature.PrettyFormat,
    //             SerializerFeature.WriteMapNullValue,
    //             SerializerFeature.WriteNullNumberAsZero,
    //             SerializerFeature.WriteNullStringAsEmpty
    //     );
    //     // 处理中文乱码问题
    //     List<MediaType> fastMediaTypes = new ArrayList<>();
    //     fastMediaTypes.add(MediaType.APPLICATION_JSON);
    //     fastConverter.setSupportedMediaTypes(fastMediaTypes);
    //     fastConverter.setFastJsonConfig(fastJsonConfig);
    //     return fastConverter;
    // }

    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     converters.add(fastJsonHttpMessageConverter());
    // }

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     String locations = "file:" + fileUploadProperties.getLocalUrl();
    //     String pathPatterns = fileUploadProperties.getBrowserUrl() + "**";
    //     logger.info("本地上传路径：{}", locations);
    //     logger.info("文件映射路径：{}", pathPatterns);
    //     registry.addResourceHandler(pathPatterns).addResourceLocations(locations);
    // }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
    //     registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
    //             .addPathPatterns("/**")
    //             .excludePathPatterns(getExcludeList());
    // }

    /**
     * 注册 [Sa-Token全局过滤器]
     */
    // @Bean
    // public SaServletFilter getSaServletFilter() {
    //     return new SaServletFilter().setBeforeAuth(r -> {
    //         SaHolder.getResponse()
    //                 // 服务器名称
    //                 .setServer("sa-server")
    //                 // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
    //                 .setHeader("X-Frame-Options", "SAMEORIGIN")
    //                 // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
    //                 .setHeader("X-XSS-Protection", "1; mode=block")
    //                 // 禁用浏览器内容嗅探
    //                 .setHeader("X-Content-Type-Options", "nosniff");
    //     });
    // }

    /**
     * 获取放行路由列表
     *
     * @return 路由集合
     */
    public List<String> getExcludeList() {
        List<String> excludes = satokenProperties.getExcludes();
        logger.info("############# 放行路由START ##############");
        if (excludes != null) {
            excludes.forEach(logger::info);
        }
        logger.info("############# 放行路由END ##############");
        return excludes;
    }
}
