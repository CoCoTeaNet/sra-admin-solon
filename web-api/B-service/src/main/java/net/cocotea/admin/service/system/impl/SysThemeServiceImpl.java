package net.cocotea.admin.service.system.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import net.cocotea.admin.model.system.dto.theme.SysThemeUpdateParam;
import net.cocotea.admin.model.system.po.SysTheme;
import net.cocotea.admin.model.system.vo.SysThemeVO;
import net.cocotea.admin.service.system.ISysThemeService;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.EntityQuery;

/**
 * 系统主题表
 *
 * @author CoCoTea 572315466@qq.com
 * @since 1.2.4 2023-02-25
 */
@Service
public class SysThemeServiceImpl implements ISysThemeService {

    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean updateByUser(SysThemeUpdateParam param) {
        String userId = StpUtil.getLoginId().toString();
        SysTheme sysTheme = Convert.convert(SysTheme.class, param);
        SysTheme loadSysTheme = sqlToyLazyDao.loadEntity(SysTheme.class, new EntityQuery().values("userId").names(userId));
        if (loadSysTheme == null) {
            sysTheme.setLayoutMode(0);
            sysTheme.setUserId(userId);
            return sqlToyLazyDao.save(sysTheme) != null;
        } else {
            sysTheme.setID(loadSysTheme.getID());
            return sqlToyLazyDao.update(sysTheme) > 0;
        }
    }

    @Override
    public SysThemeVO loadByUser() {
        String userId = StpUtil.getLoginId().toString();
        SysTheme loadSysTheme = sqlToyLazyDao.loadEntity(SysTheme.class, new EntityQuery().values("userId").names(userId));
        return Convert.convert(SysThemeVO.class, loadSysTheme);
    }
}