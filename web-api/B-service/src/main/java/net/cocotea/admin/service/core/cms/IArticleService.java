package net.cocotea.admin.service.core.cms;

import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.service.dto.cms.article.ArticleAddParam;
import net.cocotea.admin.service.dto.cms.article.ArticlePageParam;
import net.cocotea.admin.service.dto.cms.article.ArticleUpdateParam;
import net.cocotea.admin.service.vo.cms.ArchiveVO;
import net.cocotea.admin.service.vo.cms.ArticleVO;
import net.cocotea.admin.service.vo.cms.TagVO;
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
