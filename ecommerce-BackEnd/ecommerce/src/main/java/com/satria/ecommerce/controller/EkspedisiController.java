package com.satria.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satria.ecommerce.entity.Ekspedisi;
import com.satria.ecommerce.service.EkspedisiService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class EkspedisiController {

    @Autowired
    private EkspedisiService ekspedisiService;

    @GetMapping("/ekspedisis")
    public List<Ekspedisi> findAll() {
        return ekspedisiService.findAll();
    }

    @GetMapping("/ekspedisis/{id}")
    public Ekspedisi findById(@PathVariable("id") String id) {
        return ekspedisiService.findById(id);
    }

    @PostMapping("/ekspedisis")
    public Ekspedisi create(@RequestBody Ekspedisi ekspedisi) {
        return ekspedisiService.create(ekspedisi);
    }

    @PutMapping("/ekspedisis")
    public Ekspedisi edit(@RequestBody Ekspedisi ekspedisi) {
        return ekspedisiService.edit(ekspedisi);
    }

    @DeleteMapping("/ekspedisis/{id}")
    public void deleteById(@PathVariable("id") String id) {
        ekspedisiService.deleteById(id);
    }

}
