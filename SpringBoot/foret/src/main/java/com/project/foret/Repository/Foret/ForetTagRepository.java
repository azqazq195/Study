package com.project.foret.Repository.Foret;

import com.project.foret.Model.Foret.ForetTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForetTagRepository extends JpaRepository<ForetTag, Integer> {

}
