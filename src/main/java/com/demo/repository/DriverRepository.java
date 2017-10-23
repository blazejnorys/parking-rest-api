package com.demo.repository;

import com.demo.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverRepository extends JpaRepository<Driver, Long> {

}
