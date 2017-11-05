import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_Inbox extends HttpServlet
{
   Statement st=null,st1=null;
   Connection con=null;
   ServletContext scon=null;
   ResultSet rs=null,rs1=null;
   String newfol=null,f1=null,uname=null,sub=null,from=null,date1=null;
   int sta,m;//,col=1;
   //String color=null;
   
   public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
   {
      try
      {
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
         st=con.createStatement();
         scon=getServletContext();
         //Serv_SignUp a = new Serv_SignUp();
         //uname=a.name();
         HttpSession session = req.getSession(true);
         uname=session.getValue("name").toString();

		 //newfol=session.getValue("foldername").toString();

         ServletOutputStream sos=res.getOutputStream();         
         sos.println("<html><head><title>Welcome To Intranet Mailing System</title>");
         sos.println("<script>function d()");
         sos.println("{document.f.action='Serv_DeleteCkd';");    
         sos.println("document.f.submit();}");
         sos.println("function e()");
         sos.println("{document.f.action='Serv_Enter';");
         sos.println("document.f.submit();}");
         sos.println("</script></head>");
         sos.println("<body bgcolor=white background=indtextb.jpg text=blue>");
         sos.println("<blink><h1><b><i>Inbox Screen of "+uname+" </i></b></h1></blink><br>");

		 int rscount=0;
		 ResultSet rs=st.executeQuery("select mailid from newcompose where mailto='"+uname+"' and folder='inbox'");
		 while(rs.next())
		 {
			 rscount++;
		 }
		 
		 sos.println("<b>Total Messages:</b> "+rscount+"<br><br>");
         sos.println("<br><a href=www.monsterindia.com target=new><img src=jobsearch1.gif border=0></a><br><br>");
         //sos.println("<!--img src=tiger.gif border=0>");
         sos.println("<form name=f action='Serv_MoveFolder'>");
         sos.println("<input type=button name=delete value='Delete' onClick='d()' style=\"width:100\">");
         sos.println("<input type=reset name=deselect value='DeSelect' style=\"width:100\">");
         sos.println("&nbsp;&nbsp;&nbsp;&nbsp;<input type=submit name=move value='Move To' style=\"width:100\">");
       //  sos.println("<input type=button name=enter value='ENTER' onClick='e()' style=\"width:100\">");
         rs=st.executeQuery("select folder from folders where uname='"+uname+"'");
         
		 sos.println("<select name=se style=\"width:150\">");
         while(rs.next())
         {
         f1=rs.getString(1);
         sos.println("<option value="+f1+">"+f1+"</option>");
         }
         sos.println("</select><br>");
		 sos.println("<pre>                              <font face=verdana color=red>select a folder then go for MoveTo</font></pre>");	
         
         //sos.println("<h3><i>Messages in Inbox:</i></h3>        <img src='http://localhost:8080/chkmail.gif'>");
         /*sos.println("<br/><br/><br/><table cellspacing=3 border=1 bgcolor=#e6e6e6>");*/
         //sos.println("<br/><br/><table cellspacing=3 bgcolor=white background=indtextb.jpg>"); 
         
		 rs.close();
		 st.close();
		 sos.println("<br><table border=2 cellpadding=4 cellspacing=5 width=80%><TR bgcolor=#00FFFF><th><font color=red>Status</th><TH><font color=red>From</TH><TH><font color=red>Subject</Th><TH><font color=red>Date</TH></TR>"); 
		 //<TH><input type=checkbox name=chkall onclick=chkit()></TH>
         st1=con.createStatement();
         rs1=st1.executeQuery("select mailid,mailfrom,subject,maildate,mailst from newcompose where mailto='"+uname+"'");

		 // and folder='inbox'");
											
         int count=0;
         while(rs1.next())
         {
                /*  if (col%2==0)
                     color="violet";
                  else
                     color="pink";
                  col++;*/
                  count++;
                  m=rs1.getInt(1);
                  from=rs1.getString(2);
                  sub=rs1.getString(3);
                  date1=rs1.getString(4);
                  sta=rs1.getInt(5);
                  if (sub==null)
                     sub="[NONE]";
                  
                  if(sta==1)
                  {          
                     sos.println("<tr><td><center><img src='button.gif' height=20 width=25 ></td><td>");
//					  bgcolor="+color+"
                     sos.println("<input type=checkbox name=b"+count+" value="+m+"></td><td><a href='Serv_SeeMessage?id="+m+"'>"+from+"</td></a><td>"+sub+"</td><td>"+date1+"</td></tr>");
                  }
                  else 


                     sos.println("<tr><td width=6%><input type=checkbox name=b"+count+" value="+m+"></td><td width=24%><a href='Serv_SeeMessage?id="+m+"'>"+from+"</td></a><td width=23%>"+sub+"</td><td width=47%>"+date1+"</td></tr>");                  
//				   bgcolor="+color+"
         }
         sos.println("<script language=javascript>");
         sos.println("function chkit(){");
         for (int i=1;i<=count;i++)
            sos.println("document.f.b"+i+".checked=document.f.chkall.checked;");
         sos.println("}</script>");         
         sos.println("<input type=hidden name=hid  value="+count+">");
		 sos.println("</form>");
         sos.println("</table></body></html>");
         }
      catch(Exception e){
				e.printStackTrace();
					}
 }
}
   
   







   /*
   import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_Inbox extends HttpServlet
{
   Statement st=null,st1=null;
   Connection con=null;
   ServletContext scon=null;
   ResultSet rs=null,rs1=null;
   String f1=null,uname=null,sub=null,from=null,date1=null;
   int sta,m,col=1;
   String color=null;
   
   public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
   {
      try
      {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         con=DriverManager.getConnection("jdbc:oracle:thin:@server:1521:server","intmail","intmail");
         st=con.createStatement();
         scon=getServletContext();
         //Serv_SignUp a = new Serv_SignUp();
         //uname=a.name();
         HttpSession session = req.getSession(true);
         uname=session.getValue("name").toString();

         ServletOutputStream sos=res.getOutputStream();         
         sos.println("<html><head><title>Welcome To Intranet Mailing System</title>");
         /*sos.println("<script>function d()");
         sos.println("{document.f.action='Serv_DeleteCkd';");    
         sos.println("document.f.submit();}");
         sos.println("function e()");
         sos.println("{document.f.action='http://localhost:8081/IntranetMailserver/Serv_Enter';");
         sos.println("document.f.submit();}");
         sos.println("</script></head>");*/
    /*     sos.println("<body bgcolor=white background=indtextb.jpg text=6F6F00>");
         //sos.println("<center><blink><h1><b>Inbox Screen of "+uname+" </b></h1></blink></center><br>");
         sos.println("<a href=www.monsterindia.com target=new><img src=http://localhost:8081/IntranetMailserver/jobsearch1.gif border=0></a>");
         //sos.println("<!--img src=http://localhost:8081/IntranetMailserver/tiger.gif border=0>");
    /*   sos.println("<form name=f action='http://localhost:8081/IntranetMailserver/Serv_MoveMessage'>");
         sos.println("<input type=button name=delete value='Delete' onClick='d()' style=\"width:100\">");
         sos.println("<input type=reset name=deselect value='DeSelect' style=\"width:100\"><br><br>");
         sos.println("<input type=submit name=move value='MOVE' style=\"width:100\">");
         sos.println("<input type=button name=enter value='ENTER' onClick='e()' style=\"width:100\">");
         rs=st.executeQuery("select folder from folders where uname='"+uname+"'");
         sos.println("<select name=se style=\"width:150\">");
         while(rs.next())
         {
         f1=rs.getString(1);
         sos.println("<option value="+f1+">"+f1+"</option>");
         }
         sos.println("</select>");
         
         //sos.println("<h3><i>Messages in Inbox:</i></h3>        <img src='http://localhost:8080/chkmail.gif'>");
         /*sos.println("<br/><br/><br/><table cellspacing=3 border=1 bgcolor=#e6e6e6>");*/
    /*    sos.println("<br/><br/><hr/><table cellspacing=3 bgcolor=white background=indtextb.jpg>"); 
         sos.println("<TR><th style=\"width:10\"><font color=red>Status</th><TH><input type=checkbox name=chkall onclick=chkit()></TH><TH  style=\"width:380\"><font color=red>From</TH><TH  style=\"width:250\"><font color=red>Subject</Th><TH  style=\"width:150\"><font color=red>Date</TH></TR>"); 
         st1=con.createStatement();
         rs1=st1.executeQuery("select mailid,mailfrom,subject,maildate,mailst from newcompose where mailto='"+uname+"'");
		 //and folder='inbox'"); 
     /*    int count=0;
         while(rs1.next())
         {
                  if (col%2==0)
                     color="violet";
                  else
                     color="pink";
                  col++;
                  count++;
                  m=rs1.getInt(1);
                  from=rs1.getString(2);
                  sub=rs1.getString(3);
                  
                  date1=rs1.getDate(4).toString();
                  sta=rs1.getInt(5);
                  if (sub==null)
                     sub="[NONE]";
                  
                  if(sta==1)
                  {          
                     sos.println("<tr bgcolor="+color+"><td><center><img src='http://localhost:8081/IntranetMailserver/button.gif' height=20 width=25 ></td><td>");
                     sos.println("<input type=checkbox name=b"+count+" value="+m+"></td><td><h3><a href='http://localhost:8081/IntranetMailserver/Serv_SeeMessage?id="+m+">"+from+"</td></a><td><h3>"+sub+"</td><td><h3>"+date1+"</td></tr>");
                  }
                  else 
                     sos.println("<tr bgcolor="+color+"><td><center></td><td><input type=checkbox name=b"+count+" value="+m+"></td><td><h3><a href='http://localhost:8081/IntranetMailserver/Serv_SeeMessage?id="+m+">"+from+"</td></a><td><h3>"+sub+"</td><td><h4>"+date1+"</td></tr>");                  
         }
         sos.println("<script language=javascript>");
         sos.println("function chkit(){");
         for (int i=1;i<=count;i++)
            sos.println("document.f.b"+i+".checked=document.f.chkall.checked;");
         sos.println("}</script>");         
         sos.println("<input type=hidden name=hid  value="+count+">");
         sos.println("</form></table><hr/></body></html>");
         }
      catch(Exception e){
				e.printStackTrace();
					}
 }
}
   
   */