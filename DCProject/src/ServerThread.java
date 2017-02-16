import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread {
Socket ser;
BufferedReader br;
PrintStream ps;
int puzzleNo;


public int getPuzzleNo() {
	return puzzleNo;
}
public void setPuzzleNo(int puzzleNo) {
	this.puzzleNo = puzzleNo;
}
ArrayList<ServerThread>clientHandler=new ArrayList<>();
int[] score;
	public ArrayList<ServerThread> getClientHandler() {
	return clientHandler;
}
public void setClientHandler(ArrayList<ServerThread> clientHandler1) {
	this.clientHandler = clientHandler1;
	int size=clientHandler.size();
	score=new int[size];
	for(int i=0;i<clientHandler.size();i++){
		System.out.println(clientHandler.get(i).getName());
		score[i]=0;
	}
}
	public ServerThread(Socket s) throws IOException {
	ser=s;
	ps=new PrintStream(ser.getOutputStream());
	br=new BufferedReader(new InputStreamReader(ser.getInputStream()));
}
	public ServerThread(){
		
	}
	public synchronized boolean authenticate(String User,String Password) throws Exception{
		System.out.println(User);
		System.out.println(Password);
		File f= new File("Authenticate.txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		String l=br.readLine();
		String[] a;
		while(l!=null){
			
			a=l.split(",");
			if(a[0].equals(Password) && a[1].equals(User)){
				br.close();
				return true;
				
			}
			l=br.readLine();
		}
		System.out.println(l+" false");
		br.close();
		return false;
	}
	public synchronized void  broadcast(String s) throws IOException{
		System.out.println(s);
		for(int i=0;i<clientHandler.size();i++){
			PrintStream P=new PrintStream(clientHandler.get(i).ser.getOutputStream());
			P.println(s);
			if(clientHandler.get(i).getName().equalsIgnoreCase(this.getName())){
				score[i]++;
				ps.println(score[i]+"");
			}
		}
	}
	public synchronized String solution() throws Exception{
		int n=1;
		File f=new File("Solution.txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		String l=br.readLine();
		while(l!=null){
			if(n==puzzleNo){
				br.close();
				return l;
			}
			
			l=br.readLine();
			n++;
		}
		System.out.println(puzzleNo);
		System.out.println(l);
		br.close();
		return l;
	}
	public synchronized String puzzle() throws Exception{
		int n=1;
		File f=new File("Puzzle.txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		String l=br.readLine();
		while(l!=null){
			if(n==puzzleNo){
				br.close();
				return l;
			}
			
			l=br.readLine();
			n++;
		}
		System.out.println(puzzleNo);
		System.out.println(l);
		br.close();
		return l;
	}
	public void run(){
		try {
			
			ps.println("Matrix ");
			String a=puzzle();
			System.out.println(a);
			ps.println(a);
			a=solution();
			ps.println(a);
			System.out.println(a);	
			String line=br.readLine();
			//System.out.println(line);
			while(line!=null){
				// broadcast(this.getName()+" "+line);
				System.out.println(line);
				line=br.readLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
