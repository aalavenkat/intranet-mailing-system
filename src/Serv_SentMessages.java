import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_SentMessages extends HttpServlet
{
Statement st=null;
Connection con=null;
ServletContext scon=null;
ResultSet rs=null;
String uname,s="",d="",mt="",mcc="";
int m,col=1;
String color,bgcolor;
public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();

HttpSession session = req.getSession(true);
uname=session.getValue("name").toString();            

ServletOutputStream sos=res.getOutputStream();
sos.println("<html><head><title>Welcome To Intranet Mailing System</title>");
sos.println("<script>function d1(){");
sos.println("document.ff.action='Serv_DeleteMessage';");
sos.println("document.ff.submit();");
sos.println("}</script></head>");
sos.println("<body bgcolor=white background=indtextb.jpg text=blue><h3><i>Welcome '"+uname+"' @Intranet Mailing System</i></h3>");
sos.println("<form name=ff><input type=button name=del value='Delete' onClick='d1()'> <input type=reset name=des value='DeSelect'>");
sos.println("<h3><i>Messages sent by "+uname+" till now</i></h3><br><br>");
sos.println("<table border=2 cellpadding=4 cellspacing=5 width=80%><tr bgcolor=brown><th>X</th><th>To</th><th>Subject</th><th>Date</th></tr>");
rs=st.executeQuery("select mailid,mailto,subject,mailcc,maildate from newcompose where mailfrom='"+uname+"'");
int count=0;
while(rs.next())
{
count++;
m=rs.getInt(1);
mt=rs.getString(2);
s=rs.getString(3);
mcc=rs.getString(4);
d=rs.getString(5);
if(col%2==0)
   color="pink";
else
   color="violet";  bgcolor="+color+";  
  col++;
sos.println("<tr><td><input type=checkbox name=cb"+count+" value="+m+"></td><td><a href='Serv_SeeMessage1?id="+m+"'>"+mt+"</a></td><td>"+s+"</td><td>"+d+"</td></tr>"); 
}
sos.println("<input type=hidden name=h value="+count+">");
sos.println("</table></form></body></html>");
}
catch(Exception e)
{
e.printStackTrace();
}
}}






/*
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_SentMessages extends HttpServlet
{
Statement st=null;
Connection con=null;
ServletContext scon=null;
ResultSet rs=null;
String uname,s="",d="",mt="";
int m;//,col=1;
//String color;
public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@server:1521:server","intmail","intmail");
st=con.createStatement();
scon=getServletContext();
//try
//{
//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
//Serv_SignUp ssu = new Serv_SignUp();
//uname=ssu.name();
HttpSession session = req.getSession(true);
uname=session.getValue("name").toString();            
//}
//catch(Exception e)
//{
//    uname ="vinodkumar";
//}

ServletOutputStream sos=res.getOutputStream();
sos.println("<html><head><title>Welcome To Intranet Mailing System</title>");
sos.println("<script>function d1(){");
sos.println("document.ff.action='http://localhost:8081/IntranetMailserver/Serv_DeleteMessage';");
sos.println("document.ff.submit();");
sos.println("}</script></head>");
sos.println("<body bgcolor=white background=indtextb.jpg text=blue><h3><i>Welcome '"+uname+"' @Intranet Mailing System</i></h3>");
sos.println("<form name=ff><input type=button name=del value='Delete' onClick='d1()'> <input type=reset name=des value='DeSelect'>");
sos.println("<h3><i>Messages sent by "+uname+" till now</i></h3><br><br>");
sos.println("<table border=2 cellpadding=4 cellspacing=5 width=80%><tr bgcolor=brown><th>X</th><th>To</th><th>Subject</th><th>Date</th></tr>");
rs=st.executeQuery("select mailid,mailto,subject,maildate from newcompose where mailfrom='"+uname+"'");
int count=0;
while(rs.next())
{
count++;
m=rs.getInt(1);
mt=rs.getString(2);
s=rs.getString(3);
d=rs.getString(4);
/*if(col%2==0)
   color="pink";
else
   color="violet";  bgcolor="+color+"   
  col++;*/
/*sos.println("<tr><td><input type=checkbox name=cb"+count+" value="+m+"></td><td><a href='http://localhost:8081/IntranetMailserver/Serv_SeeMessage1?id="+m+"'>"+mt+"</a></td><td>"+s+"</td><td>"+d+"</td></tr>"); 
}
//sos.println("<input type=hidden name=h value="+count+">");
sos.println("</table></form></body></html>");
}
catch(Exception e)
{
e.printStackTrace();
}
}}*/

