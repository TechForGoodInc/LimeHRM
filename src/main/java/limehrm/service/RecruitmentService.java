package limehrm.service;

import limehrm.hibernate.dao.RecruitmentDao;
import limehrm.hibernate.model.Recruitment;
import limehrm.util.LoggerUtil;
import java.util.List;

public class RecruitmentService {
    private static LoggerUtil logger = new LoggerUtil(RecruitmentService.class.getSimpleName());
    
    
    /** 
     * @param recruitment
     */
    public static void save(Recruitment recruitment) {
        logger.logTrace("Saving recruitment");
        RecruitmentDao.saveRecruitment(recruitment);
    }
    
    /** 
     * @return List<Recruitment>
     */
    public static List<Recruitment> getAllRecruitments() {
        logger.logTrace("Fetching all recruitments");
        return RecruitmentDao.getAllRecruitments();
    }

    
    /** 
     * @param email
     * @return Recruitment
     */
    public static Recruitment findById(String recruitmentId) {
        logger.logTrace("Fetching recruitment by personal_email");
        return RecruitmentDao.getRecruitment(recruitmentId);
    }
    
    
    /** 
     * @param recruitment
     */
    public static void update(Recruitment recruitment) {
        logger.logTrace("Updating recruitment record in database");
        RecruitmentDao.updateRecruitment(recruitment);
    }
    
    
    /** 
     * @param email
     */
    public static void delete (String email) {
        logger.logTrace("Deleting recruitment record from database");
        RecruitmentDao.deleteRecruitment(email);
    }
}
