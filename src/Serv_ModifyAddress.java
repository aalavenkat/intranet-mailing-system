import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_ModifyAddress extends HttpServlet
{
	Statement st;
	Connection con;
	ServletContext scon;
	String frname,newfraddress,newfrmid;
	int newfrphone;
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
			st=con.createStatement();
			scon=getServletContext();
			
			ServletOutputStream sos=res.getOutputStream();
			String str=req.getParameter("nam");
			System.out.println("str:"+str);
			newfraddress=req.getParameter("add1");
			System.out.println("new address:"+newfraddress);
			int newfrphone=Integer.parseInt(req.getParameter("pno1"));
			System.out.println("new phone: "+newfrphone);
			newfrmid=req.getParameter("mid1");
			System.out.println("new frm id:"+newfrmid);
			System.out.println("str="+str+"add="+newfraddress+"ph="+newfrphone+"id="+newfrmid);
			int r=st.executeUpdate("update address set addresses='"+newfraddress+"',phone="+newfrphone+",emailid='"+newfrmid+"' where uname='"+str+"'");
			if (r>0)
			 {
				    sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h2><i>"+str+"'s Address Updated Successfully</i></h2></body></html>"); 
				    res.setHeader("Refresh","2;URL='Serv_Address'");
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

