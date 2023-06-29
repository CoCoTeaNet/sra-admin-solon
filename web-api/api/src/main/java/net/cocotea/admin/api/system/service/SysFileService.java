package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.SysFileAddDTO;
import net.cocotea.admin.api.system.model.dto.SysFilePageDTO;
import net.cocotea.admin.api.system.model.dto.SysFileUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysFileVO;
import net.cocotea.admin.common.service.IBaseService;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * 系统文件表
 *
 * @author CoCoTea 572315466@qq.com
 * @since 1.2.1 2022-12-28
 */
public interface SysFileService extends IBaseService<Page<SysFileVO>, SysFilePageDTO, SysFileAddDTO, SysFileUpdateDTO> {
    /**
     * 数据为删除状态的数据
     *
     * @param param 系统文件分页参数
     * @return 分页对象
     */
    Page<SysFileVO> recycleBinPage(SysFilePageDTO param);

    /**
     * 删除回收站的文件
     *
     * @param param 列表id
     * @return 成功返回TRUE
     */
    boolean recycleBinDeleteBatch(List<String> param);

    /**
     * 恢复已删除的文件
     *
     * @param param 主键ID集合
     * @return 成功返回TRUE
     */
    boolean recoveryBatch(List<String> param);
}