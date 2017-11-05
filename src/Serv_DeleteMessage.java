import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Serv_DeleteMessage extends HttpServlet
{
Statement st=null,st1=null;
ResultSet rs=null,rs1=null;
Connection con=null;
ServletContext scon=null;
String uname;
int y;
int rcnt=0;

public void service(HttpServletRequest req, HttpServletResponse res)
						throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
st1=con.createStatement();
scon=getServletContext();
//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
      //Serv_SignUp ssu = new Serv_SignUp();
      //uname=ssu.name();
      HttpSession session = req.getSession(true);
      uname=session.getValue("name").toString();            
      
ServletOutputStream sos= res.getOutputStream();
 int cnt=Integer.parseInt(req.getParameter("h"));

 for (int i=1;i<=cnt;i++)
  {
     String chk=req.getParameter("cb"+i);
        if(chk!=null)
            {
            int mid=Integer.parseInt(chk);
            y=st1.executeUpdate("delete from newcompose where mailid="+mid);
             if(y > 0)
                {
                  rcnt++;
                  sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue>");
                  sos.println("</body></html>");
                  res.setHeader("Refresh","2;URL=Serv_SentMessages");
                }

              }
   }
                  sos.println("<h3><i>"+rcnt+ "Record(s) deleted </i></h3>");
                  rcnt=0;
}
catch(Exception e){
e.printStackTrace();
}}}

