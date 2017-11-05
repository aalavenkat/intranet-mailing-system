import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Serv_EditFolder extends HttpServlet
{
Statement st;
Connection con;
ResultSet rs;
ServletContext scon;
String uname,of;
public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
          st=con.createStatement();
          scon=getServletContext();
//          Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
          //Serv_SignUp a = new Serv_SignUp();
          //uname=a.name();
             HttpSession session = req.getSession(true);
            uname=session.getValue("name").toString();            

          of=req.getParameter("fol");
		  System.out.println("of: "+of);
          ServletOutputStream sos=res.getOutputStream();
          sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue>");
          sos.println("<form name=for method=post action='Serv_ModifyFolder'>");
          sos.println("<h3>Changing the name of '"+of+"'</h3><br>");
          sos.println("");
          sos.println("<b>Folders New Name</b>  :   <input type=text name=newfol value=''><br><br>");
          sos.println("<input type=submit name=su value='MODIFY-FOLDER'>");
          sos.println("<input type=hidden name=hid value='"+of+"'>");
          sos.println("</form></body></HTML>");
}
catch(Exception e){
e.printStackTrace();
}
}                                             
}


 
