package com.oam.code;

import com.alibaba.fastjson.JSONObject;
import com.oam.code.service.ExcelService;
import com.oam.code.service.QrCodeService;
import com.oam.code.utils.Util;
import com.oam.code.vo.Doctor;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CodeApplicationTests {
    @Autowired
    QrCodeService qrCodeService;
    @Autowired
    ExcelService excelService;

    @Test
    void contextLoads() {
    }

    @Test
    void qrCodeTest() throws Exception {
//        String token = qrCodeService.getToken("wxeddbe9b1de8ff09c", "91945ad945d4be120a50843f77c39d35");
        String token = "54_Y8HhB1wsg6NXkp-yqAtyF1TOTVYW-tN4bis4_L4pJnR5UFiMLMWSlYd17bS4cMgNizYlB4rb3C5RMc90nXCEKu4lxJeUVx46Yz55QlKKsnozMPz-EeZP_e2giqhwct5K1nk_Bq8ODNFCF_HOXEXjAEAJQJ";
        System.out.println(token);
//        qrCodeService.generateQrCode("D:\\qrCode\\test.png","pages/register/docinfo/index?subHisId=218&deptId=1&doctorId=418",
//                "a=1",token);

        qrCodeService.postMiniqrQr("a=1","pages/register/docinfo/index?subHisId=218&deptId=1&doctorId=418",token,"D:\\qrCode\\test.png");
//        String url = "https://api.weixin.qq.com/wxa/getwxacode?access_token="+token;
//        String requestBody = "{\"\"path\":\"pages/register/docinfo/index?subHisId=218&deptId=1&doctorId=124\",\"\"scene\":\"a=1\"}";
//        byte[] bytes = Util.sendHttpPost(url, requestBody, ContentType.APPLICATION_JSON);
//        System.out.println(Arrays.toString(bytes));
//        String s = new String(bytes);
//        System.out.println(s);
    }

    @Test
    void getToken() throws Exception{
        String token = qrCodeService.getToken("wxeddbe9b1de8ff09c", "91945ad945d4be120a50843f77c39d35");
        System.out.println(token);
    }

    @Test
    void excelTest() throws Exception{
        List<Doctor> doctorByExcel = excelService.getDoctorByExcel("D:\\his-218.xls");
        System.out.println(JSONObject.toJSONString(doctorByExcel));
    }

    @Test
    void doctorQrCode() throws Exception{

        String token = "54_xz-ejWYsQazWbPaYjdTT19Y_5XwkuAejEy4WTIbYFhzQSPqGikooM6v0gwHJIxIDkOzEDOSwNhEZy_phOphpPiYbAIRGeKV4nZ7_aF7eSYS0kY_o3DA-vA7bx2vrfbV6cqWPIlCZwdipyQGKPGWdADAWND";
        List<Doctor> doctorByExcel = excelService.getDoctorByExcel("D:\\qrCode\\his-doctor.xls");
        for (Doctor doctor : doctorByExcel) {

            if (doctor.getDeptName().contains("/")){
                String deptName = doctor.getDeptName();
                String s = deptName.replaceAll("/", "-");
                doctor.setDeptName(s);
            }

            String filePath = "D:\\qrCode\\"+KeyEnum.getFileByKey(doctor.getHisId())+"\\"+doctor.getDeptName()+"-"+doctor.getName()+".png";
            String page = "pages/register/docinfo/index?subHisId="+doctor.getHisId()+"&deptId="+doctor.getDeptCode()+"&doctorId="+doctor.getNo();
            qrCodeService.postMiniqrQr("a=1",page,token,filePath);
//            break;
        }

    }

    @Test
    void doctorQrCode2() throws Exception{

        String token = "54_xz-ejWYsQazWbPaYjdTT19Y_5XwkuAejEy4WTIbYFhzQSPqGikooM6v0gwHJIxIDkOzEDOSwNhEZy_phOphpPiYbAIRGeKV4nZ7_aF7eSYS0kY_o3DA-vA7bx2vrfbV6cqWPIlCZwdipyQGKPGWdADAWND";
        List<Doctor> doctorByExcel = excelService.getDoctorByExcel("D:\\qrCode\\his-doctor-454.xls");
        for (Doctor doctor : doctorByExcel) {

            if (doctor.getDeptName().contains("/")){
                String deptName = doctor.getDeptName();
                String s = deptName.replaceAll("/", "-");
                doctor.setDeptName(s);
            }
            String filePath = "D:\\qrCode\\"+KeyEnum.getFileByKey(doctor.getHisId())+"\\"+doctor.getDeptName()+"-"+doctor.getName()+".png";
            String page = "pages/register/docinfo/index?subHisId="+doctor.getHisId()+"&deptId="+doctor.getDeptCode()+"&doctorId="+doctor.getNo();
            qrCodeService.postMiniqrQr("a=1",page,token,filePath);
//            break;
        }

    }

    @Test
    void doctorQrCode3() throws Exception{

        String token = "54_xz-ejWYsQazWbPaYjdTT19Y_5XwkuAejEy4WTIbYFhzQSPqGikooM6v0gwHJIxIDkOzEDOSwNhEZy_phOphpPiYbAIRGeKV4nZ7_aF7eSYS0kY_o3DA-vA7bx2vrfbV6cqWPIlCZwdipyQGKPGWdADAWND";
        List<Doctor> doctorByExcel = excelService.getDoctorByExcel("D:\\qrCode\\his-doctor-456.xls");
        for (Doctor doctor : doctorByExcel) {

            if (doctor.getDeptName().contains("/")){
                String deptName = doctor.getDeptName();
                String s = deptName.replaceAll("/", "-");
                doctor.setDeptName(s);
            }
            String filePath = "D:\\qrCode\\"+KeyEnum.getFileByKey(doctor.getHisId())+"\\"+doctor.getDeptName()+"-"+doctor.getName()+".png";
            String page = "pages/register/docinfo/index?subHisId="+doctor.getHisId()+"&deptId="+doctor.getDeptCode()+"&doctorId="+doctor.getNo();
            qrCodeService.postMiniqrQr("a=1",page,token,filePath);
//            break;
        }

    }





}
