

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
	static ArrayList<ServerThread>users=new ArrayList<ServerThread>();
	static ServerThread s1;
	static ServerThread s2;
	static ServerThread s3;
	Timer timer = new Timer();
	TimerTask tasknew = new TimerTask(){
		public void run(){
			
			System.out.println();
		}
	};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		try {
//			int count=0;
//			ServerSocket ss=new ServerSocket(5656);
//			s1=new ServerThread();
//			s2=new ServerThread();
//			s3=new ServerThread();
//			ArrayList<ServerThread>clientHandler=new ArrayList<>();
//			while(true){
//				Socket s=ss.accept();
//				count++;
//				if(count==1){
//					s1= new ServerThread(s);
//					PrintStream ps=new PrintStream(s.getOutputStream());
//					BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
//					String user=br.readLine();
//					String pass=br.readLine();
//					if(s1.authenticate(user, pass)){
//						s1.setName("p1");
//						ps.println("Waiting");
//						Random r = new Random();
//						int Low = 1;
//						int High = 2;
//						int Result = r.nextInt(High-Low) + Low;
//						s1.setPuzzleNo(1);
//						s1.start();
//						clientHandler.add(s1);
//					}
//					else{
//						ps.println("false");
//						count--;
//					}
//				}
//				if(count==2){
//					s2= new ServerThread(s);
//					PrintStream ps=new PrintStream(s.getOutputStream());
//					BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
//					String user=br.readLine();
//					String pass=br.readLine();
//					if(s2.authenticate(user, pass)){
//						s2.setName("p2");
//						ps.println("Waiting");
//						clientHandler.add(s2);
//					}
//					else{
//						ps.println("false");
//						count--;
//					}
//				}
//				if(count==3){
//					s3= new ServerThread(s);
//					PrintStream ps=new PrintStream(s.getOutputStream());
//					BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
//					String user=br.readLine();
//					String pass=br.readLine();
//					if(s3.authenticate(user, pass)){
//						s3.setName("p3");
//						ps.println("Waiting");
//						clientHandler.add(s3);
//						s1.setClientHandler(clientHandler);
//						s2.setClientHandler(clientHandler);
//						s3.setClientHandler(clientHandler);
//						Random r = new Random();
//						int Low = 1;
//						int High = 2;
//						int Result = r.nextInt(High-Low) + Low;
//						s1.setPuzzleNo(Result);
//						s2.setPuzzleNo(Result);
//						s3.setPuzzleNo(Result);
//						s1.start();
//						s2.start();
//						s3.start();
//					}
//					else{
//						ps.println("false");
//						count--;
//					}
//					
//					
//				}
//				
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ServerSocket ss=new ServerSocket(9080);
		ServerThread t;
		for(int i=0;i<1000;i++)
		{
		int flag=0;
		long start=System.currentTimeMillis();
		ss.setSoTimeout(5000);
		while(true){
			if(flag==1){
				break;
			}
			if(i==1000){
				i=0;
			}
			try{
				Socket s=ss.accept();
				t=new ServerThread(s);
				String pass=t.br.readLine();
				//t.setName(t.br.readLine());
				t.setName(t.br.readLine());
				if(t.authenticate(pass,t.getName())){
					t.ps.println("Waiting");
					users.add(t);
				}
				
				if(users.size()>0 && start-System.currentTimeMillis()>5000){
					toRun();
					break;
				}
			}
			catch(SocketTimeoutException s){
				if(users.size()>0)
				toRun();
				flag=1;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		}
	}
	public static void toRun(){
		ServerThread t;
		Random r = new Random();
		int Low = 1;
		int High = 2;
		int result = r.nextInt(High-Low) + Low;
		for(int i=0;i<users.size();i++){
			t=users.get(i);
			t.setClientHandler(users);
			t.setPuzzleNo(result);
			t.run();
		}
		users.clear();
	}
}

