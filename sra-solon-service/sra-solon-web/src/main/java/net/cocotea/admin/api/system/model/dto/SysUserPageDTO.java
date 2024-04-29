package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.SysUserVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

/**
 * @author jwss
 * @project sss-rbac-admin
 * @version 1.0.0
 * @description sys_menu,系统菜单表  
 */
public class SysUserPageDTO extends Page<SysUserVO> implements Serializable{

	private static final long serialVersionUID = 5565588370362046172L;

	private SysUserVO user;

	public SysUserVO getUser() {
		return user;
	}

	public SysUserPageDTO setUser(SysUserVO user) {
		this.user = user;
		return this;
	}
}
