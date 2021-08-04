package limehrm.service;

import limehrm.hibernate.dao.LeaveDao;
import limehrm.hibernate.model.Leave;
import limehrm.util.LoggerUtil;
import java.util.List;

public class LeaveService {
    private static LoggerUtil logger = new LoggerUtil(LeaveService.class.getSimpleName());
    
    
    /** 
     * @param leave
     */
    public static void save(Leave leave) {
        logger.logTrace("Saving leave");
        LeaveDao.saveLeave(leave);
    }
    
    /** 
     * @return List<Leave>
     */
    public static List<Leave> getAllLeave() {
        logger.logTrace("Fetching all leave");
        return LeaveDao.getAllLeaves();
    }

    
    /** 
     * @param leaveId
     * @return Leave
     */
    public static Leave findById(String leaveId) {
        logger.logTrace("Fetching leave by id");
        return LeaveDao.getLeave(leaveId);
    }
    
    
    /** 
     * @param leave
     */
    public static void update(Leave leave) {
        logger.logTrace("Updating leave record in database");
        LeaveDao.updateLeave(leave);
    }
    
    
    /** 
     * @param email
     */
    public static void delete (String email) {
        logger.logTrace("Deleting leave record from database");
        LeaveDao.deleteLeave(email);
    }
}
