package org.example.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.scheduler.entites.TaskStatus;
import org.example.scheduler.entites.TreatmentAction;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentTaskDto {

    private TreatmentAction treatmentAction;
    private String subjectPatient;
    private LocalDateTime startTime;
    private TaskStatus status;
}
