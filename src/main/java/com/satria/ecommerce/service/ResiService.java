package com.satria.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.satria.ecommerce.entity.Resi;
import com.satria.ecommerce.exception.BadRequestException;
import com.satria.ecommerce.repository.EkspedisiRepository;
import com.satria.ecommerce.repository.PesananRepository;
import com.satria.ecommerce.repository.ResiRepository;

@Service
public class ResiService {

    @Autowired
    private EkspedisiRepository ekspedisiRepository;

    @Autowired
    private PesananRepository pesananRepository;

    @Autowired
    private ResiRepository resiRepository;

    public List<Resi> findAll() {
        return resiRepository.findAll();
    }

    // public Resi findById(String id) {
    //     return resiRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Resi dengan id " + id + " tidak ditemukan"));
    // }

    public List<Resi> findByPesananId(String pesananId) {
        return resiRepository.findByPesanan_Id(pesananId);
    }

    public Resi create(Resi resi) {
        if (!StringUtils.hasText(resi.getNoResi())) {
            throw new BadRequestException("Nomor resi tidak boleh kosong");
        }

        if (resi.getEkspedisi() == null) {
            throw new BadRequestException("Ekspedisi tidak boleh kosong");
        }

        if (!StringUtils.hasText(resi.getEkspedisi().getId())) {
            throw new BadRequestException("Ekspedisi ID tidak boleh kosong");
        }

        if (resi.getPesanan() == null) {
            throw new BadRequestException("Pesanan tidak boleh kosong");
        }

        if (!StringUtils.hasText(resi.getPesanan().getId())) {
            throw new BadRequestException("Pesanan ID tidak boleh kosong");
        }

        ekspedisiRepository.findById(resi.getEkspedisi().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Ekspedisi ID " + resi.getEkspedisi().getId() + " tidak ditemukan dalam database"));

        pesananRepository.findById(resi.getPesanan().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Pesanan ID " + resi.getPesanan().getId() + " tidak ditemukan dalam database"));

        resi.setId(UUID.randomUUID().toString());
        return resiRepository.save(resi);
    }

    public Resi edit(Resi resi) {
        if (!StringUtils.hasText(resi.getId())) {
            throw new BadRequestException("Resi ID harus diisi");
        }

        if (!StringUtils.hasText(resi.getNoResi())) {
            throw new BadRequestException("Nomor resi tidak boleh kosong");
        }

        if (resi.getEkspedisi() == null) {
            throw new BadRequestException("Ekspedisi tidak boleh kosong");
        }

        if (!StringUtils.hasText(resi.getEkspedisi().getId())) {
            throw new BadRequestException("Ekspedisi ID tidak boleh kosong");
        }

        if (resi.getPesanan() == null) {
            throw new BadRequestException("Pesanan tidak boleh kosong");
        }

        if (!StringUtils.hasText(resi.getPesanan().getId())) {
            throw new BadRequestException("Pesanan ID tidak boleh kosong");
        }

        ekspedisiRepository.findById(resi.getEkspedisi().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Ekspedisi ID " + resi.getEkspedisi().getId() + " tidak ditemukan dalam database"));

        pesananRepository.findById(resi.getPesanan().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Pesanan ID " + resi.getPesanan().getId() + " tidak ditemukan dalam database"));

        return resiRepository.save(resi);
    }

    public void deleteById(String id) {
        resiRepository.deleteById(id);
    }
}
