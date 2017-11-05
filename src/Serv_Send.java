import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
//import org.apache.commons.fileupload.*;

public class Serv_Send extends HttpServlet
{
Statement stmt=null,st1=null,st2=null,st3=null;;
Connection con=null;
ResultSet rs=null,rs1=null;
int i;
ServletContext scon=null;
ServletOutputStream sos=null;
boolean bool;
String mfrom=null;
boolean flag=true;
String str1=null,mdate="";


public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{

try{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	System.out.println("Driver Loaded");
	con=DriverManager.getConnection("jdbc:odbc:dsn1","scott","tiger");
	System.out.println("Connection created");
	stmt=con.createStatement();
	scon=getServletContext();

	/*DiskFileUpload upload = new DiskFileUpload();
	File tempdir = new File("f:\\uploaded");

	upload.setSizeMax(-1L);
	upload.setRepositoryPath(tempdir.getCanonicalPath());
		
	String basename = null;
	String war = null;
	FileItem warUpload = null;
			
	List items = upload.parseRequest(req);
    Iterator iter = items.iterator();
    do
    {
      if(!iter.hasNext())
		  break;
			FileItem item = (FileItem)iter.next();
            if(!item.isFormField())
              if(item.getFieldName().equals("abc") && warUpload == null)
			  {
                 warUpload = item;
				//System.out.println("warupload: "+warUpload);
			   }
               else
                item.delete();
     } while(true);
	 war = warUpload.getName();
	 System.out.println("war :"+war);
			

/*			if(war.lastIndexOf('\\') >= 0)
				war = war.substring(war.lastIndexOf('\\') + 1);
			if(war.lastIndexOf('/') >= 0)
				war = war.substring(war.lastIndexOf('/') + 1);

			
			if(war.indexOf('\\') >= 0)
				war = war.substring(war.indexOf('\\') + 1);
				System.out.println("2nd war :"+war);
			if(war.indexOf('/') >= 0)
				war = war.substring(war.indexOf('/') + 1);
				System.out.println("3rd war :"+war);*/

			//System.out.println(war);
			/*String war1="";

			if(war.lastIndexOf('\\') >= 0)
				war1 = war.substring(0,war.lastIndexOf('\\') + 1);
				System.out.println("war1 :"+war1);
			//System.out.println(war1);
			if(war.lastIndexOf('/') >= 0)
				war1 = war.substring(0,war.lastIndexOf('/') + 1);
				System.out.println("2nd war1 :"+war1);
			//System.out.println(war1);
			
			File appBaseDir = new File("");
			//System.out.println("appBaseDir: "+appBaseDir);
			File file1 = new File(war1);
			//File file1 = new File(appBaseDir,war1);
			System.out.println("file1: "+file1);
			file1.mkdirs();
			appBaseDir.mkdirs();
			File file = new File(war);
			//warUpload.write(file);
			//File file = new File(appBaseDir,war);
			System.out.println("file: "+file);
			/*String newfile=file.toString();
			if(newfile.lastIndexOf('\\') > 0)
			{
				String newfile1=newfile.substring(newfile.lastIndexOf('\\')+1);
				System.out.println("newfile1: "+newfile1);
			}

			File newfile2=new File("newfile1");
			int len=(int)file.length();	
			System.out.println("len: "+len);
			FileReader fr=new FileReader(file);

			ps.setBinaryStream( ,fis,(int)file.length());

			System.out.println("filereader: "+fr);
			char arr[]=new char[len];
			fr.read(arr,0,len-1);
			String s=new String(arr);
			System.out.println("s: "+s);*/



    HttpSession session = req.getSession(true);
    mfrom=session.getValue("name").toString();            

//}
//catch(Exception e)
//{
//    mfrom ="vinodkumar";
//}

ServletOutputStream sos=res.getOutputStream();
//st=con.createStatement();
/*while(flag)
{
	rs=st.executeQuery("Select max(mailid) from newcompose");
	/*rs=st.executeQuery("Select mailid from newcompose");
	rs.next();
	if(rs==null)
	{
	i=1;
	}
	else
	{
	i=rs.getInt(1);
	i++;
	}
	rs.close();
	*/
	String mto=req.getParameter("to");
	String msub=req.getParameter("subject");
	String mcc=req.getParameter("cc");
	String mbcc=req.getParameter("bcc");
	String mexc=req.getParameter("s1");
	String mdata=req.getParameter("maildata");
	java.util.StringTokenizer st=new java.util.StringTokenizer(mto,",");
	mdate=String.valueOf(new java.util.Date());


	while(st.hasMoreTokens())
	{
		str1=st.nextToken();

		st1=con.createStatement();
//	rs1=st1.executeQuery("select * from signupdetails where uname='"+mto+"'");
		rs1=st1.executeQuery("select * from signupdetails where uname='"+str1+"'");
		if(rs1.next())
		{
		bool=true;
		}
		else
		{
		bool=false;
		sos.println("<html><head><script>{alert('Invalid Mail-to address - He is an unregistered user');window.history.go(-1);}</script></head></html>");
//sos.println("window.document.form4.to.focus();window.document.form4.to.select();}</script></head></html>");
		}
		rs1.close();
		st1.close();
		if(bool==true)
		{
//String date=String.valueOf(new java.util.Date());
//System.out.println(date);
			if(flag)
			{
				rs=stmt.executeQuery("Select max(mailid) from newcompose");
	/*rs=st.executeQuery("Select mailid from newcompose");*/
				rs.next();
				if(rs==null)
				{
					i=1;
				}
				else
				{
					i=rs.getInt(1);
					i++;
				}
				rs.close();

						
			//}
				st2=con.createStatement();
				System.out.println("St2 successful");
//
				int x=st2.executeUpdate("insert into newcompose values("+i+",'"+mfrom+"','"+str1+"','"+msub+"','"+mcc+"','"+mbcc+"','','"+mdata+"','inbox','"+mdate+"','')");
//int x=st2.executeUpdate("Insert into newcompose values("+i+",'"+mfrom+"','"+mto+"','"+msub+"','"+mcc+"','"+mbcc+"','"+mexc+"','"+mdata+"','inbox',sysdate,"+1+")")
				if(x>0)
//	sos.println("Message has been stored<br>");
//sos.println("<html><body bgcolor=abbccd><font color=blue><h3><i>One row inserted </i></h3></font></body></html>");

//st3=con.createStatement();
//int y=st3.executeUpdate("Insert into newfolder values('"+mfrom+"','inbox',"+1+")");
//if(y==1){
//sos.println("<html><body bgcolor=abbccd><font color=blue><h3><i>One row inserted into folderdir</i></h3></font></body></html>");
//}
//st3.close();
//	sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><font color=blue><h3><i>Message has been sent to "+mto+" </i></h3></font>");
				sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><font color=blue><h3><i>Message has been sent to "+str1+" </i></h3></font>");
				st2.close();
			}
		}
	}
				str1="";
				i++;
				st3=con.createStatement();
				System.out.println("St3 successful");
				int y=st3.executeUpdate("insert into newcompose values("+i+",'"+mfrom+"','"+mcc+"','"+msub+"','','"+mbcc+"','','"+mdata+"','inbox','"+mdate+"','')");
				if(y>0)
				sos.println("<html><body bgcolor=white background=indtextb.jpg text=blue><font color=blue><h3><i>Message has been sent to "+mcc+" </i></h3></font>");
				st3.close();
				sos.println("<form action=Serv_NewAddress><center>");
				sos.println("<h3><a href=Serv_Compose>Compose</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=Serv_Inbox>Goto Inbox</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=Serv_NewAddress>Add Address</a>");
				sos.println("</form></body></html>");
				

//res.setHeader("Refresh","2;URL=http://localhost:8080/description.html");

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
}

