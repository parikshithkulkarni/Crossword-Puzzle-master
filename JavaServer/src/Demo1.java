import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Demo1 {

	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		Socket s=new Socket("localhost", 9080);
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintStream ps=new PrintStream(s.getOutputStream());
		Scanner scan=new Scanner(System.in);
		ps.println(scan.nextLine());
		//Scanner scan=new Scanner(System.in);
		ps.println(scan.nextLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		//System.out.println((String[])new ObjectInputStream(s.getInputStream()).readObject());
		System.out.println(br.readLine());
		ps.println(scan.nextLine());
		System.out.println(br.readLine());

	}

}
