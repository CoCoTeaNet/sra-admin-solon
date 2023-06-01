package net.cocotea.admin.cms.controller;

import net.cocotea.admin.cms.service.IArticleService;
import net.cocotea.admin.cms.service.ICommentService;
import org.noear.solon.annotation.*;

/**
 * @author CoCoTea
 */
@Mapping("/cmsPage")
@Controller
public class PageController {
    @Inject
    private IArticleService articleService;
    @Inject
    private ICommentService commentService;

    // @Get
    // @Mapping("/index")
    // public String index(ModelMap modelMap) {
    //     // 获取文章列表（时间倒叙、评论数最多）
    //     List<ArticleVo> articleServiceByTimeDesc = articleService.findByTimeDesc();
    //     // 归档列表
    //     List<ArchiveVo> archiveVoList = articleService.findByArchiveList();
    //     // 获取标签列表
    //     List<TagVo> tags = articleService.findTags(articleServiceByTimeDesc);
    //     modelMap.addAttribute("articleServiceByTimeDesc", articleServiceByTimeDesc);
    //     modelMap.addAttribute("archiveVoList", archiveVoList);
    //     modelMap.addAttribute("tags", tags);
    //     modelMap.addAttribute("title", "首页");
    //     return "index";
    // }
    //
    // @Get
    // @Mapping("/p/{articleId}")
    // public String detail(@Path("articleId") String articleId, ModelMap modelMap) {
    //     ArticleVo article = articleService.detail(articleId);
    //     modelMap.addAttribute("article", article);
    //     modelMap.addAttribute("archiveVoList", articleService.findByArchiveList());
    //     modelMap.addAttribute("tags", articleService.findTags(null));
    //     modelMap.addAttribute("articleCommentVoList", commentService.listByArticleId(articleId));
    //     modelMap.addAttribute("title", article.getTitle());
    //     return "detail";
    // }
}
