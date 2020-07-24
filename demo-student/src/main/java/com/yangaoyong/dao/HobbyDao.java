package com.yangaoyong.dao;

import com.yangaoyong.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HobbyDao extends JpaRepository<Hobby,Integer>, JpaSpecificationExecutor<Hobby> {
}
