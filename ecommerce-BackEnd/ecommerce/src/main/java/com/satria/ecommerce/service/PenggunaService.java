package com.satria.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.satria.ecommerce.entity.Pengguna;
import com.satria.ecommerce.exception.BadRequestException;
import com.satria.ecommerce.exception.ResourceNotFoundException;
import com.satria.ecommerce.repository.PenggunaRepository;

@Service
public class PenggunaService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Pengguna findById(String id) {
        return penggunaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pengguna dengan id " + id + " tidak ditemukan"));
    }

    public List<Pengguna> findAll() {
        return penggunaRepository.findAll();
    }

    public Pengguna create(Pengguna pengguna) {
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestException("Username harus diisi");
        }

        if (penggunaRepository.existsById(pengguna.getId())) {
            throw new BadRequestException("Username " + pengguna.getId() + " sudah terdaftar");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("Email harus diisi");
        }

        if (penggunaRepository.existsByEmail(pengguna.getEmail())) {
            throw new BadRequestException("Email " + pengguna.getEmail() + " sudah terdaftar");
        }

        pengguna.setIsAktif(true);
        return penggunaRepository.save(pengguna);
    }

    public Pengguna edit(Pengguna pengguna) {
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestException("Username harus diisi");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("Email harus diisi");
        }

        Pengguna existingPengguna = findById(pengguna.getId());
        existingPengguna.setPassword(passwordEncoder.encode(pengguna.getPassword()));
        // if (StringUtils.hasText(pengguna.getPassword())) {
        //     if (!passwordEncoder.matches(oldPassword, existingPengguna.getPassword())) {
        //         throw new BadRequestException("Password lama salah");
        //     }
        //     existingPengguna.setPassword(passwordEncoder.encode(pengguna.getPassword()));
        // }
        return penggunaRepository.save(existingPengguna);
    }

    public void deleteById(String id) {
        penggunaRepository.deleteById(id);
    }
}
