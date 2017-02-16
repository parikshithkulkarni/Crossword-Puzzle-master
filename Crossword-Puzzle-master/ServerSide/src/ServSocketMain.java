import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ServSocketMain {
	
	public static void main(String []args) throws IOException{

		int clientNumber = 0;
		System.out.println("Enter the port number..");
		Scanner sc = new Scanner(System.in);
		ServerSocket servSock = new ServerSocket(sc.nextInt());
		System.out.println("Connecting..");
		try {
            while (true) {
                new ServerSocketThread(servSock.accept(), clientNumber++).start();
                System.out.println("Connected!");
                System.out.println("karan");
            }
        } finally {
        	servSock.close();
        	sc.close();
        }
	}
}

