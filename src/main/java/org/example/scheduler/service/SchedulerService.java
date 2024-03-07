package org.example.scheduler.service;

import lombok.extern.slf4j.Slf4j;
import org.example.scheduler.dto.TreatmentPlanDto;
import org.example.scheduler.entites.TaskStatus;
import org.example.scheduler.entites.TreatmentPlanEntity;
import org.example.scheduler.entites.TreatmentTaskEntity;
import org.example.scheduler.repo.TreatmentPlanRepo;
import org.example.scheduler.repo.TreatmentTaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class SchedulerService {
    private final TreatmentPlanRepo treatmentPlanRepo;
    private final TreatmentTaskRepo treatmentTaskRepo;
    @Autowired
    public SchedulerService(TreatmentPlanRepo treatmentPlanRepo, TreatmentTaskRepo treatmentTaskRepo) {
        this.treatmentPlanRepo = treatmentPlanRepo;
        this.treatmentTaskRepo = treatmentTaskRepo;
    }
    @Scheduled(fixedRate = 20000)
    public void generateTreatmentTasks()
    {
        log.info("Scheduled task to generate treatment tasks started.");
        LocalDateTime currentTime = LocalDateTime.now();
        List<TreatmentPlanEntity> activeTreatmentPlans = treatmentPlanRepo
                .findAll();//findByStartTimeLessThanEqualAndEndTimeGreaterThanEqualOrEndTimeIsNull(currentTime, currentTime);
        log.info("Found {} active treatment plans.", activeTreatmentPlans.size());
        activeTreatmentPlans.stream()
                .map(this::createTreatmentTask)
                .forEach(treatmentTaskRepo::save);
        log.info("Saved treatment task with ");
    }

    private TreatmentTaskEntity createTreatmentTask(TreatmentPlanEntity treatmentPlanEntity) {
        log.info("Creating treatment task for TreatmentPlanEntity with ID: {}", treatmentPlanEntity.getId());
       return TreatmentTaskEntity.builder()
                .treatmentAction(treatmentPlanEntity.getTreatmentAction())
                .subjectPatient(treatmentPlanEntity.getSubjectPatient())
                .startTime(LocalDateTime.now())
                .status(TaskStatus.ACTIVE)
                .build();


    }

}
