package net.cocotea.admin.service.core.system;

import net.cocotea.admin.service.dto.system.theme.SysThemeUpdateParam;
import net.cocotea.admin.service.vo.system.SysThemeVO;

/**
 * 系统主题表
 *
 * @author CoCoTea 572315466@qq.com
 * @since 1.2.4 2023-02-25
 */
public interface ISysThemeService {
    boolean updateByUser(SysThemeUpdateParam param);

    SysThemeVO loadByUser();
}