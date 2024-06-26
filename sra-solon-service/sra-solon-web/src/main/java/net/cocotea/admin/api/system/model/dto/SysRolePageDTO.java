package net.cocotea.admin.api.system.model.dto;

import org.noear.solon.validation.annotation.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.cocotea.admin.api.system.model.vo.SysMenuVO;
import net.cocotea.admin.api.system.model.vo.SysRoleVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author CoCoTea
 * @version 2.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysRolePageDTO extends Page<SysMenuVO> implements Serializable {

	@Serial
	private static final long serialVersionUID = -8722226920902960302L;

	@NotNull(message = "查询参数为空")
	private SysRoleVO sysRole;
}
