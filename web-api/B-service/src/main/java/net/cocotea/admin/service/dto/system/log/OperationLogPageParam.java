package net.cocotea.admin.service.dto.system.log;

import net.cocotea.admin.data.model.SysOperationLog;
import net.cocotea.admin.service.vo.system.OperationLogVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

/**
 * 系统操作日志 分页查询参数
 *
 * @author jwss
 * @date 2022-4-29 16:13:50
 */
public class OperationLogPageParam extends Page<SysOperationLog> implements Serializable {
    private static final long serialVersionUID = -2889574415371661414L;

    private OperationLogVO operationLogVO;

    public OperationLogVO getOperationLogVO() {
        return operationLogVO;
    }

    public void setOperationLogVO(OperationLogVO operationLogVO) {
        this.operationLogVO = operationLogVO;
    }
}
