package org.example.scheduler.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
public class TreatmentPlanEntity {

    private Long id;
    @Enumerated(EnumType.STRING)
    private TreatmentAction treatmentAction;
    private String subjectPatient;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ElementCollection
    @CollectionTable(name = "recurrence_days", joinColumns = @JoinColumn(name = "plan_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private List<DayOfWeek> recurrenceDays;

    @ElementCollection
    @CollectionTable(name = "recurrence_hours", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "hour")
    private List<Integer> recurrenceHours;



}
