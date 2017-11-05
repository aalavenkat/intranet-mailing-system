import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_EditAddress extends HttpServlet{
Statement st;
Connection con;
ServletContext scon1;
String fraddress,frmid,uname,str;
int frphone;
ResultSet rs;

public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon1=getServletContext();

//Serv_SignUp ss=(Serv_SignUp)scon1.getServlet("Serv_SignUp");
   //Serv_SignUp ssu = new Serv_SignUp();
   //uname=ssu.name();
   HttpSession session = req.getSession(true);
         uname=session.getValue("name").toString();            
   
ServletOutputStream sos=res.getOutputStream();
str=req.getParameter("name");
fraddress=req.getParameter("add");
frphone=Integer.parseInt(req.getParameter("pno"));
frmid=req.getParameter("mid");
System.out.println(str);
sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h2><b><i><center>Modify Address,phone,mail_id of "+str+"</center></i></b></h2><br><br>");
sos.println("<form name=form7 method=post action='Serv_ModifyAddress'><pre>");

sos.println("<h3><i>For editing the fields overwrite that particular field</i></h3><br>");
sos.println("<b>Frd-Name </b>:   <input type=text name=nam value="+str+" readonly><br>");
sos.println("<b>Address  </b>:   <input type=text name=add1 value="+fraddress+"><br>");
sos.println("<b>Phone No.</b>:   <input type=text name=pno1 value="+frphone+"><br>");
sos.println("<b>Mail Id  </b>:   <input type=text name=mid1 value="+frmid+"><br>");
sos.println("<input type=submit name=s6 value='MODIFY-ADDRESS'>");
sos.println("</form></body></HTML>");

}
catch(Exception e){
e.printStackTrace();
}}
}
 


