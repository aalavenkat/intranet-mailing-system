import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Serv_Enter extends HttpServlet
{
          Statement st,st1;
          Connection con;
          ServletContext scon;
          ResultSet rs,rs1;
          String uname,sub,date1,from;
          int m,sta;
          int col=1;
          String color;

          public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
          {
          try{
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
          st=con.createStatement();
          scon=getServletContext();
//          Serv_SignUp a=(Serv_SignUp)scon.getServlet("Serv_SignUp");
          //Serv_SignUp ssu = new Serv_SignUp();
          //uname=ssu.name();
          HttpSession session = req.getSession(true);
         uname=session.getValue("name").toString();            
          ServletOutputStream sos=res.getOutputStream();
          String f=req.getParameter("se");
          sos.println("<html><head><title>Welcome To Intranet Mailing System</title></head>");
          sos.println("<script>function d()");
          sos.println("{document.f.action='Serv_DeleteCkd';");    
          sos.println("document.f.submit();}");
          sos.println("function e()");
          sos.println("{document.f.action='Serv_Enter';");
          sos.println("document.f.submit();}");
          sos.println("</script></head>");
         
          sos.println("<body bgcolor=white background=indtextb.jpg text=blue>");
          sos.println("<center><blink><h1><b>"+f+ "Screen</b></h1></blink></center><br>");
          sos.println("<h3><i>"+f+" of "+uname+" </i></h3>");
          
          sos.println("<form name=f action='Serv_MoveMessage'>");
          sos.println("<input type=button name=delete value='Delete' onClick='d()' >");
          sos.println("<input type=reset name=deselect value='DeSelect'><br><br>");
          //sos.println("<input type=submit name=move value='MOVE'>");
          //sos.println("<input type=button name=enter value='ENTER' onClick='e()'>");
          //rs=st.executeQuery("select fname from folders where uname='"+uname+"'");
          //sos.println("<select name=se>");
          //while(rs.next())
          //{
          //f1=rs.getString(1);
          //sos.println("<option value="+f1+">"+f1+"</option>");
          //}
          //sos.println("</select>");
          
          sos.println("<h4><i>Messages in "+f+"</i></h4>");
          sos.println("<table border=2>");
          sos.println("<TR bgcolor=yellow><th>Status</th><TH>X</TH><TH>From</TH><TH>Subject</Th><TH>Date</TH></TR>");
          st1=con.createStatement();
          rs1=st1.executeQuery("select mailid,mailfrom,subject,maildate,mailst from newcompose where mailto='"+uname+"' and folder='"+f+"'"); 
          int count=0;
          while(rs1.next())
          {
                    count++;
                    m=rs1.getInt(1);
                    from=rs1.getString(2);
                    sub=rs1.getString(3);
                    date1=rs1.getString(4);
                    sta=rs1.getInt(5);
                    if(col%2==0)
                       color="pink";
                    else
                       color="violet";   
                    col++;
                    sos.println("<tr bgcolor="+color+"><td>"+sta+"</td><td><input type=checkbox name=b"+count+" value="+m+"></td><td><a href='http://localhost:8080/servlet/Serv_SeeMessage?id="+m+"'>"+from+"</td></a><td>"+sub+"</td><td>"+date1+"</td></tr>");
          }
             sos.println("<input type=hidden name=hid  value="+count+">");
          sos.println("</form></table></body></html>");
          }
          catch(Exception e){
          e.printStackTrace();
          }
          }
}


