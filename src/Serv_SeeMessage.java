import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_SeeMessage extends HttpServlet
{
Statement st=null,st1=null;
ResultSet rs=null;
Connection con=null;
ServletContext scon=null;
String uname;
public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
	scon=getServletContext();
	//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
	//Serv_SignUp ssu = new Serv_SignUp();
	//uname=ssu.name();
	HttpSession session = req.getSession(true);
	uname=session.getValue("name").toString();  
	
	st=con.createStatement();
	ServletOutputStream sos=res.getOutputStream();
	System.out.println("id:"+req.getParameter("id"));
	int mi=0;
	try{
		mi=Integer.parseInt(req.getParameter("id"));
		}
		catch(Exception e){}
	System.out.println("mi:"+mi);
	rs=st.executeQuery("select mailfrom,mailto,subject,maildate,mailcc,mailbcc,maildata,mailst,mailid from newcompose where mailto='"+uname+"' and mailid="+mi);
	//st.close();
	while(rs.next())
	{
	String mf=rs.getString(1);
	String mt=rs.getString(2);
	String s=rs.getString(3);
	String d=rs.getString(4);
	String c=rs.getString(5);
	String b=rs.getString(6);
	String md=rs.getString(7);
	int sta=rs.getInt(8);
	int mid=rs.getInt(9);
	sos.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
	sos.println("<form name=fo>");
	sos.println("<body bgcolor=white background=indtextb.jpg text=blue><pre>");
	//sos.println("From             :<input type=text name=t1 value='"+mf+"'><br>");
	sos.println("<img src=adv2.gif>");
	sos.println("<table cellpadding=15 ><tr><td><a href=Serv_Compose1?from="+mf+" & subj="+s+">Reply</a>");
	sos.println("</td><td><a href=Serv_Inbox>Inbox</a></td></tr></table><hr>"); 
	sos.println("<pre><br>"+mf+" wrote a mail on "+d+"<br>"); 
	sos.println("<p>"+md+"</pre>"); 
	sos.println("<hr><table cellpadding=15><tr><td><a href=Serv_Compose1?from="+mf+"&subj="+s+">Reply</a>");
	sos.println("</td><td><a href=Serv_Inbox>Inbox</a></td></tr></table>"); 
	sos.println("<center><img src=adv1.gif>"); 
	st1=con.createStatement();
	int x=st1.executeUpdate("update newcompose set mailst=0 where mailid="+mid);
	st1.close();
	sos.println("</form></body></html>");
	}
}
catch(Exception e)
{
	e.printStackTrace();
}
}
}
