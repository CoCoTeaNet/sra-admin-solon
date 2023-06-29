package net.cocotea.admin.api.system.service.impl;

import net.cocotea.admin.api.system.model.dto.CmsCommentAddDTO;
import net.cocotea.admin.api.system.model.dto.CmsCommentPageDTO;
import net.cocotea.admin.api.system.model.dto.CmsCommentUpdateDTO;
import net.cocotea.admin.api.system.model.po.CmsComment;
import net.cocotea.admin.api.system.model.vo.CmsCommentVO;
import net.cocotea.admin.api.system.service.CmsCommentService;
import net.cocotea.admin.common.enums.DeleteStatusEnum;
import net.cocotea.admin.common.enums.ReplyTypeEnum;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CmsCommentServiceImpl implements CmsCommentService {
    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(CmsCommentAddDTO param) throws BusinessException {
        CmsComment comment = new CmsComment();
        comment.setParentId(String.valueOf(0))
                .setArticleId(param.getArticleId())
                .setDeleteStatus(DeleteStatusEnum.NOT_DELETE.getCode())
                .setCreateTime(LocalDateTime.now())
                .setContent(param.getContent())
                .setCreateBy(param.getEmail())
                .setReplyType(ReplyTypeEnum.ARTICLE.getCode());
        return sqlToyLazyDao.save(comment) != null;
    }

    @Override
    public boolean deleteBatch(List<String> idList) throws BusinessException {
        List<CmsComment> comments = new ArrayList<>(idList.size());
        idList.forEach(item -> comments.add(new CmsComment().setId(item).setDeleteStatus(DeleteStatusEnum.DELETE.getCode())));
        Long all = sqlToyLazyDao.updateAll(comments);
        return all > 0;
    }

    @Override
    public boolean update(CmsCommentUpdateDTO param) throws BusinessException {
        return false;
    }

    @Override
    public Page<CmsCommentVO> listByPage(CmsCommentPageDTO param) throws BusinessException {
        Page<CmsCommentVO> page = sqlToyLazyDao.findPageBySql(param, "cms_comment_findByPageParam", param.getComment());
        return page;
    }

    @Override
    public boolean delete(String id) throws BusinessException {
        return false;
    }

    @Override
    public List<CmsCommentVO> listByArticleId(String articleId) {
        List<CmsCommentVO> list = sqlToyLazyDao.findBySql("cms_comment_findByEntityParam", new CmsCommentVO().setArticleId(articleId));
        return list;
    }
}
