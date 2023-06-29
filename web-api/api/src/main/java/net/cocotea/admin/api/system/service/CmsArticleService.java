package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.CmsArticleAddDTO;
import net.cocotea.admin.api.system.model.dto.CmsArticlePageDTO;
import net.cocotea.admin.api.system.model.dto.CmsArticleUpdateDTO;
import net.cocotea.admin.api.system.model.vo.CmsArchiveVO;
import net.cocotea.admin.api.system.model.vo.CmsArticleVO;
import net.cocotea.admin.api.system.model.vo.CmsTagVO;
import net.cocotea.admin.common.service.IBaseService;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * @author CoCoTea
 */
public interface CmsArticleService extends IBaseService<Page<CmsArticleVO>, CmsArticlePageDTO, CmsArticleAddDTO, CmsArticleUpdateDTO> {
    CmsArticleVO detail(String id);

    List<CmsTagVO> findTags(List<CmsArticleVO> sysArticleVOList);

    List<CmsArticleVO> findByTimeDesc();

    List<CmsArchiveVO> findByArchiveList();
}
