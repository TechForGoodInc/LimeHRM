package limehrm.worker;

import limehrm.hibernate.dao.WorkerDao;
import limehrm.hibernate.model.Worker;
import limehrm.util.LoggerUtil;


import java.util.ArrayList;
import java.util.List;

public class WorkerService {
    private static LoggerUtil logger = new LoggerUtil(WorkerService.class.getSimpleName());
    
    
    /** 
     * @param worker
     */
    public static void save(Worker worker) {
        logger.logTrace("Saving worker");
        WorkerDao.saveWorker(worker);
    }
    
    
    /** 
     * @return List<Worker>
     */
    public static List<Worker> getAll() {
        logger.logTrace("Fetching all workers");
        return WorkerDao.getAllWorkers();
    }

    
    /** 
     * @param id
     * @return Worker
     */
    public static Worker findById(String id) {
        logger.logTrace("Fetching worker by id");
        return WorkerDao.getWorker(id);
    }
    
    
    /** 
     * @param worker
     */
    public static void update(Worker worker) {
        logger.logTrace("Updating worker record in database");
        WorkerDao.updateWorker(worker);
    }
    
    
    /** 
     * @param workerId
     */
    public static void delete (String workerId) {
        logger.logTrace("Deleting worker record from database");
        WorkerDao.deleteWorker(workerId);
    }
}
