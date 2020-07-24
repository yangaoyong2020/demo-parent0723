package com.yangaoyong.dao;

import com.yangaoyong.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentDao extends JpaRepository<Student,Integer>, JpaSpecificationExecutor<Student> {


    //三级联动
   // List<Student> findByPid(Integer pid);

    //根据id查询
    //@Transactional
    //List<Integer> findBySid(Integer sid);

    Student getById(Integer id);

}
