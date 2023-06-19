package net.cocotea.admin.service.system;

import net.cocotea.admin.common.enums.LogTypeEnum;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.model.system.dto.log.OperationLogAddParam;
import net.cocotea.admin.model.system.dto.log.OperationLogPageParam;
import net.cocotea.admin.model.system.dto.log.OperationLogUpdateParam;
import net.cocotea.admin.model.system.vo.OperationLogVO;
import org.noear.solon.core.handle.Context;
import org.sagacity.sqltoy.model.Page;

/**
 * @author jwss
 * @date 2022-4-26 22:59:14
 */
public interface IOperationLogService extends IBaseService<Page<OperationLogVO>, OperationLogPageParam, OperationLogAddParam, OperationLogUpdateParam> {
    /**
     * 通过日志类型保存
     *
     * @param logType {@link LogTypeEnum}
     * @throws BusinessException 异常抛出
     */
    void saveByLogType(Integer logType) throws BusinessException;

    void saveErrorLog(Context ctx);
}
