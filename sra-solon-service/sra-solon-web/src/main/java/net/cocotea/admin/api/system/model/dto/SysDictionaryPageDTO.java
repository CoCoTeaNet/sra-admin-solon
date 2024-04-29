package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.SysDictionaryVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

/**
 * 字典分页参数
 * @author jwss
 * @date 2022-3-22
 */
public class SysDictionaryPageDTO extends Page<SysDictionaryVO> implements Serializable {
    private static final long serialVersionUID = 8818887425379309640L;

    private SysDictionaryVO dictionary;

    public SysDictionaryVO getDictionary() {
        return dictionary;
    }

    public SysDictionaryPageDTO setDictionary(SysDictionaryVO dictionary) {
        this.dictionary = dictionary;
        return this;
    }
}
