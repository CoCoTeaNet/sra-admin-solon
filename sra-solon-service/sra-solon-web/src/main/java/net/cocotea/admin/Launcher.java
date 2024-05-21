package net.cocotea.admin;

import net.cocotea.admin.common.constant.GlobalConst;
import net.cocotea.admin.properties.DefaultProp;
import org.noear.solon.Solon;
import org.noear.solon.SolonApp;
import org.noear.solon.annotation.Import;
import org.noear.solon.annotation.SolonMain;
import org.noear.solon.core.AopContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CoCoTea
 * @since v1.2.7
 */
@SolonMain
@Import(scanPackages = {"net.cocotea.admin"})
public class Launcher {
    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        SolonApp app = Solon.start(Launcher.class, args);
        AopContext aopContext = app.context();

        DefaultProp defaultProp = aopContext.getBean("devEnableProps");
        logger.warn("强密码：{}, 权限缓存状态：{}", defaultProp.getStrongPassword(), defaultProp.getPermissionCache());

        GlobalConst.START_TIME = System.currentTimeMillis();
    }
}
