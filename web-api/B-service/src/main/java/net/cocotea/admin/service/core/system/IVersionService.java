package net.cocotea.admin.service.core.system;

import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.service.dto.system.version.VersionAddParam;
import net.cocotea.admin.service.dto.system.version.VersionPageParam;
import net.cocotea.admin.service.dto.system.version.VersionUpdateParam;
import net.cocotea.admin.service.vo.system.VersionVO;
import org.sagacity.sqltoy.model.Page;

public interface IVersionService extends IBaseService<Page<VersionVO>, VersionPageParam, VersionAddParam, VersionUpdateParam> {
}
