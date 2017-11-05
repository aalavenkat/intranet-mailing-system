import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Serv_Compose extends HttpServlet
{
Connection con=null;
Statement st=null;
ServletContext scon=null;
ResultSet rs=null;
ServletOutputStream sos=null;


public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
{
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
st=con.createStatement();
scon=getServletContext();
String name=null;


//	Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
	//Serv_SignUp ssu = new Serv_SignUp();
	//name=ssu.name();
	HttpSession session = req.getSession(true);   
	name=session.getValue("name").toString();   
	
	//String mailfrom=session.getValue("mfrom").toString();
   /*y
   {      
      //Mto=session.getValue("MFrom").toString();      
      Mto=req.getParameter("from");
   }catch(Exception e){}*/
   
ServletOutputStream sos= res.getOutputStream();
sos.println("<html><title>Intranet Mailing System</title>");
sos.println("<body bgcolor=white background=INDTEXTB.JPG text=blue><center><img src=ADV3.GIF border=0></center>");
sos.println("<pre>");
//sos.println("<img src='MAIL.gif'><img src='http://localhost:8081/IntranetMailserver/MAIL.gif'><img src='http://localhost:8081/IntranetMailserver/MAIL.gif'>");
sos.println("<form name=form4 method=post action='Serv_Send'>");
//if(Mto!=null)
//{  
   sos.println("To                  :<input type=text name=to value= ''><br>");   
   sos.println("Subject             :<input type=text name=subject value=''><br>");
   sos.println("Cc                  :<input type=text name=cc value=''><br>");
   sos.println("Bcc                 :<input type=text name=bcc value=''><br><br>");
   sos.println("<a href=Upload.html><font face=verdana><b>Attach Files</b></font></a>");	
//sos.println("Mail data to be sent:<br>");
sos.println("<textarea name=maildata rows=10 cols=60></textarea><br>");
sos.println("<input type=submit name=submit1 value='SEND'><br>");

   rs=st.executeQuery("select actname,emailid from address where uname='"+name+"'");  
   if (rs.next())
   {
      sos.println("<SELECT name=se onclick=getName(value) style=\"LEFT: 400px; POSITION: absolute; TOP: 160px; BACKGROUND-COLOR:#b464ff;width:130px;FONT-WEIGHT:BOLD\"");      
      sos.println("SIZE=5 name=List1 value=\"List1\">");   
      do
      {         
         sos.println("<OPTION value="+rs.getString(2)+" >"+rs.getString(1)+"</OPTION>");               
      }while(rs.next());
      sos.println("</SELECT>");    
    }
       
    sos.println("</form></body></html>");
    sos.println("<script language=javascript>");
    sos.println("function getName(Myname){");
    sos.println("document.form4.to.value=Myname");
    sos.println("}</script>");
   

      
}
catch(Exception e)
	{
		e.printStackTrace();
	}
}
}


