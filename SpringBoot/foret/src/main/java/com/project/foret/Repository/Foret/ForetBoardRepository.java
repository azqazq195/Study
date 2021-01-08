package com.project.foret.Repository.Foret;

import com.project.foret.Model.Foret.ForetBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForetBoardRepository extends JpaRepository<ForetBoard, Integer> {
    ForetBoard findBoardById(int id);

}
