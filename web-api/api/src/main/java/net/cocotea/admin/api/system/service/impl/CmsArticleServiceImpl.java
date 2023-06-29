package net.cocotea.admin.api.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import net.cocotea.admin.api.system.model.dto.CmsArticleAddDTO;
import net.cocotea.admin.api.system.model.dto.CmsArticlePageDTO;
import net.cocotea.admin.api.system.model.dto.CmsArticleUpdateDTO;
import net.cocotea.admin.api.system.model.po.CmsArticle;
import net.cocotea.admin.api.system.model.vo.CmsArchiveVO;
import net.cocotea.admin.api.system.model.vo.CmsArticleVO;
import net.cocotea.admin.api.system.model.vo.CmsTagVO;
import net.cocotea.admin.api.system.service.CmsArticleService;
import net.cocotea.admin.common.enums.DeleteStatusEnum;
import net.cocotea.admin.common.enums.PublishStatusEnum;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoCoTea
 */
@Service
public class CmsArticleServiceImpl implements CmsArticleService {
    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Tran
    @Override
    public boolean add(CmsArticleAddDTO param) throws BusinessException {
        CmsArticle article = Convert.convert(CmsArticle.class, param);
        article.setPublishStatus(PublishStatusEnum.NORMAL.getCode());
        Object save = sqlToyLazyDao.save(article);
        return StrUtil.isNotBlank(save.toString());
    }

    @Override
    public boolean deleteBatch(List<String> idList) throws BusinessException {
        List<CmsArticle> articleList = new ArrayList<>();
        for (String id : idList) {
            CmsArticle article = new CmsArticle();
            article.setId(id);
            article.setDeleteStatus(DeleteStatusEnum.DELETE.getCode());
            articleList.add(article);
        }
        Long updateAll = sqlToyLazyDao.updateAll(articleList);
        return updateAll > 0;
    }

    @Tran
    @Override
    public boolean update(CmsArticleUpdateDTO param) throws BusinessException {
        CmsArticle article = Convert.convert(CmsArticle.class, param);
        Long update = sqlToyLazyDao.update(article);
        return update > 0;
    }

    @Override
    public Page<CmsArticleVO> listByPage(CmsArticlePageDTO param) throws BusinessException {
        Page<CmsArticleVO> page = sqlToyLazyDao.findPageBySql(param, "cms_article_findByPageParam", param.getArticle());
        page.getRows().forEach(articleVo -> {
            JSONArray parseArray = JSONUtil.parseArray(articleVo.getTags());
            articleVo.setTagList(parseArray.toList(String.class));
        });
        return page;
    }

    @Override
    public boolean delete(String id) throws BusinessException {
        CmsArticle article = new CmsArticle();
        article.setId(id);
        article.setDeleteStatus(DeleteStatusEnum.DELETE.getCode());
        Long update = sqlToyLazyDao.update(article);
        return update > 0;
    }

    @Override
    public CmsArticleVO detail(String id) {
        CmsArticleVO sysArticleVo = sqlToyLazyDao.loadBySql("cms_article_findByEntityParam", new CmsArticleVO().setId(id));
        JSONArray parseArray = JSONUtil.parseArray(sysArticleVo.getTags());
        sysArticleVo.setTagList(parseArray.toList(String.class));
        return sysArticleVo;
    }

    @Override
    public List<CmsTagVO> findTags(List<CmsArticleVO> sysArticleVOList) {
        List<String> tags = new ArrayList<>();
        final String[] colors = {"bg-primary", "bg-secondary", "bg-success", "bg-danger", "bg-warning text-dark", "bg-info text-dark", "bg-light text-dark", "bg-dark"};
        if (sysArticleVOList == null) sysArticleVOList = findByTimeDesc();
        sysArticleVOList.forEach(item -> {
            JSONArray array = JSONUtil.parseArray(item.getTags());
            tags.addAll(array.toList(String.class));
        });
        List<CmsTagVO> vos = new ArrayList<>();
        tags.stream().distinct().forEach(tag -> vos.add(new CmsTagVO().setTagName(tag).setColor(colors[RandomUtil.randomInt(colors.length - 1)])));
        return vos;
    }

    @Override
    public List<CmsArticleVO> findByTimeDesc() {
        String sql = """
                select ID, TITLE, TAGS, COVER, SUMMARY, CREATE_TIME
                from cms_article
                 where DELETE_STATUS = 1
                order by CREATE_TIME desc, UPDATE_TIME desc limit 15
                """;
        List<CmsArticleVO> list = sqlToyLazyDao.findBySql(sql, new CmsArticleVO());
        list.forEach(articleVo -> {
            if (StrUtil.isBlank(articleVo.getCover())) {
                articleVo.setCover("/default/default-cover.jpg");
            }
        });
        return list;
    }

    @Override
    public List<CmsArchiveVO> findByArchiveList() {
        List<CmsArchiveVO> cmsArchiveVOS = sqlToyLazyDao.findBySql("cms_article_findByArchiveList", new CmsArchiveVO());
        return cmsArchiveVOS;
    }
}
