package com.satria.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satria.ecommerce.entity.Pengguna;

public interface PenggunaRepository extends JpaRepository<Pengguna, String> {

    Boolean existsByEmail(String email);

}
