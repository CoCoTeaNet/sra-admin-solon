package net.cocotea.admin.api.system.model.dto;

import org.noear.solon.validation.annotation.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 新增文章参数
 *
 * @author CoCoTea
 * @date 2022-7-24 16:01:22
 */
public class CmsArticleAddDTO implements Serializable {
    private static final long serialVersionUID = 1113969990552647916L;

    @NotBlank(message = "标题为空")
    private String title;

    @NotBlank(message = "内容为空")
    private String content;

    private String summary;

    private List<String> tags;

    public String getTitle() {
        return title;
    }

    public CmsArticleAddDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CmsArticleAddDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public CmsArticleAddDTO setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public CmsArticleAddDTO setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
}
