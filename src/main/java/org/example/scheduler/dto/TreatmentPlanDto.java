package org.example.scheduler.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.example.scheduler.entites.TreatmentAction;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import static org.example.ConstraintConstants.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class TreatmentPlanDto {

    private TreatmentAction treatmentAction;
    private String subjectPatient;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<DayOfWeek> recurrenceDays;
    private List<Integer> recurrenceHours;


}
