package net.cocotea.admin.cms.controller;

import net.cocotea.admin.cms.param.comment.CommentAddParam;
import net.cocotea.admin.cms.param.comment.CommentPageParam;
import net.cocotea.admin.cms.service.ICommentService;
import net.cocotea.admin.cms.vo.CommentVo;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * 评论接口
 *
 * @date 2022-8-21 03:34:25
 * @author CoCoTea
 */
@Controller
@Mapping("/cms/comment")
public class CommentController {
    @Inject
    private ICommentService commentService;

    @Mapping("/add")
    @Post
    ApiResult<?> add(@Body CommentAddParam param) throws BusinessException {
        boolean add = commentService.add(param);
        return ApiResult.flag(add);
    }

    @Mapping("listByPage")
    @Post
    ApiResult<?> listByPage(@Body CommentPageParam param) throws BusinessException {
        Page<CommentVo> list = commentService.listByPage(param);
        return ApiResult.ok(list);
    }

    @Mapping("deleteBatch")
    @Post
    ApiResult<?> listByPage(@Body List<String> idList) throws BusinessException {
        boolean b = commentService.deleteBatch(idList);
        return ApiResult.flag(b);
    }
}
