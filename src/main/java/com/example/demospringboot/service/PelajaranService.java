package com.example.demospringboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Pelajaran;
import com.example.demospringboot.repository.PelajaranRepository;

@Service
public class PelajaranService {

    private final PelajaranRepository pelajaranRepository;

    public PelajaranService(PelajaranRepository pelajaranRepository) {
        this.pelajaranRepository = pelajaranRepository;
    }

    // CRUD Pelajaran
    public List<Pelajaran> getAllPelajaran() {
        return pelajaranRepository.findAll();
    }

    public Pelajaran getPelajaranById(Long id) {
        return pelajaranRepository.findById(id).orElse(null);
    }

    public Pelajaran addPelajaran(Pelajaran p) {
        return pelajaranRepository.save(p);
    }

    public Pelajaran updatePelajaran(Long id, Pelajaran updated) {
        return pelajaranRepository.findById(id).map(existing -> {
            existing.setNamaPelajaran(updated.getNamaPelajaran());
            return pelajaranRepository.save(existing);
        }).orElse(null);
    }

    public void deletePelajaran(Long id) {
        pelajaranRepository.deleteById(id);
    }
}
