package com.wittybrains.busbookingsystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.wittybrains.busbookingsystem.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
   
}
