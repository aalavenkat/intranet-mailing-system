 import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Serv_AddFolder extends HttpServlet
{
Statement st;
Connection con;
ServletContext scon;
String uname,newf;
     
public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{ 
     try{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
        st=con.createStatement();
        scon=getServletContext();
//        Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
		
		   //Serv_SignUp ssu = new Serv_SignUp();
        //uname=ssu.name();
        HttpSession session = req.getSession(true);
         uname=session.getValue("name").toString();
		 newf=req.getParameter("fname");
		 
	    session.putValue("foldername",newf);
		String newfol=session.getValue("foldername").toString();
		System.out.println("newf: "+newfol);

        ServletOutputStream sos=res.getOutputStream();
        //newf=req.getParameter("fname");
		//System.out.println("newf: "+newf);
        int i=st.executeUpdate("insert into folders values('"+uname+"','"+newf+"')");
		//System.out.println("insert into folders values('"+uname+"','"+newf+"')");
		System.out.println("i:"+i);
        sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><h2><i>Folder "+newf+" Created Successfully</i></h2></body></html>"); 
        res.setHeader("Refresh","2;URL='Serv_Inbox'");
        }
      catch(Exception e)
      {
      e.printStackTrace();
      }
   }
}
