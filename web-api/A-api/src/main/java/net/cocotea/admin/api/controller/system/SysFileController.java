package net.cocotea.admin.api.controller.system;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.util.StrUtil;
import net.cocotea.admin.common.constant.GlobalValue;
import net.cocotea.admin.common.model.FileInfo;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.properties.FileUploadProperties;
import net.cocotea.admin.common.util.FileUploadUtils;
import net.cocotea.admin.service.dto.system.file.SysFileAddParam;
import net.cocotea.admin.service.dto.system.file.SysFilePageParam;
import net.cocotea.admin.service.dto.system.file.SysFileUpdateParam;
import net.cocotea.admin.service.vo.system.SysFileVO;
import net.cocotea.admin.service.core.system.ISysFileService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.UploadedFile;
import org.sagacity.sqltoy.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Mapping("/system/file")
@Controller
public class SysFileController {
    private final Logger logger = LoggerFactory.getLogger(SysFileController.class);

    @Inject
    private FileUploadProperties fileUploadProperties;

    @Inject
    private ISysFileService sysFileService;

    @Post
    @Mapping("/upload")
    public ApiResult<String> upload(@Param("file") UploadedFile uploadedFile,
                                    @Param(value = "isSave", required = false) Integer isSave) throws BusinessException {
        logger.info("api[system/upload]filename={}", uploadedFile.getName());
        // 过滤js，html，css等语言文件
        if (uploadedFile.getName() != null) {
            String[] split = uploadedFile.getName().split("\\.");
            String fileType = split[split.length - 1];
            logger.info("api[system/upload]fileType={}", fileType);
            if (StrUtil.isBlank(fileType)) {
                throw new BusinessException("未知文件格式");
            } else {
                boolean flag = fileUploadProperties.getNotSupportFileType().contains(fileType);
                if (flag) {
                    throw new BusinessException("该文件格式不支持上传");
                }
            }
        } else {
            throw new BusinessException("文件名为空.");
        }
        FileInfo fileInfo = FileUploadUtils.saveMultipartFile(uploadedFile, fileUploadProperties.getLocalUrl());
        String browsePath = GlobalValue.getServerUrl() + fileUploadProperties.getBrowserUrl() + fileInfo.getFileBasePath();
        if (isSave != null && isSave == 1) {
            SysFileAddParam param = new SysFileAddParam()
                    .setFileName(fileInfo.getFileName())
                    .setFileSuffix(fileInfo.getFileSuffix())
                    .setFileSize(fileInfo.getFileSize())
                    .setBrowsePath(fileInfo.getFileBasePath())
                    .setRealPath(fileInfo.getRealPath())
                    .setFullPath(browsePath);
            sysFileService.add(param);
        }
        return ApiResult.ok(browsePath);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/deleteBatch")
    public ApiResult<?> deleteBatch(@Body List<String> param) throws BusinessException {
        boolean b = sysFileService.deleteBatch(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/update")
    public ApiResult<?> update(@Body SysFileUpdateParam param) throws BusinessException {
        boolean b = sysFileService.update(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/listByPage")
    public ApiResult<?> listByPage(@Body SysFilePageParam param) throws BusinessException {
        Page<SysFileVO> r = sysFileService.listByPage(param);
        return ApiResult.ok(r);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/recycleBinPage")
    public ApiResult<?> recycleBinPage(@Body SysFilePageParam param) throws BusinessException {
        Page<SysFileVO> r = sysFileService.recycleBinPage(param);
        return ApiResult.ok(r);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/recycleBin/deleteBatch")
    public ApiResult<?> recycleBinDeleteBatch(@Body List<String> param) throws BusinessException {
        return ApiResult.flag(sysFileService.recycleBinDeleteBatch(param));
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/recycleBin/recoveryBatch")
    public ApiResult<?> recoveryBatch(@Body List<String> param) throws BusinessException {
        return ApiResult.flag(sysFileService.recoveryBatch(param));
    }

}
