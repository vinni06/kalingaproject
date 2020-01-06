package com.example.kalingaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kalingaproject.entity.Ig;

@Repository
public interface IgRepository extends JpaRepository<Ig, Integer> {

}
