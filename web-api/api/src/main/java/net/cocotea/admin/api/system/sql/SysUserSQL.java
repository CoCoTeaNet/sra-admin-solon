package net.cocotea.admin.api.system.sql;

/**
 * 系统用户SQL
 */
public class SysUserSQL {

    /**
     * 通过用户主键ID获取用户名称
     */
    public static final String selectUserName =
            """
            select USERNAME from sys_user where DELETE_STATUS = 1 and ID = :id
            """;

}
