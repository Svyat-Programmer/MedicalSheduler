package org.example.scheduler.repo;

import org.example.scheduler.entites.TreatmentTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentTaskRepo extends JpaRepository <TreatmentTaskEntity, Long> {



}
