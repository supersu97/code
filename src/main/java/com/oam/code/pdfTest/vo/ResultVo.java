package com.oam.code.pdfTest.vo;

public class ResultVo {


    private String code;
    private String fileAddress;
    private String fssAddress;
    private String errMsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getFssAddress() {
        return fssAddress;
    }

    public void setFssAddress(String fssAddress) {
        this.fssAddress = fssAddress;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
