package com.satria.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.satria.ecommerce.entity.PesananItem;

public interface PesananItemRepository extends JpaRepository<PesananItem, String> {

    @Query("SELECT pi FROM PesananItem pi WHERE pi.pesanan.id = :pesananId")
    List<PesananItem> findByPesanan_Id(@Param("pesananId") String pesananId);
}
