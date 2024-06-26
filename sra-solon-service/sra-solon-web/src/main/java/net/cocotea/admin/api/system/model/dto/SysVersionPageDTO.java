package net.cocotea.admin.api.system.model.dto;

import org.noear.solon.validation.annotation.NotNull;;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.cocotea.admin.api.system.model.vo.SysVersionVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysVersionPageDTO extends Page<SysVersionVO> implements Serializable {
	@Serial
	private static final long serialVersionUID = 2569886533383934522L;

	@NotNull(message = "查询参数为空")
	private SysVersionVO sysVersion;

}
