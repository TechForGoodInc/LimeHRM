package limehrm.worker;

import limehrm.exceptions.DuplicateIDsSqlException;
import limehrm.exceptions.ResourceNotFoundException;
import limehrm.tables.Worker;
import limehrm.tables.records.WorkerRecord;
import limehrm.util.LoggerUtil;
import limehrm.util.db.DbUtil;
import org.jooq.SQL;

import java.util.ArrayList;

public class WorkerDao {
    private static final LoggerUtil logger = new LoggerUtil(WorkerDao.class.getSimpleName());
    
    private DbUtil dbUtil;
    
    public WorkerDao(DbUtil dbUtil) {
        this.dbUtil = dbUtil;
    }
    
    private boolean doesWorkerExist(int id) {
        return dbUtil.getCreate()
                .select(Worker.WORKER.ID)
                .from(Worker.WORKER)
                .where(Worker.WORKER.ID.eq(id))
                .execute() == 1;
    }
    
    public void createWorker(WorkerRecord workerRecord) {
        logger.logTrace("Checking if worker already exists");
        
        if (doesWorkerExist(workerRecord.getId())) {
            logger.logDebug("Worker already exists");
            throw new DuplicateIDsSqlException();
        }
        
        dbUtil.getCreate().insertInto(Worker.WORKER, 
                    Worker.WORKER.ID, Worker.WORKER.FIRST_NAME, Worker.WORKER.LAST_NAME,
                    Worker.WORKER.PERSONAL_EMAIL, Worker.WORKER.HOME_PHONE, Worker.WORKER.HOME_ADDRESS,
                    Worker.WORKER.SEX, Worker.WORKER.MARITAL_STATUS, Worker.WORKER.POSITION_NAME,
                    Worker.WORKER.TEAM_NAME, Worker.WORKER.SALARY, Worker.WORKER.DEPARTMENT,
                    Worker.WORKER.BIRTH_DATE, Worker.WORKER.START_DATE, Worker.WORKER.END_DATE,
                    Worker.WORKER.JOB_STATUS, Worker.WORKER.MANAGER_EMAIL)
                .values(workerRecord.getId(), workerRecord.getFirstName(), workerRecord.getLastName(),
                        workerRecord.getPersonalEmail(), workerRecord.getHomePhone(), workerRecord.getHomeAddress(),
                        workerRecord.getSex(), workerRecord.getMaritalStatus(), workerRecord.getPositionName(),
                        workerRecord.getTeamName(), workerRecord.getSalary(), workerRecord.getDepartment(), 
                        workerRecord.getBirthDate(), workerRecord.getStartDate(), workerRecord.getEndDate(),
                        workerRecord.getJobStatus(), workerRecord.getManagerEmail())
                .execute();
    }
    
    public ArrayList<limehrm.tables.pojos.Worker> getAllWorkers() {
        return new ArrayList<>(dbUtil.getCreate().selectFrom(Worker.WORKER).fetchInto(limehrm.tables.pojos.Worker.class));
    }
    
    public limehrm.tables.pojos.Worker getOneWorker(int id) {
        if (!doesWorkerExist(id)) {
            logger.logDebug("Specified worker doesn't exist");
            throw new ResourceNotFoundException();
        }
        
        return dbUtil.getCreate().selectFrom(Worker.WORKER)
                .where(Worker.WORKER.ID.eq(id))
                .fetchOne()
                .into(limehrm.tables.pojos.Worker.class);
    }
    
    public void updateWorker(WorkerRecord worker) {
        if (!doesWorkerExist(worker.getId())) {
            logger.logDebug("Specified worker doesn't exist");
            throw new ResourceNotFoundException();
        }
        
        dbUtil.getCreate().update(Worker.WORKER)
                .set(worker)
                .where(Worker.WORKER.ID.eq(worker.getId()))
                .execute();
    }
    
    public void deleteWorker(int workerId) {
        if (!doesWorkerExist(workerId)) {
            logger.logDebug("Specified worker doesn't exist");
            throw new ResourceNotFoundException();
        }
        dbUtil.getCreate().deleteFrom(Worker.WORKER)
                .where(Worker.WORKER.ID.eq(workerId))
                .execute();
    }
}
