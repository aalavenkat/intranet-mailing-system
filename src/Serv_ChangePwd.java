import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Serv_ChangePwd extends HttpServlet
{
Statement st;
Connection con;
ResultSet rs;
ServletContext scon;
String un;
public void init(ServletConfig sc)
{
try{
super.init(sc);
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try
{
//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
   //Serv_SignUp ssu = new Serv_SignUp();
   //String un=ssu.name();
   HttpSession session = req.getSession(true);
   un=session.getValue("name").toString();            

ServletOutputStream sos=res.getOutputStream();
String newp=req.getParameter("np");
int a=st.executeUpdate("Update signupdetails set passwd='"+newp+"' where uname='"+un+"'");
sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h2><i>password is changed</i></h2></body></html>");
res.setHeader("Refresh","2;URL=Serv_Inbox");
}
catch(Exception e)
{
e.printStackTrace();
}
}
}
