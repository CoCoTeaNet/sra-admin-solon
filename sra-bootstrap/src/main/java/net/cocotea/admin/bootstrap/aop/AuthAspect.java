package net.cocotea.admin.bootstrap.aop;

import cn.dev33.satoken.stp.StpUtil;
import net.cocotea.admin.framework.service.IRedisService;
import net.cocotea.admin.framework.constant.RedisKey;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.AopContext;
import org.sagacity.sqltoy.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户认证切面
 *
 * @author CoCoTea
 * @date 2022-4-10 20:23:17
 */
@Component
public class AuthAspect {
    private final Logger logger = LoggerFactory.getLogger(AuthAspect.class);

    @Inject
    private IRedisService redisService;

    @Inject
    private AopContext aopContext;

    public void requestAspect() {
        aopContext.beanScan("public * net.cocotea.admin.*.controller.*.*(..)");

    }

    /**
     * 在线用户续期
     */
    private void onlineUsersRenewal() {
        if (StpUtil.isLogin()) {
            if (StringUtil.isBlank(redisService.get(RedisKey.ONLINE_USER))) {
                String loginId = String.valueOf(StpUtil.getLoginId());
                redisService.save(String.format(RedisKey.ONLINE_USER, loginId), loginId, 30L);
            }
        }
    }
}
