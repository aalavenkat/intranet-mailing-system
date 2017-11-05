import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_SeeMessage1 extends HttpServlet{
Statement st=null,st1=null;
ResultSet rs=null;
Connection con=null;
ServletContext scon=null;
String uname;
int x;
HttpSession session=null;

public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
scon=getServletContext();
//try
//{

//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
//Serv_SignUp ssu = new Serv_SignUp();
//uname=ssu.name();
session = req.getSession(true);
uname=session.getValue("name").toString();            
//}
//catch(Exception e)
//{
//    uname ="vinodkumar";
//}

st=con.createStatement();
ServletOutputStream sos=res.getOutputStream();
int mi=Integer.parseInt(req.getParameter("id"));
rs=st.executeQuery("select mailfrom,mailto,subject,maildate,mailcc,mailbcc,maildata,mailst from newcompose where mailfrom='"+uname+"' and mailid="+mi);
while(rs.next())
{
String mf=rs.getString(1);
session.putValue("mfrom",mf);//later added
String mt=rs.getString(2);
String s=rs.getString(3);
String d=rs.getString(4);
String c=rs.getString(5);
String b=rs.getString(6);
String md=rs.getString(7);
//int sta=rs.getInt(8);
sos.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
sos.println("<form name=fo>");
sos.println("<body bgcolor=white background=indtextb.jpg text=blue><pre>");
//sos.println("From             :<input type=text name=t1 value='"+mf+"'><br>");
sos.println("From             :  "+mf+"<br>"); 

//sos.println("To               :<input type=text name=t2 value='"+mt+"'><br>");
sos.println("To               :  "+mt+"<br>"); 
//sos.println("Cc               :<input type=text name=t3 value='"+c+"'><br>");
sos.println("Cc               :  "+c+"<br>"); 

//sos.println("Bcc              :<input type=text name=t4 value='"+b+"'><br>");
sos.println("Bcc              :  "+b+"<br>"); 

sos.println("Subject          :  "+s+"<br><br>");
sos.println("<textarea rows=10 cols=60 name=t6 readonly>"+md+"</textarea>");
//if(sta==1)
//{
st1=con.createStatement();
x=st1.executeUpdate("update newcompose set mailst=0");
st1.close();
//st2=con.createStatement();
//int y=st2.executeUpdate("update newfolder set stat=0");
//st2.close();
sos.println("</form></body></html>");
}}
catch(Exception e)
{
e.printStackTrace();
}
}}
