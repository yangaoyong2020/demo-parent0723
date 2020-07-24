package com.yangaoyong.dao;


import com.yangaoyong.entity.Midd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MiddDao extends JpaRepository<Midd,Integer>, JpaSpecificationExecutor<Midd> {

    List<Midd> findBySid(Integer sid);
}
