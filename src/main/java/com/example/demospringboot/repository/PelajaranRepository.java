package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Pelajaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PelajaranRepository extends JpaRepository<Pelajaran, Long> {

}
