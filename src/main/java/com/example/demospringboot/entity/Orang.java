package com.example.demospringboot.entity;

import jakarta.persistence.*;
// import java.io.Serializable;

@Entity
@Table(name = "orang")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Orang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private int umur;
    private double gajiPokok;

    public Orang() {}

    public Orang(String nama, int umur, double gajiPokok) {
        this.nama = nama;
        this.umur = umur;
        this.gajiPokok = gajiPokok;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNama() { 
        return nama; 
    }

    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public int getUmur() { 
        return umur; 
    }

    public void setUmur(int umur) {
        if (umur >= 0) {
            this.umur = umur;
        } else {
            System.out.println("Umur tidak boleh negatif!");
        }
    }

    public double getGajiPokok() { 
        return gajiPokok; 
    }

    public void setGajiPokok(double gajiPokok) { 
        this.gajiPokok = gajiPokok; 
    }

    public String getJenis() {
        return this.getClass().getSimpleName();
    }

    public String getDetail() {
        return "-";
    }

    public abstract double hitungGaji();

    @Override
    public String toString() {
        return "Nama: " + nama + ", Umur: " + umur;
    }
}
