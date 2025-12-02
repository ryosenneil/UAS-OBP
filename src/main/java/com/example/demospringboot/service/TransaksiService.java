package com.example.demospringboot.service;

import com.example.demospringboot.entity.Transaksi;
import com.example.demospringboot.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    public List<Transaksi> findAll() {
        return transaksiRepository.findAll();
    }

    public Transaksi findById(Long id) {
        return transaksiRepository.findById(id).orElse(null);
    }

    public void save(Transaksi transaksi) {
        transaksiRepository.save(transaksi);
    }

    public void edit(Transaksi transaksi) {
        Transaksi existing = transaksiRepository.findById(transaksi.getId()).orElse(null);

        if (existing != null) {
            existing.setOrang(transaksi.getOrang());
            existing.setGaji(transaksi.getGaji());
            existing.setTanggal(transaksi.getTanggal()); 
            existing.setDeskripsi(transaksi.getDeskripsi());

            transaksiRepository.save(existing);
        }
    }

    public void delete(Long id) {
        transaksiRepository.deleteById(id);
    }
}
