import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Serv_AddAddress extends HttpServlet
{
	Statement st;
	Connection con;
	ServletContext scon;
	String name,nick,mailid,address,uname;
	int phone;
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
			st=con.createStatement();
			scon=getServletContext();
			
			//Serv_SignUp ssu = new Serv_SignUp();
			//uname=ssu.name();
			HttpSession session = req.getSession(true);
			uname=session.getValue("name").toString();
			
			ServletOutputStream sos=res.getOutputStream();
			name=req.getParameter("nam");
			nick=req.getParameter("nname");
			mailid=req.getParameter("mid");
			address=req.getParameter("add");
			String p=req.getParameter("pno");
			phone=Integer.parseInt(p);
			//System.out.println("insert into address values('"+uname+"','"+name+"','"+nick+"','"+mailid+"','"+address+"',"+phone+")");
			int i=st.executeUpdate("insert into address values('"+uname+"','"+name+"','"+nick+"','"+mailid+"','"+address+"',"+phone+")");
			sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h2><i>"+name+"'s Address added successfully</i></h2></body></html>"); 
			res.setHeader("Refresh","2;URL='Serv_Address'");
		}
		catch(Exception e)
		{
			e.printStackTrace();     
		}
	}
	public String frdname()
	{
		return name;
	}
	public String frdnname()
	{
		return nick;
	}
	public String frdmid()
	{
		return mailid;
	}
	public String frdaddress()
	{
		return address;
	}
	public int frdphone()
	{
		return phone;
	}
}
