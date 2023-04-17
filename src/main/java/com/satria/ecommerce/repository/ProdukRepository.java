package com.satria.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satria.ecommerce.entity.Produk;

public interface ProdukRepository extends JpaRepository<Produk, String> {

}
