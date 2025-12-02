package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
}
