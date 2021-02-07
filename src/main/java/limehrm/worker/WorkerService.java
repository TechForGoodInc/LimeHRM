package limehrm.worker;

import limehrm.hibernate.dao.WorkerDao;
import limehrm.hibernate.model.Worker;
import limehrm.util.LoggerUtil;


import java.util.ArrayList;
import java.util.List;

public class WorkerService {
    private static LoggerUtil logger = new LoggerUtil(WorkerService.class.getSimpleName());
    
    public static void save(Worker worker) {
        logger.logTrace("Saving worker");
        WorkerDao.saveWorker(worker);
    }
    
    public static List<Worker> getAll() {
        logger.logTrace("Fetching all workers");
        return WorkerDao.getAllWorkers();
    }

    public static Worker findById(String id) {
        logger.logTrace("Fetching worker by id");
        return WorkerDao.getWorker(id);
    }
    
    public static void update(Worker worker) {
        logger.logTrace("Updating worker record in database");
        WorkerDao.updateWorker(worker);
    }
    
    public static void delete (String workerId) {
        logger.logTrace("Deleting worker record from database");
        WorkerDao.deleteWorker(workerId);
    }
}
