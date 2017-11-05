import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_DeleteFolder extends HttpServlet{
Statement st,st1;
ResultSet rs;
Connection con;
ServletContext scon;
String uname,f;

public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();
//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
   //Serv_SignUp ssu = new Serv_SignUp();
   //uname=ssu.name();
   HttpSession session = req.getSession(true);
   uname=session.getValue("name").toString();               
ServletOutputStream sos= res.getOutputStream();
f=req.getParameter("fol");
int r=st.executeUpdate("delete from folders where uname='"+uname+"'and folder='"+f+"'");
if (r!=0)
 {
    sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h2><i>  Folder "+f+" Deleted successfully.</i></h2></body></html>");
    res.setHeader("Refresh","2;URL='Serv_Folder'");
 }
}
catch(Exception e)
{
e.printStackTrace();
}}
}
