package com.yangaoyong.service;

import com.yangaoyong.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {



    Page<Student> selectStudent(Student student,Integer page,Integer pageSize);

    void delete(Integer id);
}
