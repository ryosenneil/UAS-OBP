package com.example.demospringboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orang_id")
    private Orang orang;

    private Double gaji;

    private LocalDate tanggal;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    public Transaksi() {
    }

    public Long getId() {
        return id;
    }

    public Orang getOrang() {
        return orang;
    }

    public Double getGaji() {
        return gaji;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrang(Orang orang) {
        this.orang = orang;
    }

    public void setGaji(Double gaji) {
        this.gaji = gaji;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
