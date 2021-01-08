package com.project.foret.Repository.Foret;

import com.project.foret.Model.Foret.ForetPhoto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForetPhotoRepository extends JpaRepository<ForetPhoto, Integer> {

}
