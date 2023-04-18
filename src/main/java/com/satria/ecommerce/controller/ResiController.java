package com.satria.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satria.ecommerce.entity.Resi;
import com.satria.ecommerce.service.ResiService;

@RestController
@RequestMapping("//api")
@PreAuthorize("isAuthenticated()")
public class ResiController {

    @Autowired
    private ResiService resiService;

    @GetMapping("/resis")
    public List<Resi> findAll() {
        return resiService.findAll();
    }

    @GetMapping("/resis/{pesananId}")
    public ResponseEntity<List<Resi>> getResiByPesananId(@PathVariable String pesananId) {
        List<Resi> resiList = resiService.findByPesananId(pesananId);
        return ResponseEntity.ok(resiList);
    }

    @PostMapping("/resis")
    public Resi create(@RequestBody Resi resi) {
        return resiService.create(resi);
    }

    @PutMapping("/resis")
    public Resi edit(@RequestBody Resi resi) {
        return resiService.edit(resi);
    }

    @DeleteMapping("/resis/{id}")
    public void deleteById(@PathVariable("id") String id) {
        resiService.deleteById(id);
    }

}
