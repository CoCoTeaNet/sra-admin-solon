package net.cocotea.admin.service.system;

import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.model.system.dto.login.LoginParam;
import net.cocotea.admin.model.system.dto.user.UserAddParam;
import net.cocotea.admin.model.system.dto.user.UserPageParam;
import net.cocotea.admin.model.system.dto.user.UserUpdateParam;
import net.cocotea.admin.model.system.vo.LoginUserVO;
import net.cocotea.admin.model.system.vo.UserVO;
import org.sagacity.sqltoy.model.Page;

/**
 * @author jwss
 * @date 2022-1-12 15:32:55
 */
public interface IUserService extends IBaseService<Page<UserVO>, UserPageParam, UserAddParam, UserUpdateParam> {
    /**
     * 用户登录
     *
     * @param param 登录参数
     * @return token
     * @throws BusinessException 业务异常
     */
    LoginUserVO login(LoginParam param) throws BusinessException;

    /**
     * 用户获取个人详细信息
     * @return 个人详细信息
     */
    UserVO getDetail();

    LoginUserVO loginUser();
}
