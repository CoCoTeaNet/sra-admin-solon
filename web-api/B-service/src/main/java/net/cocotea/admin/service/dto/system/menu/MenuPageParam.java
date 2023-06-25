package net.cocotea.admin.service.dto.system.menu;

import net.cocotea.admin.service.vo.system.MenuVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

/**
 * @author jwss
 * @project sss-rbac-admin
 * @version 1.0.0
 * @description sys_menu,系统菜单表  
 */
public class MenuPageParam extends Page<MenuVO> implements Serializable{

	private static final long serialVersionUID = -772057092053351688L;

	private MenuVO menuVO;

	public MenuVO getMenuVO() {
		return menuVO;
	}

	public void setMenuVO(MenuVO menuVO) {
		this.menuVO = menuVO;
	}
}
