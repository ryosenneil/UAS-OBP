package com.example.demospringboot.service;

import com.example.demospringboot.entity.Orang;
import com.example.demospringboot.repository.OrangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrangService {

    private final OrangRepository orangRepository;

    public OrangService(OrangRepository orangRepository) {
        this.orangRepository = orangRepository;
    }

    public List<Orang> getAllOrang() {
        return orangRepository.findAll();
    }

    public Orang getOrangById(Long id) {
        return orangRepository.findById(id).orElse(null);
    }

    public Orang addOrang(Orang orang) {
        return orangRepository.save(orang);
    }

    public Orang updateOrang(Long id, Orang updated) {
        Orang existing = getOrangById(id);
        if (existing != null) {
            existing.setNama(updated.getNama());
            existing.setUmur(updated.getUmur());
        }
        return orangRepository.save(existing);
    }

    public void deleteOrang(Long id) {
        orangRepository.deleteById(id);
    }
}
