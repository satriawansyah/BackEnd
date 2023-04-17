package com.satria.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satria.ecommerce.entity.PesananItem;
import com.satria.ecommerce.repository.PesananItemRepository;

@Service
public class PesananItemService {

    @Autowired
    private PesananItemRepository pesananItemRepository;
    
    public List<PesananItem> findByPesananId(String pesananId) {
        return pesananItemRepository.findByPesanan_Id(pesananId);
    }
}
