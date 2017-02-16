import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Seversock {
	
	public static void main(String []args) throws IOException{

		ServerSocket servSock = new ServerSocket(2222);
		String userDetails = null;
		Socket server = servSock.accept();
		InputStreamReader isRead = new InputStreamReader(server.getInputStream());
		BufferedReader bRead = new BufferedReader(isRead);
		userDetails = bRead.readLine();
		System.out.println(userDetails);
	}
}

