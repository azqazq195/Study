package com.project.foret.Repository.Foret;

import com.project.foret.Model.Foret.ForetRegion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForetRegionRepository extends JpaRepository<ForetRegion, Integer> {

}
