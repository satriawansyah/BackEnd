package com.satria.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satria.ecommerce.entity.Ekspedisi;
import com.satria.ecommerce.exception.ResourceNotFoundException;
import com.satria.ecommerce.repository.EkspedisiRepository;

@Service
public class EkspedisiService {

    @Autowired
    private EkspedisiRepository ekspedisiRepository;

    public Ekspedisi findById(String id) {
        return ekspedisiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ekspedisi dengan id " + id + " tidak ditemukan"));
    }

    public List<Ekspedisi> findAll() {
        return ekspedisiRepository.findAll();
    }

    public Ekspedisi create(Ekspedisi ekspedisi) {
        ekspedisi.setId(UUID.randomUUID().toString());
        return ekspedisiRepository.save(ekspedisi);
    }

    public Ekspedisi edit(Ekspedisi ekspedisi) {
        return ekspedisiRepository.save(ekspedisi);
    }

    public void deleteById(String id) {
        ekspedisiRepository.deleteById(id);
    }
}
