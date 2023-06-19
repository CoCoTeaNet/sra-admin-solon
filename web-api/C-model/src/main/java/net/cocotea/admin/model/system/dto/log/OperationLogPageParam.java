package net.cocotea.admin.model.system.dto.log;

import net.cocotea.admin.model.system.po.OperationLog;
import net.cocotea.admin.model.system.vo.OperationLogVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

/**
 * 系统操作日志 分页查询参数
 *
 * @author jwss
 * @date 2022-4-29 16:13:50
 */
public class OperationLogPageParam extends Page<OperationLog> implements Serializable {
    private static final long serialVersionUID = -2889574415371661414L;

    private OperationLogVO operationLogVO;

    public OperationLogVO getOperationLogVO() {
        return operationLogVO;
    }

    public void setOperationLogVO(OperationLogVO operationLogVO) {
        this.operationLogVO = operationLogVO;
    }
}
