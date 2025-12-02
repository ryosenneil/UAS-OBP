package com.example.demospringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demospringboot.entity.Guru;
import com.example.demospringboot.entity.Mengajar;
import com.example.demospringboot.entity.Pelajaran;
import com.example.demospringboot.repository.GuruRepository;
import com.example.demospringboot.repository.MengajarRepository;
import com.example.demospringboot.repository.PelajaranRepository;

@Service
public class GuruService {

    private final GuruRepository guruRepository;
    private final PelajaranRepository pelajaranRepository;
    private final MengajarRepository mengajarRepository;

    public GuruService(GuruRepository guruRepository, PelajaranRepository pelajaranRepository, MengajarRepository mengajarRepository) {
        this.guruRepository = guruRepository;
        this.pelajaranRepository = pelajaranRepository;
        this.mengajarRepository = mengajarRepository;
    }

    public List<Guru> getAllGuru() {
        return guruRepository.findAll();
    }

    public Guru getGuruById(Long id) {
        return guruRepository.findById(id).orElse(null);
    }

    @Transactional
    public Guru addGuru(Guru guru, List<Long> pelajaranIds) {
        
        Guru savedGuru = guruRepository.save(guru);

     
        if (pelajaranIds != null) {
            for (Long pid : pelajaranIds) {
                Pelajaran p = pelajaranRepository.findById(pid).orElse(null);
                if (p != null) {
                    Mengajar m = new Mengajar(savedGuru, p);
                    mengajarRepository.save(m);
                }
            }
        }

        return savedGuru;
    }

    @Transactional
    public Guru updateGuru(Long id, Guru updated, List<Long> pelajaranIds) {
        Optional<Guru> opt = guruRepository.findById(id);
        if (opt.isEmpty()) return null;

        Guru existing = opt.get();
        existing.setNama(updated.getNama());
        existing.setUmur(updated.getUmur());
        existing.setGajiPokok(updated.getGajiPokok());
        guruRepository.save(existing);

      
        mengajarRepository.deleteAllByGuru(existing);

        
        if (pelajaranIds != null) {
            for (Long pid : pelajaranIds) {
                Pelajaran p = pelajaranRepository.findById(pid).orElse(null);
                if (p != null) {
                    Mengajar m = new Mengajar(existing, p);
                    mengajarRepository.save(m);
                }
            }
        }

        return existing;
    }

    @Transactional
    public void deleteGuru(Long id) {
        Guru g = guruRepository.findById(id).orElse(null);
        if (g != null) {
            mengajarRepository.deleteAllByGuru(g);
            guruRepository.delete(g);
        }
    }

}
