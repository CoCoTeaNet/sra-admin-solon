package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.SysOperationLogAddDTO;
import net.cocotea.admin.api.system.model.dto.SysOperationLogPageDTO;
import net.cocotea.admin.api.system.model.dto.SysOperationLogUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysOperationLogVO;
import net.cocotea.admin.common.enums.LogTypeEnum;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.service.IBaseService;
import org.noear.solon.core.handle.Context;
import org.sagacity.sqltoy.model.Page;

/**
 * @author jwss
 * @date 2022-4-26 22:59:14
 */
public interface SysOperationLogService extends IBaseService<Page<SysOperationLogVO>, SysOperationLogPageDTO, SysOperationLogAddDTO, SysOperationLogUpdateDTO> {
    /**
     * 通过日志类型保存
     *
     * @param logType {@link LogTypeEnum}
     * @throws BusinessException 异常抛出
     */
    void saveByLogType(Integer logType) throws BusinessException;

    void saveErrorLog(Context ctx);
}
