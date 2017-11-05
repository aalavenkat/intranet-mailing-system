import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_Option extends HttpServlet{
Statement st;
Connection con;
ResultSet rs;
ServletContext scon;
public void init(ServletConfig sc){
try{
super.init(sc);
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();
}
catch(Exception e){
e.printStackTrace();
}
}
public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{

String un, npwd;
//try{

//	Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
  //  Serv_SignUp ssu = new Serv_SignUp();
//	un = ssu.name();
HttpSession session = req.getSession(true);
         un=session.getValue("name").toString();            
    rs=st.executeQuery("select passwd from signupdetails where uname='"+un+"'");

npwd="";
try
{
	rs.next();
    npwd=rs.getString(1);
}
catch(Exception e){}

//}
//catch(Exception e)
//{
//	password ="vinod";
//}

ServletOutputStream sos= res.getOutputStream();
sos.println("<html><head><title>Welcome to Intranet Mailing System></title></head>");
//sos.println("<script><!-- function oncheck(){if(document.form5.op.value=="+ npwd +") return true ; else	return false;}// --></script></head>");
sos.println("<body bgcolor=white background=indtextb.jpg text=blue><h1><center><blink>Change PassWord Screen</blink></center></h1>");
//sos.println("<form name=form5 method=post action='http://localhost:8080/servlet/Serv_ChangePwd' onsubmit = \"return oncheck()\">");
sos.println("<form name=form5 method=post action='Serv_ChangePwd'>");
sos.println("<pre><b><h4><center>Allows To Change PassWord</center></h4></b><BR><BR>");
sos.println("<b>Old PassWord       </b>:<input type=password name=op value="+npwd+"><br>");
sos.println("<b>New PassWord </b>      :<input type=password name=np value=''><br><br>");
sos.println("<input type=submit name=s3 value='CHANGE'></form></body></HTML>");
}
catch(Exception e)
{
e.printStackTrace();
}
}
}

