

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TaskDeleteMenard
 */
@WebServlet("/TaskDeleteMenard")
public class TaskDeleteMenard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDeleteMenard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assignmentName = request.getParameter("assignmentName");

        Connection connection = null;
        String insertSql = "DELETE FROM MyTableAssignments WHERE ASSIGNMENTNAME = ?";

        try {
           DBConnectionMenard.getDBConnection();
           connection = DBConnectionMenard.connection;
           PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
           preparedStmt.setString(1, assignmentName);
           preparedStmt.execute();
           connection.close();
        } catch (Exception e) {
           e.printStackTrace();
        }

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Deleted from Assignment List";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        out.println(docType + //
              "<html>\n" + //
              "<head><title>" + title + "</title></head>\n" + //
              "<body bgcolor=\"#f0f0f0\">\n" + //
              "<h2 align=\"center\">" + title + "</h2>\n" + //
              "<ul>\n" + //

              "  <li><b>Assignment Name</b>: " + assignmentName + "\n" + //

              "</ul>\n");

        out.println("<a href=/Individual-project-menard/TaskListControl.html>Back to Assignment Manager</a> <br>");
        out.println("</body></html>");
     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
     }

}
