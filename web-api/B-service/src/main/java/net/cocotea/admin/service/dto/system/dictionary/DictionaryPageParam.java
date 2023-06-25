package net.cocotea.admin.service.dto.system.dictionary;

import net.cocotea.admin.service.vo.system.DictionaryVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

/**
 * 字典分页参数
 * @author jwss
 * @date 2022-3-22
 */
public class DictionaryPageParam extends Page<DictionaryVO> implements Serializable {
    private static final long serialVersionUID = 8818887425379309640L;

    private DictionaryVO dictionaryVO;

    public DictionaryVO getDictionaryVO() {
        return dictionaryVO;
    }

    public void setDictionaryVO(DictionaryVO dictionaryVO) {
        this.dictionaryVO = dictionaryVO;
    }
}
