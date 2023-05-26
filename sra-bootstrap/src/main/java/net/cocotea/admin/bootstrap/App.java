package net.cocotea.admin.bootstrap;

import org.noear.solon.Solon;
import org.noear.solon.annotation.Import;
import org.noear.solon.annotation.SolonMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CoCoTea
 * @since v1.2.7
 */
@SolonMain
@Import(scanPackages = {"net.cocotea.admin", ""})
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Solon.start(App.class, args);

        // ConfigurableApplicationContext context = SpringApplication.run(AppBoot.class, args);
        // Environment environment = context.getEnvironment();
        // String contextPath = environment.getProperty("server.servlet.context-path");
        // contextPath = StrUtil.isBlank(contextPath) ? contextPath : "";
        //
        // DefaultProperties defaultProperties = (DefaultProperties)context.getBean("defaultProperties");
        // GlobalValue.PORT = Integer.valueOf(Objects.requireNonNull(environment.getProperty("server.port")));
        // GlobalValue.AGREEMENT = defaultProperties.getAgreement();
        // GlobalValue.SERVER_IP = defaultProperties.getDomain();
        //
        // String[] urls = {
        //         String.format("http://localhost:%s%s", GlobalValue.PORT, contextPath),
        //         GlobalValue.getServerUrl()
        // };
        //
        // logger.info("CMS首页访问：{}/test/index || {}/cmsPage/index", urls[0], urls[1]);
        // logger.info("测试接口访问：{}/test/index || {}/test/index", urls[0], urls[1]);
        // DevEnableProperties devEnableProperties = (DevEnableProperties)context.getBean("devEnableProperties");
        // logger.warn("强密码：{}, 权限缓存状态：{}", devEnableProperties.getStrongPassword(), devEnableProperties.getPermissionCache());
        // GlobalValue.START_TIME = System.currentTimeMillis();
    }
}
