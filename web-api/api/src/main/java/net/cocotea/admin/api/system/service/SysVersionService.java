package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.SysVersionAddDTO;
import net.cocotea.admin.api.system.model.dto.SysVersionPageDTO;
import net.cocotea.admin.api.system.model.dto.SysVersionUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysVersionVO;
import net.cocotea.admin.common.service.IBaseService;
import org.sagacity.sqltoy.model.Page;

public interface SysVersionService extends IBaseService<Page<SysVersionVO>, SysVersionPageDTO, SysVersionAddDTO, SysVersionUpdateDTO> {
}
