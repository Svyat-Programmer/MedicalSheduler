package Medical;

import org.example.scheduler.MedicalSchedulerApplication;
import org.example.scheduler.entites.TaskStatus;
import org.example.scheduler.entites.TreatmentAction;
import org.example.scheduler.entites.TreatmentTaskEntity;
import org.example.scheduler.repo.TreatmentPlanRepo;
import org.example.scheduler.repo.TreatmentTaskRepo;
import org.example.scheduler.service.SchedulerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest(classes = MedicalSchedulerApplication.class)
@Sql(scripts = {"classpath:test_data.sql"})
    public class SchedulerServiceTests {

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
            assertEquals(LocalDateTime.of(2024,3,15,8,00),generatedTasks.get(0).getStartTime());
        }
    }



