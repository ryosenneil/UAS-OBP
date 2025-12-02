package com.example.demospringboot.entity;

import jakarta.persistence.Entity;

@Entity
public class Staff extends Orang {

    private String divisi;

    public Staff() {
        super();
        this.divisi = "";
    }

    public Staff(String nama, int umur, String divisi) {
        super(nama, umur, 0); 
        this.divisi = (divisi != null && !divisi.isEmpty()) ? divisi : "";
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = (divisi != null && !divisi.isEmpty()) ? divisi : "";
    }

    @Override
    public String getDetail() {
        return "Staff - Divisi: " + divisi;
    }

    @Override
    public double hitungGaji() {
        return getGajiPokok();
    }
}
