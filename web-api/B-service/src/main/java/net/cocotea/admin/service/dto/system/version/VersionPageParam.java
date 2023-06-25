package net.cocotea.admin.service.dto.system.version;

import net.cocotea.admin.service.vo.system.VersionVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

public class VersionPageParam extends Page<VersionVO> implements Serializable {
	private static final long serialVersionUID = 2569886533383934522L;

	// @NotNull(message = "version is null")
	private VersionVO version;

	public VersionVO getVersion() {
		return version;
	}

	public VersionPageParam setVersion(VersionVO version) {
		this.version = version;
		return this;
	}
}
