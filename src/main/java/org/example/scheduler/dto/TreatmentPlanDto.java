package org.example.scheduler.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.scheduler.entites.TreatmentAction;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import static org.example.ConstraintConstants.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreatmentPlanDto {
    private TreatmentAction treatmentAction;
    @NotEmpty(message = MISSING_PATIENT)
    private String subjectPatient;
    @NotEmpty(message = NO_STARTDATE)
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @NotEmpty(message = NO_DAYS_WEEK)
    private List<DayOfWeek> recurrenceDays;
    @NotEmpty(message = NO_HOURS_REC)
    private List<Integer> recurrenceHours;


}