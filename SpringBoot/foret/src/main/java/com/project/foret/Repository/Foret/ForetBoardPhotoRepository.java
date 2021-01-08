package com.project.foret.Repository.Foret;

import com.project.foret.Model.Foret.ForetBoardPhoto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForetBoardPhotoRepository extends JpaRepository<ForetBoardPhoto, Integer> {

}
