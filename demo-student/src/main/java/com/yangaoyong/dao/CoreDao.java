package com.yangaoyong.dao;

import com.yangaoyong.entity.Core;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoreDao extends JpaRepository<Core,Integer>, JpaSpecificationExecutor<Core> {
}
