/**
 *@Generated by sagacity-quickvo 5.0
 */
package net.cocotea.admin.api.system.model.po;

import lombok.Data;
import lombok.experimental.Accessors;
import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @project sqltoy-quickstart
 * @author CoCoTea
 * @version 1.0.0 
 */
@Data
@Accessors(chain = true)
@Entity(tableName="sys_role_menu",comment="角色菜单关联表",pk_constraint="PRIMARY")
public class SysRoleMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7293681118210462724L;
/*---begin-auto-generate-don't-update-this-area--*/	

	@Id(strategy="generator",generator="org.sagacity.sqltoy.plugins.id.impl.SnowflakeIdGenerator")
	@Column(name="id",comment="角色菜单关联id",length=3L,type=java.sql.Types.TINYINT,nullable=false)
	private BigInteger id;

	@Column(name="role_id",comment="角色id",length=19L,type=java.sql.Types.BIGINT,nullable=false)
	private BigInteger roleId;

	@Column(name="menu_id",comment="菜单id",length=19L,type=java.sql.Types.BIGINT,nullable=false)
	private BigInteger menuId;
	/** default constructor */
	public SysRoleMenu() {
	}
	
	/** pk constructor */
	public SysRoleMenu(BigInteger id)
	{
		this.id=id;
	}
/*---end-auto-generate-don't-update-this-area--*/
}
