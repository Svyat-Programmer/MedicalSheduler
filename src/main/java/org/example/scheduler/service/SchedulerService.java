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

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    public void generateTreatmentTasks() {
        log.info("start generate");
        List<TreatmentTaskEntity> treatmentTasks = new ArrayList<>();

        LocalDateTime currentDate = LocalDateTime.now();

        List<TreatmentPlanEntity> activeTreatmentPlans = treatmentPlanRepo.findByProcessedIsNullOrProcessedIsFalse();
        log.info("Found {} active treatment plans.", activeTreatmentPlans.size());
        for (TreatmentPlanEntity treatmentPlan : activeTreatmentPlans) {
            log.info("Processing treatment plan for patient '{}'", treatmentPlan.getSubjectPatient());
            LocalDateTime endDate = treatmentPlan.getEndTime();
            log.info("End date for the plan: {}", endDate);


            while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
                log.info("Current date: {}", currentDate);
                for (DayOfWeek recurrenceDay : treatmentPlan.getRecurrenceDays()) {
                    log.info("Processing recurrence day: {}", recurrenceDay);

                    LocalDateTime nextRecurrence = findNextRecurrence(currentDate, recurrenceDay);

                    if (nextRecurrence.isBefore(endDate) || nextRecurrence.isEqual(endDate)) {
                        log.info("Next recurrence: {}", nextRecurrence);
                        for (Integer recurrenceHour : treatmentPlan.getRecurrenceHours()) {
                            LocalDateTime taskDateTime = LocalDateTime.of(nextRecurrence.toLocalDate(),
                                    LocalTime.of(recurrenceHour, 0));
                            log.info("Creating task for {}: {}", treatmentPlan.getSubjectPatient(), taskDateTime);

                            TreatmentTaskEntity taskEntity = new TreatmentTaskEntity();
                            taskEntity.setTreatmentAction(treatmentPlan.getTreatmentAction());
                            taskEntity.setSubjectPatient(treatmentPlan.getSubjectPatient());
                            taskEntity.setStartTime(taskDateTime);
                            taskEntity.setStatus(TaskStatus.ACTIVE);

                            treatmentTasks.add(taskEntity);
                        }
                    }
                }
                currentDate = currentDate.plusWeeks(1);
            }
            currentDate = LocalDateTime.now();
        }

        treatmentTasks.sort(Comparator.comparing(TreatmentTaskEntity::getStartTime));

        try {
            treatmentTaskRepo.saveAll(treatmentTasks);
            log.info("Saved {} tasks to the database.", treatmentTasks.size());
        } catch (Exception e) {
            log.error("Error saving tasks to the database.", e);
        }
        activeTreatmentPlans.forEach(p-> {p.setProcessed(true);
        treatmentPlanRepo.save(p);});
    }

    private LocalDateTime findNextRecurrence(LocalDateTime currentDate, DayOfWeek recurrenceDay) {
        LocalDateTime nextRecurrence = currentDate;

        while (nextRecurrence.getDayOfWeek() != recurrenceDay) {
            nextRecurrence = nextRecurrence.plusDays(1);
        }

        return nextRecurrence;
    }
}
