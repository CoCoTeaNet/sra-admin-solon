package net.cocotea.admin.api.system.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.cocotea.admin.api.system.model.vo.SysFileVO;
import org.noear.solon.validation.annotation.NotNull;
import org.sagacity.sqltoy.model.Page;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysFilePageDTO extends Page<SysFileVO> implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;

    @NotNull(message = "查询参数为空")
    private SysFileVO sysFile;

    /**
     * 是否删除：{@link net.cocotea.admin.common.enums.IsEnum}
     */
    private Integer isDeleted;
}