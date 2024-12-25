package com.shared.info.service.impl;

import com.shared.info.dto.Student;
import com.shared.info.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private static final List<Student> studentList = List.of(
            Student.builder()
                    .stdId(1)
                    .stdName("A")
                    .stdPhone(1L)
                    .stdPoints(1)
                    .stdRollNumber("101")
                    .additionalProperties(Map.of("computerSkills", "Y",
                            "sports", "N"))
                    .build(),
            Student.builder()
                    .stdId(2)
                    .stdName("B")
                    .stdPhone(2L)
                    .stdPoints(2)
                    .stdRollNumber("102")
                    .additionalProperties(Map.of("computerSkills", "N",
                            "sports", "Y"))
                    .build(),
            Student.builder()
                    .stdId(3)
                    .stdName("C")
                    .stdPhone(3L)
                    .stdPoints(3)
                    .stdRollNumber("103")
                    .additionalProperties(Map.of("computerSkills", "Y",
                            "sports", "Y"))
                    .build()
    );

    @Override
    public List<Student> getByStudentId(Integer stdId) {
        log.info("fetching student details for Id {}", stdId);
        return studentList.stream()
                .filter(student -> student.getStdId().equals(stdId))
                .toList();
    }

    @Override
    public List<String> getStudentNameById(Integer stdId) {
        log.info("fetching student name by student Id {}", stdId);
        return studentList.stream().filter(student -> student.getStdId().equals(stdId))
                .map(Student::getStdName).toList();
    }

    @Override
    public List<Map<String, String>> getStudentPropertiesById(Integer stdId) {
        log.info("fetching student's additional properties by student Id {}", stdId);
        return studentList.stream().filter(student -> student.getStdId().equals(stdId))
                .map(Student::getAdditionalProperties).toList();
    }
}