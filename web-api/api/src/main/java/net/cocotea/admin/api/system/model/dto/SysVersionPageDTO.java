package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.SysVersionVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

public class SysVersionPageDTO extends Page<SysVersionVO> implements Serializable {
	private static final long serialVersionUID = 2569886533383934522L;

	// @NotNull(message = "version is null")
	private SysVersionVO version;

	public SysVersionVO getVersion() {
		return version;
	}

	public SysVersionPageDTO setVersion(SysVersionVO version) {
		this.version = version;
		return this;
	}
}
