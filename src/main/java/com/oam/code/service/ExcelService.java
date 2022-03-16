package com.oam.code.service;

import com.oam.code.vo.Doctor;

import java.util.List;

public interface ExcelService {

    List<Doctor> getDoctorByExcel(String path);

}
