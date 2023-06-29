package net.cocotea.admin.api.system.controller;

import net.cocotea.admin.api.system.model.dto.CmsCommentAddDTO;
import net.cocotea.admin.api.system.model.dto.CmsCommentPageDTO;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.api.system.model.vo.CmsCommentVO;
import net.cocotea.admin.api.system.service.CmsCommentService;
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
    private CmsCommentService commentService;

    @Mapping("/add")
    @Post
    ApiResult<?> add(@Body CmsCommentAddDTO param) throws BusinessException {
        boolean add = commentService.add(param);
        return ApiResult.flag(add);
    }

    @Mapping("listByPage")
    @Post
    ApiResult<?> listByPage(@Body CmsCommentPageDTO param) throws BusinessException {
        Page<CmsCommentVO> list = commentService.listByPage(param);
        return ApiResult.ok(list);
    }

    @Mapping("deleteBatch")
    @Post
    ApiResult<?> listByPage(@Body List<String> idList) throws BusinessException {
        boolean b = commentService.deleteBatch(idList);
        return ApiResult.flag(b);
    }
}
