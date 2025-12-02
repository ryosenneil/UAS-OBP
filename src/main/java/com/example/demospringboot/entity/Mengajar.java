package com.example.demospringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mengajar")
public class Mengajar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guru_id", nullable = false)
    private Guru guru;

    @ManyToOne
    @JoinColumn(name = "pelajaran_id", nullable = false)
    private Pelajaran pelajaran;

    public Mengajar() {}

    public Mengajar(Guru guru, Pelajaran pelajaran) {
        this.guru = guru;
        this.pelajaran = pelajaran;
    }

    public Long getId() {
        return id;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    public Pelajaran getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(Pelajaran pelajaran) {
        this.pelajaran = pelajaran;
    }
}
