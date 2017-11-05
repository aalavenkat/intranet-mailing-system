import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_Folder extends HttpServlet
{
Connection con=null;
ResultSet rs=null;
ServletContext scon=null;
String uname,f1;
String fol[]=new String[25];
int t[]=new int[25];
int n[]=new int[25];
int col=1;
String color;
public void service(HttpServletRequest req, HttpServletResponse res)
					throws ServletException,IOException
{
		try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
		ServletOutputStream sos=res.getOutputStream();
		Statement st=con.createStatement();
		scon=getServletContext();

		HttpSession session = req.getSession(true);
		uname=session.getValue("name").toString();  
		
		rs=st.executeQuery("select folder from folders where uname='"+uname+"'");
		System.out.println("rs: "+rs);
		//System.out.println("select folder from folders where uname='"+uname+"'");

			int i=0;
				  while(rs.next())
				  {
				  f1=rs.getString(1);
				  fol[i]=f1;
				  System.out.println("fol[i]: "+fol[i]);
				   i++;

				  }
				  st.close();

		sos.println("<html><head><title>Welcome To Intranet Mailing System</title></head>");
		sos.println("<body bgcolor=white background=indtextb.jpg text=#00FFFF>");
		sos.println("<center><blink><h1><b><font color=blue>Folder Screen</font></b></h1></blink></center><br>");
		sos.println("<form name=fm method=post action='Serv_NewFolder'><input type=submit value='ADD-FOLDER'></form>");
		sos.print("<a href='listoptions.html' target=_parent><b>BACK</b></a>");
		sos.println("<table border=2>");
		sos.println("<tr bgcolor=brown><th>Folder Name</th><th>New Mails</th><th>Total Mails</th><th>Options</th></tr>");

		for(int j=0;j<i;j++)
		{

		 int tmails=0,nmails=0;
		 Statement st1=con.createStatement();
		 ResultSet rs1=st1.executeQuery("select mailst from newcompose where mailto='"+uname+"' and folder='"+fol[j]+"'");
		//System.out.println("rs1: "+rs1);
		////
		//System.out.println("select mailst from newcompose where mailto='"+uname+"' and folder='"+fol[j]+"'");
		if(col%2==0)
		   color="pink";
		else
		   color="violet";   
		   col++;


		/////
		while(rs1.next())
		   {
		   int s=rs1.getInt(1);
			   tmails++;
		   if(s==1)
			   nmails++;

		   }
		 t[j]=tmails;
		 n[j]=nmails;

		sos.println("<tr bgcolor="+color+"><td><a href=Serv_Show_Folder?foldval="+fol[j]+">"+fol[j]+"</td><td>"+n[j]+"</td><td>"+t[j]+"</td>");
		sos.println("<td><a href='Serv_EditFolder?fol="+fol[j]+"'>EDIT</a>");
		sos.println("<a href='Serv_DeleteFolder?fol="+fol[j]+"'>DELETE</a></td></tr>");
		st1.close();
		//rs1.close();
		}
		 sos.println("</table></form></body></html>");
		}
catch(Exception e)
{
e.printStackTrace();
}
}
}

