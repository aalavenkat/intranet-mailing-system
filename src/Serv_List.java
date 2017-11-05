//import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Serv_List extends HttpServlet
{
	ServletContext scon=null;
    String newfol=null,f1=null,uname=null,sub=null,from=null,date1=null,folder=null;
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
   {
	
		try{
		 scon=getServletContext();
		 HttpSession session = req.getSession(true);
		 uname=session.getValue("name").toString();            

		ServletOutputStream sos=res.getOutputStream();
		sos.println("<html><head><title>Welcome To Intranet Mailing System</title><!--mstheme--><link rel=stylesheet href=copy1111.css>");
		sos.println("<meta name=Microsoft Theme content=copy-of-industrial 1111>");
		sos.println("</head><body leftmargin=0 Topmargin=0><!--table cellpadding=16 align=center-->");
		sos.println("<pre><i><b><a href=Serv_Logout target=_parent><img src=imsimages1/Logout.jpg border=0 width=108 height=36></a></b></i></pre>");
		sos.println("<pre><i><b><a href=Serv_Inbox target=in2><img src=imsimages1/InSide.JPG border=0 width=108 height=36></a>");
		
		/*sos.println("<pre><i><b><a href=Serv_Inbox target=in2><img src=imsimages1/car.jpg border=0 width=108 height=36></a>");*/
		
		sos.println("<a href=Serv_Compose target=in2><img src=imsimages1/Comp.JPG border=0 width=108 height=36></a></b></i><font color=#FFFFFF>1</font><i><b>");
		sos.println("<a href=Serv_Option target=in2><img src=imsimages1/Options.jpg border=0 width=108 height=36 lowsrc=Images%20Raj/Options.jpg></a><font color=#FFFFFF>1</font>");
		sos.println("<a href=Serv_Address target=in2><img src=imsimages1/Contact.jpg border=0 width=108 height=36></a><font color=#FFFFFF>1</font>");
		sos.println("<a href=Serv_SentMessages target=in2><img src=imsimages1/Sent.jpg border=0 width=108 height=36></a><font color=#FFFFFF>1</font>");
		sos.println("<a href=Serv_Folder target=in2><img src=imsimages1/Folders.jpg border=0 width=108 height=36></a></b></i></pre>");
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
}
		



