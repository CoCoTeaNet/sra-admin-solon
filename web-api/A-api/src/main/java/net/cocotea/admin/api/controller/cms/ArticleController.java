package net.cocotea.admin.api.controller.cms;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.service.dto.cms.article.ArticleAddParam;
import net.cocotea.admin.service.dto.cms.article.ArticlePageParam;
import net.cocotea.admin.service.dto.cms.article.ArticleUpdateParam;
import net.cocotea.admin.service.vo.cms.ArticleVO;
import net.cocotea.admin.service.core.cms.IArticleService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * 文章接口
 *
 * @date 2022-7-24 16:02:26
 * @author CoCoTea
 */
@Controller
@Mapping("/cms/article")
public class ArticleController {
    @Inject
    private IArticleService articleService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/add")
    public ApiResult<?> add(@Body ArticleAddParam param) throws BusinessException {
        boolean add = articleService.add(param);
        return ApiResult.ok(add);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/deleteBatch")
    public ApiResult<?> delete(@Body List<String> ids) throws BusinessException {
        boolean delete = articleService.deleteBatch(ids);
        return ApiResult.ok(delete);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/update")
    public ApiResult<?> update(@Body ArticleUpdateParam param) throws BusinessException {
        boolean update = articleService.update(param);
        return ApiResult.ok(update);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/listByPage")
    public ApiResult<?> listByPage(@Body ArticlePageParam param) throws BusinessException {
        Page<ArticleVO> list = articleService.listByPage(param);
        return ApiResult.ok(list);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/detail/{articleId}")
    public ApiResult<?> detail(@Path("articleId") String articleId) {
        ArticleVO article = articleService.detail(articleId);
        return ApiResult.ok(article);
    }
}
