package com.oam.code.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.oam.code.pdfTest.util.FileUtils;
import com.oam.code.pdfTest.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;
 
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
 
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
 
/**
 * 基于freemarker生成pdf<br/>
 * Author:杨杰超<br/>
 * Date:2019年11月15日 下午3:33:07 <br/>
 * Copyright (c) 2019, yangjiechao@dingtalk.com All Rights Reserved.<br/>
 *
 */
public class GenPdfTest {
 
    /**
     * 生成测试数据
     */
    private static Map<String, Object> newData() {
        Map<String, Object> data = new HashMap<String, Object>();
        // 存入一个集合
        List<Object> cureseList = new ArrayList<Object>();
        cureseList.add(newCureseData("▲公共必修课1", "数理统计", "1/春", "2.5", "85"));
        cureseList.add(newCureseData("▲公共必修课2", "数理统计", "1/春", "2.5", "85"));
        cureseList.add(newCureseData("▲公共必修课3", "数理统计", "1/春", "2.5", "85"));
        cureseList.add(newCureseData("▲公共必修课4", "数理统计", "1/春", "2.5", "85"));
        cureseList.add(newCureseData("▲公共必修课5", "数理统计", "1/春", "2.5", "85"));
        // 课程加权平均成绩
        String avgAchievement = "-";
        String stuName = "Jc";
        String stuNo = "1111111";
        String stuGrade = "2019";
        String stuType = "研究生";
        String stuCollege = "软件学院";
        String stuTeacher = "何XX";
        String stuMajor = "计算机科学与技术";
 
        data.put("courseList", cureseList);
        data.put("avgAchievement", avgAchievement);
        data.put("stuName", stuName);
        data.put("stuNo", stuNo);
        data.put("stuGrade", stuGrade);
        data.put("stuType", stuType);
        data.put("stuCollege", stuCollege);
        data.put("stuTeacher", stuTeacher);
        data.put("stuMajor", stuMajor);
 
        return data;
    }
 
    /**
     * 生成测试数据
     * 
     * @param type
     * @param name
     * @param semester
     * @param credit
     * @param achievement
     * @return
     */
    private static Map<String, Object> newCureseData(String type, String name, String semester, String credit,
            String achievement) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("name", name);
        map.put("semester", semester);
        map.put("credit", credit);
        map.put("achievement", achievement);
        return map;
    }
 
    public static void main(String[] args) throws Exception {
 
        Map<String, Object> data = newData();

//        // 模板名称
        String ftlName = "print.ftl";
//        // 根路径
//        String path = GenPdfTest.class.getResource("/").getPath().substring(1);
////        // 图片路径
//        String imageDiskPath = path + "imgs/";
////        // 文件生成全路径
//        String outputFile = "D:\\pdftest.pdf";
////
//        generateToFile(path, ftlName, imageDiskPath, data, outputFile);
        String fileName = "test.pdf";
        ResultVo resultVo = FileUtils.generateToFile(ftlName, data, fileName);
        System.out.println(JSONObject.toJSONString(resultVo));
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
    public static Template generateTemplate(String ftlPath, String ftlName) throws IOException {
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
    public static ITextRenderer generateITextRenderer(String path) throws DocumentException, IOException {
        ITextRenderer render = new ITextRenderer();
        // 添加字体，以支持中文
        render.getFontResolver().addFont(path + "fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        render.getFontResolver().addFont(path + "fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
 
        return render;
    }
 
    /**
     * 生成PDF到文件
     * 
     * @param path
     *            根路径
     * @param ftlName
     *            模板文件吗（不含路径）
     * @param imageDiskPath
     *            图片的磁盘路径
     * @param data
     *            数据
     * @param outputFile
     *            目标文件（全路径名称）
     * @throws Exception
     */
    public static void generateToFile(String path, String ftlName, String imageDiskPath, Object data, String outputFile)
            throws Exception {
 
        Template tpl = generateTemplate(path, ftlName);
 
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        String html = writer.toString();
 
        ITextRenderer render = generateITextRenderer(path);
 
        OutputStream out = new FileOutputStream(outputFile);
        render.setDocumentFromString(html);
        if (imageDiskPath != null && !"".equals(imageDiskPath)) {
            render.getSharedContext().setBaseURL("file:/" + imageDiskPath);
        }
        render.layout();
        render.createPDF(out);
        render.finishPDF();
        render = null;
        out.close();
    }
}