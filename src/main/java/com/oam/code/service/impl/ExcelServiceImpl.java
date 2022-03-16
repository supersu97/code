package com.oam.code.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.oam.code.service.ExcelService;
import com.oam.code.vo.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public List<Doctor> getDoctorByExcel(String path) {
        ExcelReader reader = ExcelUtil.getReader(path);
        List<Doctor> doctors = reader.readAll(Doctor.class);
        return doctors;
    }
}
