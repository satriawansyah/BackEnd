package com.satria.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satria.ecommerce.entity.PesananItem;
import com.satria.ecommerce.service.PesananItemService;

@RestController
@RequestMapping("/api/pesananItems")
@PreAuthorize("isAuthenticated()")
public class PesananItemController {

    @Autowired
    private PesananItemService pesananItemService;

    @GetMapping("/{pesananId}")
    public ResponseEntity<List<PesananItem>> getPesananItemsByPesananId(@PathVariable String pesananId) {
        List<PesananItem> pesananItemList = pesananItemService.findByPesananId(pesananId);
        return ResponseEntity.ok(pesananItemList);
    }
}
