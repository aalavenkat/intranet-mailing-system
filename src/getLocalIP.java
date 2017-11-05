import java.net.*;
import java.io.*;
public class getLocalIP
{
   public String HostName()throws Exception
   {
      String name;
      InetAddress i=InetAddress.getLocalHost();
      name=i.getHostName();         
      return name;
   }      
}