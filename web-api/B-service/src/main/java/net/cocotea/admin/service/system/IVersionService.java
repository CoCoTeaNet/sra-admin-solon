package net.cocotea.admin.service.system;

import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.model.system.dto.version.VersionAddParam;
import net.cocotea.admin.model.system.dto.version.VersionPageParam;
import net.cocotea.admin.model.system.dto.version.VersionUpdateParam;
import net.cocotea.admin.model.system.vo.VersionVO;
import org.sagacity.sqltoy.model.Page;

public interface IVersionService extends IBaseService<Page<VersionVO>, VersionPageParam, VersionAddParam, VersionUpdateParam> {
}
