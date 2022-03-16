package com.oam.code.service;

public interface QrCodeService {

    public String getToken(String appId, String appKey) throws Exception;

    public void generateQrCode(String filePath,String page, String scene, String accessToken);

    public void postMiniqrQr(String scene, String page ,String accessToken,String filePath);
}
