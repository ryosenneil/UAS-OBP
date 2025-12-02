package com.example.demospringboot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Guru extends Orang {

    @OneToMany(mappedBy = "guru", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mengajar> mengajarList = new ArrayList<>();

    public Guru() {
        super();
    }

    public Guru(String nama, int umur, double gajiPokok) {
        super(nama, umur, gajiPokok);
    }

    public List<Mengajar> getMengajarList() {
        return mengajarList;
    }

    public void setMengajarList(List<Mengajar> mengajarList) {
        this.mengajarList = mengajarList;
    }

    @Transient
    public List<Pelajaran> getPelajaranList() {
        List<Pelajaran> list = new ArrayList<>();
        for (Mengajar m : mengajarList) {
            if (m.getPelajaran() != null) {
                list.add(m.getPelajaran());
            }
        }
        return list;
    }

    @Override
    public String getDetail() {
        return "Guru - Mengajar " + mengajarList.size() + " Mata Pelajaran";
    }

    @Override
    public double hitungGaji() {
        return getGajiPokok() + (mengajarList.size() * 1000000);
    }
}
