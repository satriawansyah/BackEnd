package com.satria.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PesananRequest implements Serializable {
    private BigDecimal ongkir;
    private String alamatPengiriman;
    private String namaPenerima;
    private String telpPenerima;
    private List<KeranjangRequest> items;
}
