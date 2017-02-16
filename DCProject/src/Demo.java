import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Demo {
	static int s=0;
	static Timer timer = new Timer();
	static TimerTask tasknew = new TimerTask(){
		public void run(){
			s++;
			System.out.println(s);
		}
	};
	
	public static void main(String[] args) throws Exception, IOException {
//		Random r = new Random();
//		int Low = 10;
//		int High = 100;
//		int Result = r.nextInt(High-Low) + Low;
//		long start=System.currentTimeMillis();
//		System.out.println(Result);
//		
//		   
//		      
//		   // scheduling the task at interval
//		   timer.schedule(tasknew, 10000);
		Socket s=new Socket("localhost", 9090);
		PrintStream ps=new PrintStream(s.getOutputStream());
		Scanner scan=new Scanner(System.in);
		ps.println(scan.nextLine());

	}
	public void a(){
		Math.abs(10);
	}

}
