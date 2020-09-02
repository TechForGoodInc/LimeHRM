package limehrm.worker;

import limehrm.DatabaseConnector;
import limehrm.Limehrm;
import limehrm.tables.pojos.Worker;
import limehrm.tables.records.WorkerRecord;
import limehrm.util.LoggerUtil;
import org.jooq.Field;
import org.jooq.RecordMapper;
import org.jooq.RecordType;
import org.jooq.RecordUnmapper;
import org.jooq.impl.DefaultRecordMapper;
import org.modelmapper.ModelMapper;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class WorkerService {
    private static LoggerUtil logger = new LoggerUtil(WorkerService.class.getSimpleName());
    public static ModelMapper modelMapper = new ModelMapper();
    public static WorkerDao workerDao = new WorkerDao(DatabaseConnector.dbUtil);
    
    public static void save(Worker worker) {
        logger.logTrace("Converting Worker to WorkerRecord");
        WorkerRecord workerRecord = modelMapper.map(worker, WorkerRecord.class);

        logger.logTrace("Inserting workerRecord into database");
        workerDao.createWorker(workerRecord);
    }
    
    public static ArrayList<Worker> getAll() {
        logger.logTrace("Fetching all workers");
        return workerDao.getAllWorkers();
    }

    public static Worker findById(int id) {
        logger.logTrace("Fetching worker by id");
        return workerDao.getOneWorker(id);
    }
    
    public static void update(Worker worker) {
        logger.logTrace("Converting Worker to WorkerRecord");
        WorkerRecord workerRecord = modelMapper.map(worker, WorkerRecord.class);
        
        logger.logTrace("Configuring changed flags for workerRecord");
        for (Field<?> field : limehrm.tables.Worker.WORKER.fields()) {
            if (workerRecord.get(field) == null) {
                workerRecord.changed(field, false);
            }
        }
        
        logger.logTrace("Updating worker record in database");
        workerDao.updateWorker(workerRecord);
    }
    
    public static void delete (int workerId) {
        logger.logTrace("Deleting worker record from database");
        workerDao.deleteWorker(workerId);
    }
}
