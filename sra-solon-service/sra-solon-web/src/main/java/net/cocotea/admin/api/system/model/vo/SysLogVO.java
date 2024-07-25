package net.cocotea.admin.api.system.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class SysLogVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4883072904594356224L;

    private String id;
    private String ipAddress;
    private String operator;
    private String username;
    private String nickname;

    /**
     * 请求方法
     */
    private String requestWay;

    /**
     * 接口路径
     */
    private String apiPath;

    private Integer logStatus;
    private Integer logType;
    private LocalDateTime createTime;

}
