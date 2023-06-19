package net.cocotea.admin.service.system;

import net.cocotea.admin.model.system.dto.theme.SysThemeUpdateParam;
import net.cocotea.admin.model.system.vo.SysThemeVO;

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