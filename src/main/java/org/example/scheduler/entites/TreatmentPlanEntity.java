package org.example.scheduler.entites;

import jakarta.persistence.*;
import lombok.*;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "TREATMENT_PLAN")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TreatmentPlanEntity {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name="treatment_action")
    private TreatmentAction treatmentAction;
    @Column(name="patient")
    private String subjectPatient;
    @Column(name="start_time")
    private LocalDateTime startTime;
    @Column(name="end_time")
    private LocalDateTime endTime;
    @Column(name = "days", columnDefinition = "varchar(255) array")
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> recurrenceDays;
    @Column(name = "hours", columnDefinition = "bigint array")
    private List <Integer> recurrenceHours;
   @Setter
    @Column(name = "processed")
    private Boolean processed;



}
