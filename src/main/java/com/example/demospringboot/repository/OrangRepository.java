package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Orang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrangRepository extends JpaRepository<Orang, Long> {

}
