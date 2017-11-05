import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class Serv_MoveFolder extends HttpServlet
{
Statement st,st1;
ResultSet rs,rs1;
Connection con;
ServletContext scon;
String uname,newfol,from,to,subj,data,date,mdate,mcc;
int id,status;

public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
{
try{
int store=0;
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
System.out.println("driver loaded");
con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
System.out.println("connection created");
st=con.createStatement();

//st1=con.createStatement();
scon=getServletContext();

//Serv_SignUp ss=(Serv_SignUp)scon.getServlet("Serv_SignUp");
   //Serv_SignUp ssu = new Serv_SignUp();
   //uname=ssu.name();
   HttpSession session = req.getSession(true);
   uname=session.getValue("name").toString();            
   //newfol=session.getValue("foldername").toString();

   String folderselect=req.getParameter("se");
   session.putValue("newfold",folderselect);

  ServletOutputStream sos= res.getOutputStream();
  String hidden=req.getParameter("hid");
  System.out.println("hidden: "+hidden);
  int cnt=Integer.parseInt(hidden);
  System.out.println("cnt: "+cnt);
//  int cnt=Integer.parseInt(req.getParameter("hid"));
  for (int i=1;i<=cnt;i++)
  {
     String chk=req.getParameter("b"+i);
	 System.out.println("chk: "+chk);
        if(chk!=null)
        {
            int mid=Integer.parseInt(chk);
			rs=st.executeQuery("select mailid,mailfrom,mailto,subject,mailcc,maildata,maildate,mailst from newcompose where mailid="+mid);

			while(rs.next())
			{
				
				id=rs.getInt(1);
				from=rs.getString(2);
				System.out.println("from: "+from);
				to=rs.getString(3);
				System.out.println("to :"+to);
				subj=rs.getString(4);
				mcc=rs.getString(5);
				data=rs.getString(6);
				mdate=rs.getString(7);
				status=rs.getInt(8);
				
			}
			int y=st.executeUpdate("insert into newfolder values("+mid+",'"+from+"','"+to+"','"+subj+"','"+mcc+"','"+data+"','"+folderselect+"','','"+mdate+"')");
			int z=st.executeUpdate("delete from newcompose where mailid="+mid);
			if(y > 0)
            {
                 sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue>");  
                 sos.println("<h2><i>Informations successfully saved in "+folderselect+"</i></h2><br><br>");
				 //sos.println("<a href=Serv_Inbox? mailid="+chk+" & folder="+folderselect+"><b>"+folderselect+"</b></a>");
				 //sos.println("</body></html>");
                 res.setHeader("Refresh","2;URL=Serv_Folder"); 
				// res.setHeader("Refresh","2;URL=Serv_Folder"); 
				 //res.setHeader("Refresh","2;URL=Serv_Folder1"); 
                  //res.sendRedirect("http://localhost:8080/servlet/Serv_Inbox");
             }
       }
   }
               
}
catch(Exception e){e.printStackTrace();}
}
}


