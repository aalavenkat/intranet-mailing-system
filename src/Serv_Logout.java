import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_Logout extends HttpServlet
{
   ServletContext scon;
   String name;   

   public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
   {
      try
      {
         scon=getServletContext();
         HttpSession session = req.getSession(true);
         name=session.getValue("name").toString();            
         session.removeValue("name") ; 
      }catch(Exception e){e.printStackTrace();}
      ServletOutputStream sos= res.getOutputStream();
      //sos.println("<html><title>Welcome To Intranet Mailing System</title>");
      sos.println("<body bgcolor=white background=INDTEXTB.JPG text=blue><center><img src='THANKS.GIF'></center>");
      
      sos.println("<body bgcolor= blue>");
      
      
      sos.println("<center><h2><i>'"+name+"'</i></h2></center>");
      sos.println("<center><h2><i>For Visiting Intranet Mailing System</i></h2></center>");
      sos.println("<center><a href='loginsc.html' target=_parent><h3>Login to Intranet Mailing System</h3></a></center>");
      sos.println("</body></html>");
         
    }
}

