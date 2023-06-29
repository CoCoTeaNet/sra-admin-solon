package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.CmsCommentVO;
import org.sagacity.sqltoy.model.Page;

public class CmsCommentPageDTO extends Page<CmsCommentVO> {
    private static final long serialVersionUID = -1346427578243868811L;

    private CmsCommentVO comment;

    public CmsCommentVO getComment() {
        return comment;
    }

    public CmsCommentPageDTO setComment(CmsCommentVO comment) {
        this.comment = comment;
        return this;
    }
}
