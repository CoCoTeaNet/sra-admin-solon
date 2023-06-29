package net.cocotea.admin.common.properties;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

/**
 * 默认值配置项
 *
 * @author jwss
 * @date 2022-3-30 14:12:38
 */
@Component
public class FileUploadProperties {
    /**
     * 本地位置
     */
    @Inject("${sra.file-upload.local-url}")
    private String localUrl;

    /**
     * 浏览位置
     */
    @Inject("${sra.file-upload.browser-url}")
    private String browserUrl;

    @Inject("${sra.file-upload.not-support-file-type}")
    private String notSupportFileType;

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getBrowserUrl() {
        return browserUrl;
    }

    public void setBrowserUrl(String browserUrl) {
        this.browserUrl = browserUrl;
    }

    public String getNotSupportFileType() {
        return notSupportFileType;
    }

    public FileUploadProperties setNotSupportFileType(String notSupportFileType) {
        this.notSupportFileType = notSupportFileType;
        return this;
    }
}
