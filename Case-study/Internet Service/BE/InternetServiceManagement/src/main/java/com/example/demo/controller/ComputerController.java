package com.example.demo.controller;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Manufacturer;
import com.example.demo.entity.Status;
import com.example.demo.entity.Type;
import com.example.demo.repository.TypeComputerRepository;
import com.example.demo.service.ComputerService;
import com.example.demo.service.ManufacturerComputerService;
import com.example.demo.service.StatusComputerService;
import com.example.demo.service.TypeComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/computer")
@CrossOrigin("http://localhost:4200")
public class ComputerController {

    @Autowired
    ComputerService computerService;

    @Autowired
    TypeComputerService typeComputerService;

    @Autowired
    StatusComputerService statusComputerService;

    @Autowired
    ManufacturerComputerService manufacturerComputerService;

    @GetMapping("/type")
    public ResponseEntity<List<Type>> listTypeComputer() {
        List<Type> typeList = typeComputerService.finAllType();
        if (typeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeList, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Status>> listStatusComputer() {
        List<Status> statusList = statusComputerService.finAll();
        if (statusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusList, HttpStatus.OK);
    }


    @GetMapping("/manufacturer")
    public ResponseEntity<List<Manufacturer>> listManufacturerComputer() {
        List<Manufacturer> manufacturerList = manufacturerComputerService.findAllManufacturer();
        if (manufacturerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(manufacturerList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Computer>> listComputer(@PageableDefault(size = 5) Pageable pageable) {
        Page<Computer> computerList = computerService.finAll(pageable);
        if (computerList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(computerList, HttpStatus.OK);
    }

    @GetMapping("/getInfor/{id}")
    public ResponseEntity<Computer> getInforComputer(@PathVariable String id) {
        Computer computer = computerService.findById(id);
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(computer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Computer> deleteComputer(@PathVariable String id) {
        Computer computer = computerService.findById(id);
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        computerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Computer>> searchComputer(@RequestParam("computerId") String id,
                                                         @RequestParam("computerLocation") String computerLocation,
                                                         @RequestParam("startUsedDateFromComputer") String startUsedDateFromComputer,
                                                         @RequestParam("startUsedDateToComputer") String startUsedDateToComputer,
                                                         @RequestParam("type") String type,
                                                         @RequestParam("status") String status, @PageableDefault(size = 5) Pageable pageable) {


        Page<Computer> computers = computerService.search(id, computerLocation, startUsedDateFromComputer, startUsedDateToComputer, type, status, pageable);
        if (computers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Computer> post(@Valid @RequestBody Computer computer, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            this.computerService.save(computer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ResponseEntity<Computer> edit(@PathVariable String id) {
        Computer computer = computerService.findById(id);
        if (computer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(computer, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Computer> update(@Valid @RequestBody Computer computer, BindingResult bindingResult, @PathVariable String id) {
        System.out.println(id);
        if (!bindingResult.hasErrors() && id != null) {
            if (computerService.findById(id) != null) {
                computerService.update(computer);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}



