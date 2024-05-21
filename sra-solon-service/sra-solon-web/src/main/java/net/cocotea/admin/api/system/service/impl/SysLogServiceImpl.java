package net.cocotea.admin.api.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import com.sagframe.sagacity.sqltoy.plus.conditions.Wrappers;
import com.sagframe.sagacity.sqltoy.plus.dao.SqlToyHelperDao;
import com.sagframe.sagacity.sqltoy.plus.multi.MultiWrapper;
import com.sagframe.sagacity.sqltoy.plus.multi.model.LambdaColumn;
import net.cocotea.admin.api.system.model.dto.SysLogAddDTO;
import net.cocotea.admin.api.system.model.dto.SysLogPageDTO;
import net.cocotea.admin.api.system.model.dto.SysLogUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysLog;
import net.cocotea.admin.api.system.model.po.SysUser;
import net.cocotea.admin.api.system.model.vo.SysLogVO;
import net.cocotea.admin.api.system.service.SysLogService;
import net.cocotea.admin.common.enums.LogStatusEnum;
import net.cocotea.admin.common.enums.LogTypeEnum;
import net.cocotea.admin.common.model.ApiPage;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.properties.DefaultProp;
import net.cocotea.admin.util.LoginUtils;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.Context;
import org.sagacity.sqltoy.model.Page;
import org.noear.solon.aspect.annotation.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Inject
    private SqlToyHelperDao sqlToyHelperDao;

    @Inject
    private DefaultProp defaultProp;

    @Override
    public boolean add(SysLogAddDTO param) throws BusinessException {
        SysLog sysOperationLog = Convert.convert(SysLog.class, param);
        Object save = sqlToyHelperDao.save(sysOperationLog);
        return save != null;
    }

    @Override
    public boolean deleteBatch(List<BigInteger> idList) throws BusinessException {
        idList.forEach(this::delete);
        return !idList.isEmpty();
    }

    @Override
    public boolean update(SysLogUpdateDTO param) throws BusinessException {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return sqlToyHelperDao.deleteByIds(SysLog.class, id) > 0;
    }

    @Override
    public ApiPage<SysLogVO> listByPage(SysLogPageDTO pageDTO) throws BusinessException {
        MultiWrapper multiWrapper = Wrappers.lambdaMultiWrapper(SysLogVO.class)
                .select(
                        LambdaColumn.of(SysLog::getId), LambdaColumn.of(SysLog::getIpAddress),
                        LambdaColumn.of(SysLog::getRequestWay), LambdaColumn.of(SysLog::getLogStatus),
                        LambdaColumn.of(SysLog::getLogType), LambdaColumn.of(SysLog::getCreateTime),
                        LambdaColumn.of(SysUser::getUsername), LambdaColumn.of(SysUser::getNickname)
                )
                .from(SysLog.class)
                .leftJoin(SysUser.class).on().eq(SysLog::getOperator, SysUser::getId)
                .where()
                .eq(SysLog::getId, pageDTO.getSysLog().getId())
                .like(SysUser::getUsername, pageDTO.getSysLog().getOperator())
                .like(SysUser::getNickname, pageDTO.getSysLog().getOperator());
        Page<SysLogVO> page = sqlToyHelperDao.findPage(multiWrapper, new Page<>(pageDTO.getPageSize(), pageDTO.getPageNo()));
        return ApiPage.rest(page, SysLogVO.class);
    }

    @Override
    public void saveByLogType(Integer logType, Context context) throws BusinessException {
        ThreadUtil.execAsync(() -> {
            if (defaultProp.getSaveLog()) {
                SysLogAddDTO sysLogAddDTO = new SysLogAddDTO();
                sysLogAddDTO.setIpAddress(context.ip());
                sysLogAddDTO.setLogType(logType);
                sysLogAddDTO.setRequestWay(context.method());
                sysLogAddDTO.setOperator(LoginUtils.loginId());
                sysLogAddDTO.setLogStatus(LogStatusEnum.SUCCESS.getCode());
                try {
                    add(sysLogAddDTO);
                } catch (BusinessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void saveErrorLog(Context context) {
        if (StpUtil.isLogin() && defaultProp.getSaveLog()) {
            SysLogAddDTO sysLogAddDTO = new SysLogAddDTO();
            sysLogAddDTO.setIpAddress(context.ip());
            sysLogAddDTO.setLogType(LogTypeEnum.OPERATION.getCode());
            sysLogAddDTO.setRequestWay(context.method());
            sysLogAddDTO.setOperator(LoginUtils.loginId());
            sysLogAddDTO.setLogStatus(LogStatusEnum.ERROR.getCode());
            try {
                add(sysLogAddDTO);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
