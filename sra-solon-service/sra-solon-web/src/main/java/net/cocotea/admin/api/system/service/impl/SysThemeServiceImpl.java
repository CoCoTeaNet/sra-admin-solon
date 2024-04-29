package net.cocotea.admin.api.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import net.cocotea.admin.api.system.model.dto.SysThemeUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysTheme;
import net.cocotea.admin.api.system.model.vo.SysThemeVO;
import net.cocotea.admin.api.system.service.SysThemeService;
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
public class SysThemeServiceImpl implements SysThemeService {

    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean updateByUser(SysThemeUpdateDTO param) {
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