

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
 * Servlet implementation class TaskInsertMenard
 */
@WebServlet("/TaskInsertMenard")
public class TaskInsertMenard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskInsertMenard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assignmentName = request.getParameter("assignmentName");
        String className = request.getParameter("className");
        String dateDue = request.getParameter("dateDue");
        String timeDue = request.getParameter("timeDue");

        Connection connection = null;
        String insertSql = " INSERT INTO MyTableAssignments (id, ASSIGNMENTNAME, CLASSNAME, DATEDUE, TIMEDUE) values (default, ?, ?, ?, ?)";

        try {
           DBConnectionMenard.getDBConnection();
           connection = DBConnectionMenard.connection;
           PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
           preparedStmt.setString(1, assignmentName);
           preparedStmt.setString(2, className);
           preparedStmt.setString(3, dateDue);
           preparedStmt.setNString(4, timeDue);
           preparedStmt.execute();
           connection.close();
        } catch (Exception e) {
           e.printStackTrace();
        }

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Insert Assignment to the Assignment List";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        out.println(docType + //
              "<html>\n" + //
              "<head><title>" + title + "</title></head>\n" + //
              "<body bgcolor=\"#f0f0f0\">\n" + //
              "<h2 align=\"center\">" + title + "</h2>\n" + //
              "<ul>\n" + //

              "  <li><b>Assignment Name</b>: " + assignmentName + "\n" + //
              "  <li><b>Class Name</b>: " + className + "\n" + //
              "  <li><b>Time Due</b>: " + dateDue + "\n" + //
              "  <li><b>Date Due</b>: " + timeDue + "\n" + //

              "</ul>\n");

        out.println("<a href=/Individual-project-menard/TaskListControl.html>Back to Assignment Manager</a> <br>");
        out.println("</body></html>");
     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
     }

}
