package org.example.scheduler.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
