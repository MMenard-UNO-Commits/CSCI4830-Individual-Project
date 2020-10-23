

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class TaskListDisplayMenard
 */
@WebServlet("/TaskListDisplayMenard")
public class TaskListDisplayMenard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskListDisplayMenard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        search(response);
     }

     void search(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
              "transitional//en\">\n"; //
        out.println(docType + //
              "<html>\n" + //
              "<head><title>" + title + "</title></head>\n" + //
              "<body bgcolor=\"#f0f0f0\">\n" + //
              "<h1 align=\"center\">" + title + "</h1>\n");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
           DBConnectionMenard.getDBConnection();
           connection = DBConnectionMenard.connection;

           
           String selectSQL = "SELECT * FROM MyTableAssignments";
           preparedStatement = connection.prepareStatement(selectSQL);
           ResultSet rs = preparedStatement.executeQuery();

           while (rs.next()) {
              int id = rs.getInt("id");
              String assignmentName = rs.getString("assignmentname").trim();
              String className = rs.getString("classname").trim();
              String dateDue = rs.getString("datedue").trim();
              String timeDue = rs.getString("timedue").trim();

              out.println("ID: " + id + ", ");
              out.println("Assignment Name: " + assignmentName + ", ");
              out.println("Class Name: " + className + ", ");
              out.println("Date Due: " + dateDue + ", ");
              out.println("Time Due:" + timeDue + "<br>"); 
           }
           out.println("<a href=/Individual-project-menard/TaskListControl.html>Back to Assignment Manager</a> <br>");
           out.println("</body></html>");
           rs.close();
           preparedStatement.close();
           connection.close();
        } catch (SQLException se) {
           se.printStackTrace();
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {
              if (preparedStatement != null)
                 preparedStatement.close();
           } catch (SQLException se2) {
           }
           try {
              if (connection != null)
                 connection.close();
           } catch (SQLException se) {
              se.printStackTrace();
           }
        }
     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
     }

}
