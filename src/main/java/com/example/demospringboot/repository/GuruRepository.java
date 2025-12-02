package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Guru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuruRepository extends JpaRepository<Guru, Long> {

}
