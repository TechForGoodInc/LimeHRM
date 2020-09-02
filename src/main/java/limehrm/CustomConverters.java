package limehrm;

import io.javalin.Javalin;
import io.javalin.core.validation.JavalinValidation;
import limehrm.enums.JobStatus;
import limehrm.enums.MaritalStatus;
import limehrm.enums.Sex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class CustomConverters {
    public CustomConverters() {
        WorkerConverters();
    }
    
    private void WorkerConverters() {
        JavalinValidation.register(Sex.class, Sex::valueOf);
        JavalinValidation.register(MaritalStatus.class, MaritalStatus::valueOf);
        JavalinValidation.register(JobStatus.class, JobStatus::valueOf);
        JavalinValidation.register(LocalDate.class, e -> LocalDate.parse(e, DateTimeFormatter.ofPattern("dd MMM uuuu")));
    }
}
