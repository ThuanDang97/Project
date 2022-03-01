package com.example.demo.controller;

import com.example.demo.entity.Service;
import com.example.demo.service.ServiceService;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/service")
public class ServiceController {
   @Autowired
    private ServiceService serviceService;

    @PostMapping("/create")
    public ResponseEntity<Service> post(@Validated @RequestBody Service service, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors() || this.serviceService.findById(service.getServiceId()) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            this.serviceService.save(service);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Service> update(@Valid @RequestBody Service service,
                                          BindingResult bindingResult,
                                          @PathVariable String id) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            this.serviceService.save(service);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Service>> listAllService(@PageableDefault(size = 5) Pageable pageable) {
        Page<Service> serviceList = serviceService.findAllService(pageable);
        if (serviceList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(serviceList, HttpStatus.OK);
    }

    @GetMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Service> getService(@PathVariable("id") String serviceId) {
        System.out.println("Fetching Service with serviceId" + serviceId);
        Service service = serviceService.findServiceById(serviceId);
        if (service == null) {
            System.out.println("Service with serviceId" + serviceId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Service> deleteService(@PathVariable("id") String serviceId) {
        System.out.println("Fetching and Deleting Service with service id" + serviceId);
        Service service = serviceService.findServiceById(serviceId);

        if (service == null) {
            System.out.println("Unable to delete. Service with serviceId" + serviceId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        serviceService.deleteService(serviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<Service> deleteAllService() {
        serviceService.deleteAllService();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/search")
    public ResponseEntity<Page<Service>> searchService(@RequestParam("searchName") String searchName,
                                                       @PageableDefault(size = 5) Pageable pageable) {
        Page<Service> searchService = serviceService.search(pageable, searchName);
        System.out.println(searchService);
        System.out.println("abdc");
        if (searchService.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(searchService,HttpStatus.OK);
    }
}
