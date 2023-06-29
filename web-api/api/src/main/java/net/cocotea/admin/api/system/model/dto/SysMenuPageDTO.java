package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.SysMenuVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

/**
 * @author jwss
 * @project sss-rbac-admin
 * @version 1.0.0
 * @description sys_menu,系统菜单表  
 */
public class SysMenuPageDTO extends Page<SysMenuVO> implements Serializable{

	private static final long serialVersionUID = -772057092053351688L;

	private SysMenuVO menu;

	public SysMenuVO getMenu() {
		return menu;
	}

	public SysMenuPageDTO setMenu(SysMenuVO menu) {
		this.menu = menu;
		return this;
	}
}
