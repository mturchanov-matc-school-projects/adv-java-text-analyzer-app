package java112.project4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "EmployeeForwardServlet",
        urlPatterns = { "/addEmployee" }
)

/**
 * This servlet
 * forwards to the add employee
 * output webpage
 *
 * @author     mturchanov
 */
public class EmployeeForwardServlet extends HttpServlet {

    /**
     *  Forwards to the
     *  output webpage
     *
     * @param request request
     * @param response response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/jsp/add_employee.jsp");
        dispatcher.forward(request, response);
    }
}
