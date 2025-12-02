package com.example.demospringboot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pelajaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaPelajaran;

    @OneToMany(mappedBy = "pelajaran", cascade = CascadeType.ALL)
    private List<Mengajar> mengajarList = new ArrayList<>();

    public Pelajaran() {}

    public Pelajaran(String namaPelajaran) {
        this.namaPelajaran = namaPelajaran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPelajaran() {
        return namaPelajaran;
    }

    public void setNamaPelajaran(String namaPelajaran) {
        this.namaPelajaran = namaPelajaran;
    }

    public List<Mengajar> getMengajarList() {
        return mengajarList;
    }

    public void setMengajarList(List<Mengajar> mengajarList) {
        this.mengajarList = mengajarList;
    }
}
