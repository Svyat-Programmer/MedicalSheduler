package org.example.scheduler.service;

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
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<TreatmentPlanEntity> activeTreatmentPlans = treatmentPlanRepo
                .findByStartTimeLessThanEqualAndEndTimeGreaterThanEqualOrEndTimeIsNull(currentDateTime, currentDateTime);
        activeTreatmentPlans.stream()
                .map(this::createTreatmentTask)
                .forEach(treatmentTaskRepo::save);
    }

    private TreatmentTaskEntity createTreatmentTask(TreatmentPlanEntity treatmentPlanEntity) {
       return TreatmentTaskEntity.builder()
                .treatmentAction(treatmentPlanEntity.getTreatmentAction())
                .subjectPatient(treatmentPlanEntity.getSubjectPatient())
                .startTime(LocalDateTime.now())
                .status(TaskStatus.ACTIVE)
                .build();


    }

}
