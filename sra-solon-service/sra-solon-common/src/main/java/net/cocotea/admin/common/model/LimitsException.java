package net.cocotea.admin.common.model;

import net.cocotea.admin.common.enums.ApiResultEnum;

import java.io.Serializable;

/**
 * 访问过快限制异常
 *
 * @author CoCoTea
 */
public class LimitsException extends Exception implements Serializable {
    private static final long serialVersionUID = -1;

    /**
     * 异常编号
     */
    private Integer errorCode;

    /**
     * 异常信息
     */
    private String errorMsg;

    public LimitsException(String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = ApiResultEnum.ERROR.getCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}