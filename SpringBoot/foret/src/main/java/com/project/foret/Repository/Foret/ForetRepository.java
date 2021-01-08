package com.project.foret.Repository.Foret;

import com.project.foret.Model.Foret.Foret;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForetRepository extends JpaRepository<Foret, Integer> {
    Foret findForetById(int id);
}
