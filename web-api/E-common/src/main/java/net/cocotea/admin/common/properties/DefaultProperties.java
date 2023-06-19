package net.cocotea.admin.common.properties;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

/**
 * 默认值配置项
 *
 * @author CoCotea
 * @date 2022-3-30 14:12:38
 */
@Component
public class DefaultProperties {
    /**
     * 默认密码
     */
    @Inject("${sra.default.password}")
    private String password;

    /**
     * 密码加密的盐
     */
    @Inject("${sra.default.salt}")
    private String salt;

    /**
     * 数据库名称
     */
    @Inject("${sra.default.dbName}")
    private String dbName;

    /**
     * 请求协议
     */
    @Inject("${sra.default.agreement}")
    private String agreement;

    /**
     * 域名 | IP
     */
    @Inject("${sra.default.domain}")
    private String domain;

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
