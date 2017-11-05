import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_FolderMessage extends HttpServlet
{
		Statement st=null,st1=null;
		ResultSet rs=null;
		Connection con=null;
		ServletContext scon=null;
		String uname;
		int x;
		HttpSession session=null;

		public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
		{
			try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
				scon=getServletContext();
				//try
				//{

				//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
				//Serv_SignUp ssu = new Serv_SignUp();
				//uname=ssu.name();
				session = req.getSession(true);
				uname=session.getValue("name").toString();            
				//String newfol=session.getValue("foldername").toString();
				int foldmid=Integer.parseInt(req.getParameter("id"));
				//}
				//catch(Exception e)
				//{
				//    uname ="vinodkumar";
				//}

				st=con.createStatement();
				ServletOutputStream sos=res.getOutputStream();
				//int mi=Integer.parseInt(req.getParameter("id"));
				rs=st.executeQuery("select mfrom,mto,sub,mcc,mdata,folder,mailst,maildate from newfolder where mid="+foldmid);
				while(rs.next())
				{
				String mf=rs.getString(1);
				//session.putValue("mfrom",mf);//later added
				String mt=rs.getString(2);
				String s=rs.getString(3);
				String cc=rs.getString(4);
				String folddata=rs.getString(5);
				String foldname=rs.getString(6);
				String md=rs.getString(8);
				//int sta=rs.getInt(8);
				sos.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
				sos.println("<form name=fo>");
				sos.println("<body bgcolor=white background=indtextb.jpg text=blue><pre>");
				//sos.println("From             :<input type=text name=t1 value='"+mf+"'><br>");
				sos.println("<img src=adv2.gif>");
				//sos.println("<table cellpadding=15 ><tr><td><a href=Serv_Compose1?from="+mf+" & subj="+s+">Reply</a>");
				sos.println("</td><td><a href=Serv_Show_Folder>Folder</a></td></tr></table><hr>"); 
				//sos.println("</td><td><a href=Serv_Show_Folder1>Folder</a></td></tr></table><hr>"); 
				sos.println("<pre><br>"+mf+" wrote a mail on "+md+"<br>"); 
				sos.println("<p>"+folddata+"</pre><hr>"); 
				//sos.println("<hr><table cellpadding=15><tr><td><a href=Serv_Compose1?from="+mf+"&subj="+s+">Reply</a>");
				sos.println("</td><td><a href=Serv_Show_Folder>Folder</a></td></tr></table>"); 
				//sos.println("</td><td><a href=Serv_Show_Folder1>Folder</a></td></tr></table>"); 
				sos.println("<center><img src=adv1.gif>"); 
				st1=con.createStatement();
				x=st1.executeUpdate("update newfolder set mailst=0");
				st1.close();
				//st2=con.createStatement();
				//int y=st2.executeUpdate("update newfolder set stat=0");
				//st2.close();
				sos.println("</form></body></html>");
			  }
		  }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
