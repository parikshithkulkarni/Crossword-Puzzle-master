import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;


public class DemoServer {
	static ArrayList<Socket> users=new ArrayList<Socket>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss=new ServerSocket(9090);
		for(int i=0;i<1000;i++)
		{
			long start=System.currentTimeMillis();
			ss.setSoTimeout(10000);
			int flag=0;
			while(true)
			{	
				try
			{	
				
				if(flag==1){
					break;
				}
				if(System.currentTimeMillis()-start<10000)
				{
					Socket s=ss.accept();
					users.add(s);
					System.out.println("added");
				}
				
				
				if(System.currentTimeMillis()-start>10000 && users.size()>1)
				{
					System.out.println("if");
					toRun();
					break;
				}
				}
			catch(SocketTimeoutException s)
			{
				System.out.println("exception");
				toRun();
				flag=1;
				break;
			}
			}
		}

	}
	public static void toRun(){
		DemoServerThread d;
		
		for(int i=0;i<users.size();i++){
			System.out.println("torun");
			d=new DemoServerThread(users.get(i));
			d.start();
		}
		users.clear();
		System.out.println(users.size());
	}
}
