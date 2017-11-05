import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_NewFolder extends HttpServlet
{
Connection con;
Statement st;
ServletContext scon;
String uname;

public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
   try{
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
      st=con.createStatement();
      scon=getServletContext();
//      Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
    //  Serv_SignUp ssu = new Serv_SignUp();
      //uname=ssu.name();
      HttpSession session = req.getSession(true);
         uname=session.getValue("name").toString();            

      ServletOutputStream sos=res.getOutputStream();
      sos.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
      sos.println("<body bgcolor=white background=indtextb.jpg text=blue> <pre>");
      sos.println("<h2><center><blink> New Folder Screen</blink></center></h2>");
      sos.println("<form name=f11 action='Serv_AddFolder'");
      sos.println("<b> Enter Folder Name : </b><input type=text name=fname value=''><br>");  
      sos.println("                            <input type=submit name=sub11  value='Add-Folder' <br><br>");
      sos.println("</form></body></html>");      
      }
   catch(Exception e)
      {e.printStackTrace();}
   }
}

