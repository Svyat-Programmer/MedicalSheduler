package Medical;

import org.example.MedicalSchedulerApplication;
import org.example.scheduler.entites.TaskStatus;
import org.example.scheduler.entites.TreatmentAction;
import org.example.scheduler.entites.TreatmentPlanEntity;
import org.example.scheduler.entites.TreatmentTaskEntity;
import org.example.scheduler.repo.TreatmentPlanRepo;
import org.example.scheduler.repo.TreatmentTaskRepo;
import org.example.scheduler.service.SchedulerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest(classes = MedicalSchedulerApplication.class)
    public class ShedulerServiceTests {

        @Autowired
        private SchedulerService schedulerService;

        @Autowired
        private TreatmentPlanRepo treatmentPlanRepo;

        @Autowired
        private TreatmentTaskRepo treatmentTaskRepo;


        @Test
        public void generateTreatmentTasks_ShouldCreateTasksWithCorrectDetails() {
            schedulerService.generateTreatmentTasks();
            List<TreatmentTaskEntity> generatedTasks = treatmentTaskRepo.findAll();
            assertEquals(TreatmentAction.ACTION_A, generatedTasks.get(0).getTreatmentAction());
            assertEquals("Svyat", generatedTasks.get(0).getSubjectPatient());
            assertEquals(TaskStatus.ACTIVE, generatedTasks.get(0).getStatus());
            LocalDateTime currentDateTime=LocalDateTime.now();
            LocalTime time = LocalTime.of(8, 0, 0);
            LocalDateTime nextFriday = currentDateTime.with(time).with(DayOfWeek.FRIDAY);
            assertEquals(nextFriday,generatedTasks.get(0).getStartTime());
        }
    }



