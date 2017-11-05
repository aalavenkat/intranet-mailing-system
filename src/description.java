import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
public class description extends HttpServlet
{
   public void service(HttpServletRequest req, HttpServletResponse res)
   throws IOException, ServletException
   {
      PrintWriter pw=res.getWriter();
      res.setContentType("Text/Html");
      HttpSession session = req.getSession(true);
      //session.invalidate();
      String name=session.getValue("name").toString();      
      pw.println("<html>");
      pw.println("<body background=CONFETTI.GIF text=blue>");
      pw.println("<br>");
      pw.println("<center><h1><font color=brown> Hi "+name+ "<h2><font color=blue>");
      pw.println("<font color=red>GoTo <a href=Serv_Inbox> Inbox</a>");
      pw.println("<h2><b><i></i></b></h2><br><br><br><br><br><br>");
      pw.println("<img src=MOVED.GIF border=0>");
      pw.println("</body></html>");
   }
}         
