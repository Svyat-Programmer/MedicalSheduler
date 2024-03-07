package org.example.scheduler.entites;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class TreatmentTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TreatmentAction treatmentAction;

    private String subjectPatient;

    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;





}
