package net.cocotea.admin.service.system;

import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.model.system.dto.role.RoleAddParam;
import net.cocotea.admin.model.system.dto.role.RolePageParam;
import net.cocotea.admin.model.system.dto.role.RoleUpdateParam;
import net.cocotea.admin.model.system.vo.RoleMenuVO;
import net.cocotea.admin.model.system.vo.RoleVO;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * 角色服务类
 * @date 2022-1-17 17:14:06
 * @author jwss
 */
public interface IRoleService extends IBaseService<Page<RoleVO>, RolePageParam, RoleAddParam, RoleUpdateParam> {
    /**
     * 给角色赋予权限
     * @param roleMenuVOList 角色菜单列表
     * @return 成功返回true
     * @throws BusinessException 业务异常
     */
    boolean grantPermissionsByRoleId(List<RoleMenuVO> roleMenuVOList) throws BusinessException;

    /**
     * 通过用户ID获取角色
     * @param userId 用户id
     * @return 角色
     */
    List<RoleVO> loadByUserId(String userId);
}
