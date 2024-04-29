package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.SysFileVO;
import org.noear.solon.validation.annotation.NotNull;
import org.sagacity.sqltoy.model.Page;
import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;

public class SysFilePageDTO extends Page<SysFileVO> implements Serializable {
    private static final long serialVersionUID = -1L;

    @NotNull(message = "sysFile is null")
    private SysFileVO file;

    public SysFileVO getFile() {
        return file;
    }

    public SysFilePageDTO setFile(SysFileVO file) {
        this.file = file;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}