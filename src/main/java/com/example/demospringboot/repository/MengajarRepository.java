package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Mengajar;
import com.example.demospringboot.entity.Guru;

import java.util.List;

public interface MengajarRepository extends JpaRepository<Mengajar, Long> {
    
  
    void deleteAllByGuru(Guru guru);

    
    List<Mengajar> findAllByGuru(Guru guru);
}
