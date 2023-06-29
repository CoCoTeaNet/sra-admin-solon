package net.cocotea.admin.api.system.model.dto;

import org.noear.solon.validation.annotation.NotBlank;

import java.io.Serializable;
public class SysVersionAddDTO implements Serializable {
	private static final long serialVersionUID = 1746246243402738341L;

	@NotBlank(message = "版本号不能为空")
	private String updateNo;
	private String updateDesc;
	private String platformName;
	private String downloadUrl;

	public String getUpdateNo() {
		return updateNo;
	}

	public SysVersionAddDTO setUpdateNo(String updateNo) {
		this.updateNo = updateNo;
		return this;
	}

	public String getUpdateDesc() {
		return updateDesc;
	}

	public SysVersionAddDTO setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
		return this;
	}

	public String getPlatformName() {
		return platformName;
	}

	public SysVersionAddDTO setPlatformName(String platformName) {
		this.platformName = platformName;
		return this;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public SysVersionAddDTO setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
		return this;
	}
}
