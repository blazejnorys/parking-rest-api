package com.demo.repository;

import com.demo.model.ParkingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface ParkingEventRepository extends JpaRepository<ParkingEvent, Long> {

    @Query("SELECT SUM(paymentPln) FROM ParkingEvent")
    BigDecimal getPaymentSum();

    @Query("SELECT SUM(paymentPln) FROM ParkingEvent WHERE paymentDate BETWEEN :startDate AND :endDate")
    BigDecimal getPaymentSumByDate(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);


}
