package com.oam.code.pdfTest.util;

import com.alibaba.fastjson.JSONObject;
import com.gzhc365.fss.client.FSS;
import com.gzhc365.fss.client.builder.UploadRequestBuilder;
import com.gzhc365.fss.client.impl.FSSClient;
import com.gzhc365.fss.client.other.FssClientConfig;
import com.gzhc365.fss.common.vo.UploadResponse;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.oam.code.pdfTest.vo.FileConfig;
import com.oam.code.pdfTest.vo.ResultVo;
import com.oam.code.utils.GenPdfTest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Locale;
import java.util.Map;

@Component
public class FileUtils {
//    @Value("${file.ftl.path:'/ftl'}")
    private static String ftlPath = "ftl/";
//    @Value("${file.img.path}:'/img'")
    private static String imgPath = "imgs/";
//    @Value("${file.path:'/files'}")
    private static String filePath = "files/";



    /**
     * 生成PDF到文件
     *
     * @param path
     *            根路径
     * @param ftlPath
     *            模板路径
     * @param ftlName
     *            模板文件名（不含路径）
     * @param imgPath
     *            图片的磁盘路径
     * @param data
     *            数据
     * @param filePath
     *            目标文件（全路径名称）
     * @param fileName
     *            文件名
     * @throws Exception
     */
    public static ResultVo generateToFile(String path,String ftlPath,String ftlName, String imgPath, Map<String,Object> data,String filePath,String fileName) throws Exception{

        ResultVo result = new ResultVo();
        ftlPath = path+ftlPath;
        imgPath = path+imgPath;
        Template tpl = generateTemplate(ftlPath, ftlName);

        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        String html = writer.toString();

        ITextRenderer render = generateITextRenderer(path);
        OutputStream out = null;
        try {
            File file = new File(filePath);
            if  (!file .exists()  && !file .isDirectory()) {
                file.mkdir();
            }
            filePath = filePath+fileName;
            out = new FileOutputStream(filePath);
            render.setDocumentFromString(html);
            if (imgPath != null && !"".equals(imgPath)) {
                render.getSharedContext().setBaseURL("file:/" + imgPath);
            }
            render.layout();
            render.createPDF(out);
            render.finishPDF();
            render = null;

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode("1");
            result.setErrMsg(e.getMessage());
            return result;
        } finally {
            if (out!=null) {
                out.close();
            }
        }
        result.setCode("0");
        result.setFileAddress(filePath);
        return result;
    };


    public static ResultVo generateToFile(String ftlName,Map<String,Object> data,String fileName) throws Exception{
        // 根路径
        String path = GenPdfTest.class.getResource("/").getPath().substring(1);
        String outputFile = path+filePath;
        return generateToFile(path,ftlPath,ftlName,imgPath,data,outputFile,fileName);
    }


    /**
     * 获取freemarker模板对象
     *
     * @param ftlPath
     *            FTL模板路径
     * @param ftlName
     *            FTL模板名称
     * @return
     * @throws IOException
     */
    private static Template generateTemplate(String ftlPath, String ftlName) throws IOException {
        Configuration config = new Configuration(new Version("2.3.22"));
        config.setDirectoryForTemplateLoading(new File(ftlPath));
        config.setEncoding(Locale.CHINA, "UTF-8");

        Template template = config.getTemplate(ftlName);
        return template;
    }


    /**
     * 获取ITextRenderer渲染器
     *
     * @param path
     *            根路径
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private static ITextRenderer generateITextRenderer(String path) throws DocumentException, IOException {
        ITextRenderer render = new ITextRenderer();
        // 添加字体，以支持中文
        render.getFontResolver().addFont(path + "fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        render.getFontResolver().addFont(path + "fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        return render;
    }


    //上传文件
    private UploadResponse uploadFile(FileConfig fileConfig,UploadRequestBuilder builder, byte[] bytes){
        ResultVo result = new ResultVo();
        FssClientConfig clientConfig = new FssClientConfig();
        // 密钥对
        clientConfig.setAppKey(fileConfig.getAppKey());
        clientConfig.setAppSecret(fileConfig.getAppSecret());
        // 文件服务中心地址(即web-fss访问地址)
        clientConfig.setFssCenterUrl(fileConfig.getFssUrl());

        // 初始化文件上传客户端
        FSSClient fssClient = new FSSClient(clientConfig);

        // 构建文件上传参数
//        UploadRequestBuilder builder = UploadRequestBuilder
//                .newBuilder(fileConfig.getFileName(), bytes);

//        // 其他可选参数
//        builder.serviceType(fileConfig.getServiceType())// 设定上传文件业务代码
//                .expiredSeconds(7200)     // 设定文件过期时间为7200秒，也就是2小时 (不设置默认是永久)
//                .ofPrivate(600)           // 设置为私有文件，且临时url过期时间为600秒 (不设置默认是3600秒)
//                .compressByScale(0.6)     // 0.6比例缩放
//                .cropPic(100, 100);       // 100 x 100 剪切

        // 文件上传
        UploadResponse uploadResponse = fssClient.uploadFile(builder);

        //System.out.println(null != uploadResponse ? JSONObject.toJSONString(uploadResponse) : "");
        return uploadResponse;
    }

    //根据token获取文件信息




}
