package com.example.kalingaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kalingaproject.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
