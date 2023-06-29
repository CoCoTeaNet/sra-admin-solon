package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.CmsArticleVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;

public class CmsArticlePageDTO extends Page<CmsArticleVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    private CmsArticleVO article;

    public CmsArticleVO getArticle() {
        return article;
    }

    public CmsArticlePageDTO setArticle(CmsArticleVO article) {
        this.article = article;
        return this;
    }
}
