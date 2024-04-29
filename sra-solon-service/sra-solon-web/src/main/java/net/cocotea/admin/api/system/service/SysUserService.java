package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.SysLoginDTO;
import net.cocotea.admin.api.system.model.dto.SysUserAddDTO;
import net.cocotea.admin.api.system.model.dto.SysUserPageDTO;
import net.cocotea.admin.api.system.model.dto.SysUserUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysLoginUserVO;
import net.cocotea.admin.api.system.model.vo.SysUserVO;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.service.IBaseService;
import org.sagacity.sqltoy.model.Page;

/**
 * @author jwss
 * @date 2022-1-12 15:32:55
 */
public interface SysUserService extends IBaseService<Page<SysUserVO>, SysUserPageDTO, SysUserAddDTO, SysUserUpdateDTO> {
    /**
     * 用户登录
     *
     * @param param 登录参数
     * @return token
     * @throws BusinessException 业务异常
     */
    SysLoginUserVO login(SysLoginDTO param) throws BusinessException;

    /**
     * 用户获取个人详细信息
     * @return 个人详细信息
     */
    SysUserVO getDetail();

    SysLoginUserVO loginUser();

    /**
     * 修改个人账号密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 成功TRUE
     */
    boolean doModifyPassword(String oldPassword, String newPassword) throws BusinessException;
}
