package net.cocotea.admin.api.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import net.cocotea.admin.api.system.model.dto.SysMenuAddDTO;
import net.cocotea.admin.api.system.model.dto.SysMenuPageDTO;
import net.cocotea.admin.api.system.model.dto.SysMenuUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysMenu;
import net.cocotea.admin.api.system.model.vo.SysMenuVO;
import net.cocotea.admin.api.system.service.SysMenuService;
import net.cocotea.admin.common.constant.RedisKey;
import net.cocotea.admin.common.enums.DeleteStatusEnum;
import net.cocotea.admin.common.enums.IsEnum;
import net.cocotea.admin.common.service.IRedisService;
import net.cocotea.admin.common.util.GenerateDsUtils;
import net.cocotea.admin.common.constant.CharConstant;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;
import org.sagacity.sqltoy.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author CoCoTea
 * @since 2022-11-28 17:51:41
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    private static final Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);
    @Db
    private SqlToyLazyDao sqlToyLazyDao;
    @Inject
    private IRedisService redisService;

    @Override
    public boolean add(SysMenuAddDTO param) {
        SysMenu sysMenu = sqlToyLazyDao.convertType(param, SysMenu.class);
        if (StringUtil.isBlank(sysMenu.getParentId())) {
            sysMenu.setParentId(String.valueOf(0));
        }
        if (StringUtil.isBlank(sysMenu.getPermissionCode()) && StringUtil.isNotBlank(sysMenu.getRouterPath())) {
            sysMenu.setPermissionCode(sysMenu.getRouterPath().replace(CharConstant.LEFT_LINE, CharConstant.COLON));
        }
        Object menuId = sqlToyLazyDao.save(sysMenu);
        return menuId != null;
    }

    @Override
    public boolean deleteBatch(List<String> idList) {
        idList.forEach(this::delete);
        return idList.size() > 0;
    }

    @Override
    public Page<SysMenuVO> listByPage(SysMenuPageDTO pageParam) {
        Page<SysMenuVO> page = sqlToyLazyDao.findPageBySql(pageParam, "system_menu_findByPageParam", pageParam.getMenu());
        return page;
    }

    @Override
    public Collection<SysMenuVO> listByTree(SysMenuPageDTO param) {
        List<SysMenuVO> sysMenuVOList = sqlToyLazyDao.findBySql("system_menu_findByPageParam", param.getMenu());
        GenerateDsUtils<SysMenuVO> dsUtils = new GenerateDsUtils<>();
        return dsUtils.buildTreeDefault(sysMenuVOList).values();
    }

    @Override
    public boolean update(SysMenuUpdateDTO param) {
        SysMenu sysMenu = sqlToyLazyDao.convertType(param, SysMenu.class);
        Long update = sqlToyLazyDao.update(sysMenu);
        return update > 0;
    }

    @Override
    public List<SysMenuVO> listByUserId(Integer isMenu) {
        Map<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("userId", StpUtil.getLoginId());
        hashMap.put("isMenu", isMenu);
        List<SysMenuVO> sysMenuVOList = sqlToyLazyDao.findBySql("system_menu_findUserPermission", hashMap, SysMenuVO.class);
        return sysMenuVOList;
    }

    @Tran
    @Override
    public boolean delete(String id) {
        SysMenu sysMenu = new SysMenu().setId(id).setDeleteStatus(DeleteStatusEnum.DELETE.getCode());
        Long update = sqlToyLazyDao.update(sysMenu);
        if (update <= 0) {
            return false;
        }
        // 获取子节点
        HashMap<String, Object> paramsMap = new HashMap<>(Character.UPPERCASE_LETTER);
        paramsMap.put("parentId", id);
        List<SysMenu> sysMenuList = sqlToyLazyDao.findBySql(
                "select id from sys_menu where #[PARENT_ID=:parentId] and DELETE_STATUS=1",
                paramsMap, SysMenu.class);
        if (sysMenuList.size() > 0) {
            // 存在子节点，删除子节点
            sysMenuList.forEach(item -> delete(item.getId()));
        }
        return true;
    }

    @Override
    public List<SysMenuVO> listByRoleId(String roleId) {
        Map<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("roleId", roleId);
        List<SysMenuVO> sysMenuVOList = sqlToyLazyDao.findBySql("system_menu_findByCommonParam", hashMap, SysMenuVO.class);
        return sysMenuVOList;
    }

    @Override
    public List<SysMenuVO> cachePermission(String userId) {
        // 缓存权限
        List<SysMenuVO> permissions = listByUserId(IsEnum.N.getCode());
        redisService.save(String.format(RedisKey.USER_PERMISSION, userId), JSONUtil.toJsonStr(permissions), 3600 * 24L);
        return permissions;
    }

    @Override
    public List<SysMenuVO> getCachePermission(String userId) {
        String s = redisService.get(String.format(RedisKey.USER_PERMISSION, userId));
        logger.info("[{}]-permissions={}", userId, s);
        return JSONUtil.toList(s, SysMenuVO.class);
    }

    @Override
    public Collection<SysMenuVO> listByTreeAsRoleSelection(SysMenuPageDTO pageParam) {
        List<SysMenuVO> sysMenuVOList = sqlToyLazyDao.findBySql("system_menu_findByPageParam", pageParam.getMenu());
        GenerateDsUtils<SysMenuVO> dsUtils = new GenerateDsUtils<>();
        return dsUtils.buildTreeDefault(sysMenuVOList).values();
    }

}
