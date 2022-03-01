package com.example.demo.repository;

import com.example.demo.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerComputerRepository extends JpaRepository<Manufacturer, Integer> {
}
