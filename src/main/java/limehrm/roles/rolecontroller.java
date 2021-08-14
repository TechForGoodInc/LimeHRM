import limehrm.hibernate.dao.RoleDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import limhrm.hibernate.model.Role;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import services.RoleService;
import services.interfaces.IRoleService;
import util.MethodIdentifier;

/**
 * Maps to roles.htm
 *
 *
 */
public class RoleController implements Controller {

    IRoleService rs = new RoleService();

    /**
     * Handles Controller Requests
     *
     * @param hsr
     * @param hsr1
     * @return
     * @throws Exception
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {

        ModelAndView mv = new ModelAndView("roles");

        String method = MethodIdentifier.identifyMethod(hsr);
        switch (method) {
            case "GET":
                mv.addObject("flag", false);
                break;

            case "POST":
                rs.saveRole(hsr.getParameter("title"));

                mv.addObject("flag", true);
                mv.addObject("message", "Role Added Successfully!");
                break;

            case "PUT":
                rs.updateRole(Integer.parseInt(hsr.getParameter("id")),
                        hsr.getParameter("title"));

                mv.addObject("flag", true);
                mv.addObject("message", "Role Edited Successfully!");
                break;

            case "DELETE":
                break;
        }

        get(mv);
        mv.addObject("page", "role");
        return mv;
    }

    /**
     * Returns Role List View
     *
     * @return
     */
    private ModelAndView get(ModelAndView mv) {

        try {
            mv.addObject("roles", rs.getRoles());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

}
 