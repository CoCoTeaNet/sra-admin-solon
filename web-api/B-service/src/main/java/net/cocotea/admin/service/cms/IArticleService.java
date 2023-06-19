package net.cocotea.admin.service.cms;

import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.model.cms.dto.article.ArticleAddParam;
import net.cocotea.admin.model.cms.dto.article.ArticlePageParam;
import net.cocotea.admin.model.cms.dto.article.ArticleUpdateParam;
import net.cocotea.admin.model.cms.vo.ArchiveVO;
import net.cocotea.admin.model.cms.vo.ArticleVO;
import net.cocotea.admin.model.cms.vo.TagVO;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * @author CoCoTea
 */
public interface IArticleService extends IBaseService<Page<ArticleVO>, ArticlePageParam, ArticleAddParam, ArticleUpdateParam> {
    ArticleVO detail(String id);

    List<TagVO> findTags(List<ArticleVO> articleVOList);

    List<ArticleVO> findByTimeDesc();

    List<ArchiveVO> findByArchiveList();
}
