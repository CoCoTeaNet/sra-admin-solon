package net.cocotea.admin.service.cms;

import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.model.cms.dto.comment.CommentAddParam;
import net.cocotea.admin.model.cms.dto.comment.CommentPageParam;
import net.cocotea.admin.model.cms.dto.comment.CommentUpdateParam;
import net.cocotea.admin.model.cms.vo.CommentVO;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

public interface ICommentService extends IBaseService<Page<CommentVO>, CommentPageParam, CommentAddParam, CommentUpdateParam> {
    List<CommentVO> listByArticleId(String articleId);
}
