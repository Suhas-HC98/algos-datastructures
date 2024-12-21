package com.shared.info.service;

import com.shared.info.dto.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Student> getByStudentId(Integer stdId);

    List<String> getStudentNameById(Integer stdId);

    List<Map<String, String>> getStudentPropertiesById(Integer stdId);
}
