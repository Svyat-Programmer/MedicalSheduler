package org.example.scheduler.repo;

import org.example.scheduler.entites.TreatmentPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TreatmentPlanRepo extends JpaRepository <TreatmentPlanEntity, Long>  {


    List<TreatmentPlanEntity> findByProcessedIsNullOrProcessedIsFalse();
}
