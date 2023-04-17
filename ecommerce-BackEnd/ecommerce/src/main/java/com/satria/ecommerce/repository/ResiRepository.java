package com.satria.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.satria.ecommerce.entity.Resi;

public interface ResiRepository extends JpaRepository<Resi, String> {

    @Query("SELECT r FROM Resi r WHERE r.pesanan.id = :pesananId")
    List<Resi> findByPesanan_Id(@Param("pesananId") String pesananId);
}
