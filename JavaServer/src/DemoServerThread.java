import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class DemoServerThread extends Thread{
	Socket t;
	public DemoServerThread(Socket s){
		t=s;
	}
	public void run(){
		System.out.println("inside run");
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(t.getInputStream()));
			System.out.println(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}
}
