import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_Show_Folder1 extends HttpServlet
{
Statement st,st1;
ResultSet rs,rs1;
Connection con;
ServletContext scon;
ServletOutputStream sos=null;
String fold,uname,newfol,from,sub,date1,cc;
int m,y,sta;
int col=1;
String color,myfolder="";

public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
	try{
		int store=0;
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
		st=con.createStatement();
		st1=con.createStatement();
		scon=getServletContext();
		sos=res.getOutputStream();

		HttpSession session = req.getSession(true);
		uname=session.getValue("name").toString();            
		
		myfolder=req.getParameter("foldval");
		//newfol=session.getValue("foldername").toString();
		//fold=session.getValue("newfold").toString();
//		String fold=req.getParameter("foldname");
		//String mid=req.getParameter("mailid");
		//System.out.println("mailid: "+mid);
		//int id=Integer.parseInt(mid);

		//newfol=req.getParameter("folder");
		//System.out.println("newfol: "+newfol);

		ResultSet rs=st.executeQuery("select mid,mfrom,sub,mcc,mailst,maildate from newfolder where folder='"+myfolder+"'");

		sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue>");  
		sos.println("<script>function d()");
        sos.println("{document.f.action='Serv_DeleteCkd1';");    
        sos.println("document.f.submit();}");
		sos.println("</script></head>");
		sos.println("<h2><i>Contents of folder&nbsp;&nbsp;</i>"+myfolder+"</h2><br><br><br><br>");
		sos.println("<form name=f>");
		sos.println("<input type=button name=delete value='Delete' onClick='d()' style=\"width:100\">");
        sos.println("<input type=reset name=deselect value='DeSelect' style=\"width:100\"><br><br>");
		sos.println("<br><table border=2 cellpadding=4 cellspacing=5 width=80%><TR bgcolor=#00FFFF><th><font color=red>Status</th><TH><font color=red>From</TH><TH><font color=red>Subject</Th><TH><font color=red>Date</TH></TR>"); 

		int count=0;
		while(rs.next())
		{
						  if (col%2==0)
							 color="violet";
						  else
							 color="pink";
						  col++;
			count++;
			m=rs.getInt(1);
			from=rs.getString(2);
			sub=rs.getString(3);
			cc=rs.getString(4);
			sta=rs.getInt(5);
			date1=rs.getString(6);
			
			if (sub==null)
			sub="[NONE]";
			   
			if(sta==1)
			{          
			   sos.println("<tr><td><center><img src='button.gif' height=20 width=25 ></td><td>");
		//	bgcolor="+color+"
			   sos.println("<input type=checkbox name=b"+count+" value="+m+"></td><td><a href='Serv_FolderMessage?id="+m+"'>"+from+"</td></a><td>"+sub+"</td><td>"+date1+"</td></tr>");
			}
			else 
			sos.println("<tr><td width=6%><input type=checkbox name=b"+count+" value="+m+"></td><td width=24%><a href='Serv_FolderMessage?id="+m+"'>"+from+"</td></a><td width=23%>"+sub+"</td><td width=47%>"+date1+"</td></tr>");                  
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
		 catch(Exception e)
		  {
			e.printStackTrace();
		  }
	}
}






