import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_MoveMessage extends HttpServlet
{
Statement st;
Connection con;
ResultSet rs;
ServletContext scon;
String uname,fl;
public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
int y;
int store=0;
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();
//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
   //Serv_SignUp ssu = new Serv_SignUp();
   //uname=ssu.name();
   HttpSession session = req.getSession(true);
         uname=session.getValue("name").toString();            
ServletOutputStream sos=res.getOutputStream();
String soption=req.getParameter("se");
int cnt=Integer.parseInt(req.getParameter("hid"));
for (int i=1;i<=cnt;i++)
{
     String chk=req.getParameter("b"+i);
        if(chk!=null)
            {
            int mid=Integer.parseInt(chk);
      y=st.executeUpdate("update newcompose set folder='"+soption+"'  where mailid="+mid);
             if(y > 0)
                {
                  store++;
                  sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue>");  
                  sos.println("</body></html>");
                  res.setHeader("Refresh","2;URL='Serv_Inbox'");
                }
                }
   }
                  sos.println("<h3><i>"+store+" Message(s) moved into "+soption+" </i></h3>");
}
catch(Exception e){
e.printStackTrace();
}}}

