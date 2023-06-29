package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.po.SysOperationLog;
import net.cocotea.admin.api.system.model.vo.SysOperationLogVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

/**
 * 系统操作日志 分页查询参数
 *
 * @author jwss
 * @date 2022-4-29 16:13:50
 */
public class SysOperationLogPageDTO extends Page<SysOperationLog> implements Serializable {
    private static final long serialVersionUID = -2889574415371661414L;

    private SysOperationLogVO operationLog;

    public SysOperationLogVO getOperationLog() {
        return operationLog;
    }

    public SysOperationLogPageDTO setOperationLog(SysOperationLogVO operationLog) {
        this.operationLog = operationLog;
        return this;
    }
}
