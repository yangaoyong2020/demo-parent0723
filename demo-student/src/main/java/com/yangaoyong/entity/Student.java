package com.yangaoyong.entity;

import lombok.Data;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Trim;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Name;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Integer id;

    private  String studentname;

    private String sex;


    private LocalDate birthday;

    private  String pic;


    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDate b1;

    @Transient
     @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDate b2;


    @Transient
    private  String classname; //班级的别名 用来查询

    @Transient
    private  String hbnames;//兴趣的别名用来查询

    private  Integer cid;

    @Transient
    private List<Integer> hids;

    //MERGE级联   REFRESH刷新  DETACH不指定级联删除
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})

    @JoinColumn(name = "cid",insertable = false,updatable = false)
    private  Core cores;


    //EAGER 级饿加载
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(name = "midd",joinColumns = @JoinColumn(name = "sid"),inverseJoinColumns = @JoinColumn(name = "hid"))
    private List<Hobby> hobbys;
}
