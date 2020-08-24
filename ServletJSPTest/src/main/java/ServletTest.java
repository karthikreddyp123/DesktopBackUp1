

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/Add")
public class ServletTest extends HttpServlet {
	   public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		   HttpSession session=request.getSession();
		   session.setAttribute("Test", "test");
		   String fname=request.getParameter("fname");
		   String lname=request.getParameter("lname");
		   PrintWriter out=response.getWriter();
		   out.println(session.getAttribute("Test").toString());
		   out.println(fname+" "+lname);
	   }

}
