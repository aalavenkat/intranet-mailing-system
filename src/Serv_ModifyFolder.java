import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_ModifyFolder extends HttpServlet{
Statement st;
Connection con;
ServletContext scon;
String uname,nf;
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();
//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
   //Serv_SignUp ssu = new Serv_SignUp();
   //uname=ssu.name();
   HttpSession session = req.getSession(true);
   uname=session.getValue("name").toString();            
ServletOutputStream sos=res.getOutputStream();
nf=req.getParameter("newfol");
System.out.println("new folder:"+nf);
String oldf=req.getParameter("hid");
System.out.println("old folder:"+oldf);
int r=st.executeUpdate("update folders set folder='"+nf+"' where uname='"+uname+"' and folder='"+oldf+"'");
System.out.println("r :"+r);
if (r>0)
 {
    sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h2><i>One record updated with latest modifications</i></h2></body></html>");
    res.setHeader("Refresh","2;URL='Serv_Folder'");
 }
}
catch(Exception e)
{
e.printStackTrace();
}}}

