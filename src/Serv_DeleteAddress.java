import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_DeleteAddress extends HttpServlet{
Statement st;
Connection con;
ServletContext scon;
String frname;

public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();

ServletOutputStream sos= res.getOutputStream();
//scon=getServletContext();
//serv_addaddress na=(serv_addaddress)scon.getServlet("serv_addaddress");
//frname=na.frdname();
String nm=req.getParameter("name");
System.out.println("nm: "+nm);
//String mailid=req.getParameter("email");
//System.out.println("mailid: "+mailid);
int r=st.executeUpdate("delete from address where uname='"+nm+"'");
if (r>0)
 {
    sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h2><i>Address of  "+nm+"  deleted successfully</i></h2></body></html>");
    res.setHeader("Refresh","2;URL='Serv_Address'");
 }
}
catch(Exception e)
{
e.printStackTrace();
}}
}
