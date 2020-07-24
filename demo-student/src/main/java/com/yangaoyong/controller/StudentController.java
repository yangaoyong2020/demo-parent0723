package com.yangaoyong.controller;

import com.yangaoyong.dao.CoreDao;
import com.yangaoyong.dao.HobbyDao;
import com.yangaoyong.dao.MiddDao;
import com.yangaoyong.dao.StudentDao;
import com.yangaoyong.entity.Core;
import com.yangaoyong.entity.Hobby;
import com.yangaoyong.entity.Midd;
import com.yangaoyong.entity.Student;
import com.yangaoyong.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Api(value = "没有意义",tags = {"学生","管理"})
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CoreDao coreDao;

    @Autowired
    private HobbyDao hobbyDao;

    @Autowired
    private MiddDao middDao;


    @ApiOperation(value = "获取学生的列表数据",notes = "获取学生列表。",
    response = Page.class,httpMethod="GET")
    @RequestMapping("/selectStudent")
    @ApiResponse(code = 200,message="返回的MyPageImpl对象",response=Page.class)
    public Page<Student> selectStudent(Student student,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "3")Integer pageSize){
        Page<Student> students = studentService.selectStudent(student, page, pageSize);

        return students;
    }

    @RequestMapping("/delete")
    public boolean delete(Integer id){
         studentService.delete(id);
         return true;
    }

    @RequestMapping("/save")
    public boolean save(@RequestBody Student student){
        Student s = studentDao.save(student);
        List<Integer> hids = student.getHids();
        for(Integer hid:hids){
            Midd midd = new Midd();
            midd.setSid(s.getId());
            midd.setHid(hid);
            middDao.save(midd);
        }
        return true;
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file){
        try {
            if(file!=null){
                if(!file.isEmpty()){
                    String originalFilename = file.getOriginalFilename();
                    String fileName=UUID.randomUUID()+"_"+originalFilename;
                    File dest = new File("D://imgs//",fileName);
                    if(!dest.exists()){
                        dest.mkdirs();
                    }
                    file.transferTo(dest);
                    return "http://localhost:2000/imgs/"+fileName ;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/listcore")
    public List<Core> listcore(){
        return coreDao.findAll();
    }

    @RequestMapping("/listhobby")
    public List<Hobby> listhobby(){
        return hobbyDao.findAll();
    }


    @GetMapping("/listsid")
    public Student listsid(Integer id){
        //根据学生的sid查询
        Student one = studentDao.getById(id);
        //根据学生的id去中间表查询 获取这条数据的兴趣id
        List<Midd> bySid = middDao.findBySid(id);
        List<Integer> list = new ArrayList<>();
        for( Midd  sids:bySid){
            list.add(sids.getHid());
        }
        one.setHids(list);
        return one;
    }
}
