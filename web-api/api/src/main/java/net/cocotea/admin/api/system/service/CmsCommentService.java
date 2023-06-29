package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.CmsCommentAddDTO;
import net.cocotea.admin.api.system.model.dto.CmsCommentPageDTO;
import net.cocotea.admin.api.system.model.dto.CmsCommentUpdateDTO;
import net.cocotea.admin.api.system.model.vo.CmsCommentVO;
import net.cocotea.admin.common.service.IBaseService;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

public interface CmsCommentService extends IBaseService<Page<CmsCommentVO>, CmsCommentPageDTO, CmsCommentAddDTO, CmsCommentUpdateDTO> {
    List<CmsCommentVO> listByArticleId(String articleId);
}
