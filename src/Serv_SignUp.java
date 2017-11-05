import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_SignUp extends HttpServlet
{
   Connection con;
   Statement st;
   static String u,p;
   ResultSet rs;
   public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
      try
      {
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
         st=con.createStatement();
         ServletOutputStream sos= res.getOutputStream();
         p=req.getParameter("pwd1");
         HttpSession session = req.getSession(true);
         session.putValue("name",req.getParameter("uname1"));
         u=session.getValue("name").toString();                            
         rs=st.executeQuery("select * from signupdetails where uname='"+u+"' and  passwd='"+p+"'");

		 if(rs.next())
         {      
            res.sendRedirect("listoptions.html");
         }
         else
         {
            sos.println("<html><body bgcolor=white background=indtextb.jpg text=red><h2><i><b>ur not a valid user! Try again using correct Loginname & Password or try registering </b></i></h2></body></html>");
            //res.setHeader("Refresh","3;URL=http://localhost:8080/SignMeUp.html");
            res.setHeader("Refresh","3;URL=loginsc.html");
         }
      }catch(Exception e)
      {
         e.printStackTrace();
      }
   }
         
   /*public String name()
   {
      return u;
   }*/
   /*public void setname()
   {
   	u=null;
   }
      
   public String pword()
   {
      return p;
   }*/
}
