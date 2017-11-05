import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_NewAddress extends HttpServlet{
Connection con;
Statement st;
ServletContext scon;
public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();
String uname;
try
{
//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
//uname=ss.name();
         HttpSession session = req.getSession(true);
         uname=session.getValue("name").toString();            
}
catch(Exception e)
{
    uname ="vinodkumar";
}

ServletOutputStream sos = res.getOutputStream();
sos.println("<html><head><title>Welcome To Intranet Mailing System</title></head>");
sos.println("<h1><center><blink>New Address Screen</blink></center></h1><body bgcolor=white background=indtextb.jpg text=blue><h3>Adding new address to '"+uname+"'<b>Address Box</b></h3><br>");
sos.println("<form name=form8 method=post action='Serv_AddAddress'><pre><b><h3><center>Allows To Add Name,Nick Name,Mail_Id</center></h3></b>");
sos.println("<b>Name     </b>:   <input type=text name=nam value=''><br>");
sos.println("<b>Nick Name</b>:   <input type=text name=nname value=''><br>");
sos.println("<b>E-mail Id</b>:   <input type=text name=mid value=''><br>");
sos.println("<b>Address  </b>:   <input type=text name=add value=''><br>");
sos.println("<b>Phone No.</b>:   <input type=text name=pno value=''><br><br>");
sos.println("<input type=submit name=s7 value='ADD-ADDRESS'></form></body></HTML>");
}
catch(Exception e)
{
e.printStackTrace();
}}}
 
