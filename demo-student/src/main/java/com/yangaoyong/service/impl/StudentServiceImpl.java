package com.yangaoyong.service.impl;

import com.yangaoyong.dao.StudentDao;
import com.yangaoyong.entity.Student;
import com.yangaoyong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl  implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Page<Student> selectStudent(Student student, Integer page, Integer pageSize) {
        //模糊查询
        Specification<Student>  specification = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate>  list = new ArrayList<>();
                if(student.getStudentname()!=null){
                    Predicate like = cb.like(root.get("studentname"), "%".concat(student.getStudentname()).concat("%"));
                    list.add(like);
                }
                if(student.getClassname()!=null){
                    //Predicate equal22 = cb.equal(root.get("cid"),student.getCid());
                    Predicate equal = cb.equal(root.join("cores").get("corename"), student.getClassname());
                    list.add(equal);
                }
                if(student.getHbnames()!=null){
                    Predicate like1 = cb.like(root.join("hobbys").get("hobbyname"), "%".concat(student.getHbnames()).concat("%"));
                    list.add(like1);
                }
                if(student.getB1()!=null){
                    Predicate birthday1 = cb.greaterThanOrEqualTo(root.get("birthday"), student.getB1());
                    list.add(birthday1);
                }
                if(student.getB2()!=null){
                    Predicate birthday2 = cb.lessThanOrEqualTo(root.get("birthday"), student.getB2());
                    list.add(birthday2);
                }

                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return studentDao.findAll(specification,pageRequest);
    }

    @Override
    public void delete(Integer id) {
         studentDao.deleteById(id);
    }
}
