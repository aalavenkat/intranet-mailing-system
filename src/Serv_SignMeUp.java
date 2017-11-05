import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Serv_SignMeUp extends HttpServlet 
{
Connection con=null;
Statement st=null,st1=null;
PreparedStatement ps=null;
ResultSet rs=null;
boolean b;
ServletOutputStream sos;

public void init(ServletConfig sc) throws ServletException
{
try{
super.init(sc);
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();

}
catch(Exception e){
e.printStackTrace();
}
}
public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
try{
sos=res.getOutputStream();
String uname1=req.getParameter("uname");
String pwd1=req.getParameter("pwd");
String a=req.getParameter("age");
int age1=Integer.parseInt(a);
String sex1=req.getParameter("r1");
String city1=req.getParameter("city");
String state1=req.getParameter("state");
String pincode=req.getParameter("pin");
int r=Integer.parseInt(pincode);
String nation1=req.getParameter("nation");
ResultSet rs=st.executeQuery("select * from signupdetails where uname='"+uname1+"'");

if(rs.next())
{
sos.println("<html><body><h2>Username already exists. Try giving another name</h2></body></html>");
res.setHeader("Refresh","3;URL=signmeup.html");

}
else
{
st1=con.createStatement();
//int i=st1.executeUpdate("insert into signupdetails values('"+uname1+"','"+pwd1+"',"+age1+",'"+sex1+"','"+city1+"','"+state1+"',"+r+",'"+nation1+"')");

ps=con.prepareStatement("insert into signupdetails values(?,?,?,?,?,?,?,?)");
ps.setString(1,uname1);
ps.setString(2,pwd1);
ps.setInt(3,age1);
ps.setString(4,sex1);
ps.setString(5,city1);
ps.setString(6,state1);
ps.setInt(7,r);
ps.setString(8,nation1);

ps.execute();
//int n=ps.executeUpdate();

//if(n > 0)
//	{
	sos.println("<h1> hello </h1>");
	sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h1>Congrats ! <br>for sucessfully registering urself with the Intranet Mailing System </h1></body></html>");
	res.setHeader("Refresh","2;URL=loginsc.html");
	ps=con.prepareStatement("insert into folders values(?,?)");
	ps.setString(1,uname1);
	ps.setString(2,"Trash");
	ps.execute();
	//}
	//res.setHeader("Refresh","2;URL=http://localhost:8081/IntranetMailserver/loginsc.html");

/*ps=con.prepareStatement("insert into folders values(?,?)");
ps.setString(1,uname1);
ps.setString(2,"Trash");
ps.execute();*/

//sos.println("<h1> hello </h1>");
//sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h1>Congrats ! <br>for sucessfully registering urself with the Intranet Mailing System </h1></body></html>");
//res.setHeader("Refresh","2;URL=http://localhost:8081/IntranetMailserver/loginsc.html");
}}
catch(Exception e){
e.printStackTrace();
}
}}
