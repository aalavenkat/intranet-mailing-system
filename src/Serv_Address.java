import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_Address extends HttpServlet
{
Statement st;
Connection con;
ResultSet rs;
ServletContext scon;
String uname;

public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();

ServletOutputStream sos=res.getOutputStream();
//Serv_SignUp ssu = new Serv_SignUp();
//uname=ssu.name();
HttpSession session = req.getSession(true);
uname=session.getValue("name").toString();

rs=st.executeQuery("select uname,nickname,emailid,addresses,phone from address where actname='"+uname+"'");
sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><pre>");
sos.println("<blink><h1>Address Screen</h1></blink><br><br>");
sos.println("<h2><i>Directory of '"+uname+"' :</i></h2><br><br>");
sos.println("<form name=f10 action='Serv_NewAddress'>");
sos.println("<input type=submit value='ADD'></form>");
sos.println("<a href='listoptions.html' target=_parent><b>BACK</b></a>");
sos.println("<table border=2 cellpadding=2 cellspacing=3 width=70%><tr bgcolor=brown><th>Name</th><th>Nick Name</th>");
sos.println("<th>E-Mailid</th><th>Address</th><th>Phone</th><th>Options</th></tr>");
while(rs.next())
{
String n=rs.getString(1);
String nn=rs.getString(2);
String em=rs.getString(3);
System.out.println("em:"+em);
String ad=rs.getString(4);
int p=rs.getInt(5);
sos.println("<tr><td>"+n+"</td><td>"+nn+"</td><td>"+em+"</td><td>"+ad+"</td><td>"+p+"</td><td>");
sos.println("<a href='Serv_EditAddress?name="+n+"&add="+ad+"&pno="+p+"&mid="+em+"'>EDIT</a>");
sos.println("<a href='Serv_DeleteAddress?name="+n+"'>DELETE</a></td></tr>");

}
sos.println("</table></body></html>");
}
catch(Exception e)
{
e.printStackTrace();
}}}


