package net.cocotea.admin.service.core.cms;

import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.service.dto.cms.comment.CommentAddParam;
import net.cocotea.admin.service.dto.cms.comment.CommentPageParam;
import net.cocotea.admin.service.dto.cms.comment.CommentUpdateParam;
import net.cocotea.admin.service.vo.cms.CommentVO;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

public interface ICommentService extends IBaseService<Page<CommentVO>, CommentPageParam, CommentAddParam, CommentUpdateParam> {
    List<CommentVO> listByArticleId(String articleId);
}
