package net.cocotea.admin.properties;

import lombok.Data;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

/**
 * 默认值配置项
 *
 * @author CoCoTea
 * @version 2.0.0
 */
@Data
@Component
public class FileProp {
    
    /**
     * 默认文件保存的位置
     */
    @Inject("${sra-admin.file.default-path}")
    private String defaultSavePath;

    /**
     * 头像保存位置
     */
    @Inject("${sra-admin.file.avatar}")
    private String avatarPath;

    /**
     * 支持上传的文件格式
     */
    @Inject("${sra-admin.file.support-filetype}")
    private String supportFiletype;

    /**
     * 媒体格式
     */
    @Inject("${sra-admin.file.media-filetype}")
    private String mediaFileType;

}
