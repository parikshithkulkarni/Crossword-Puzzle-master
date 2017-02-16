import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ServerSocketThread extends Thread{
	
	private Socket socket;
    private int clientNumber;
	
	public ServerSocketThread(Socket socket, int clientNumber) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client# " + this.clientNumber + " at " + socket);
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
		try{
			String userDetails = null;
			while(socket.isConnected()){
			InputStreamReader isRead = new InputStreamReader(socket.getInputStream());
			BufferedReader bRead = new BufferedReader(isRead);
			userDetails = bRead.readLine();
			System.out.println(userDetails);
			String [] userCredential = null;
			userCredential = userDetails.split(" ");
			if(userDetails.startsWith("register")){
				File file = new File("userList.txt");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String str = br.readLine();
				boolean isDuplicate = false;
				while(str!=null){
					if(str.contains(userCredential[1])){
						isDuplicate = true;
						OutputStream out = socket.getOutputStream();
						PrintStream pnt = new PrintStream(out);
						pnt.println("username already exists!");
					}
					str = br.readLine();
				}
				if(!isDuplicate){
					FileWriter writer = new FileWriter(file,true);
				    BufferedWriter bufferedWriter = new BufferedWriter(writer);
				    bufferedWriter.write(" "+userCredential[1]+ " "+ userCredential[2]+" ");
				    bufferedWriter.close();
				    OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					pnt.println("Successfully registered");
				}
				br.close();
			}
			else if(userDetails.startsWith("login")){
				File file = new File("userList.txt");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String str = br.readLine();
				while(str!=null){
					if(str.contains(userCredential[1]) && str.contains(userCredential[2])){
						OutputStream out = socket.getOutputStream();
						PrintStream pnt = new PrintStream(out);
						pnt.println("welcome "+userCredential[1]);
					}
					else
					{
						OutputStream out = socket.getOutputStream();
						PrintStream pnt = new PrintStream(out);
						pnt.println("wrong username or password!");
					}
					str = br.readLine();
				}
				br.close();
			}
			else if(userDetails.startsWith("1")){
				String [] fileName = userDetails.split(" ");
				File file = new File(fileName[1]+".txt");
				if(file.exists()){
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					String str=br.readLine();
					StringBuffer sb = new StringBuffer("");
					while(str!=null){	
						pnt.println(ServerSocketThread.encode( str, 12 ));
						for (int i = 0; i < str.length(); i++) {
					     	sb.append(Integer.toString((str.indexOf(i) & 0xff) + 0x100, 16).substring(1));
					    }
						str=br.readLine();
					}
					pnt.println("checksum");
					pnt.println(sb);
					pnt.println("finished");
					br.close();
				}
				else{
					OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					pnt.println("file does not exist!");
					System.exit(1);
				}
			}
			else if(userDetails.startsWith("2")){
				String [] fileName = userDetails.split(" ");
				File file = new File(fileName[1]+".txt");
				if(file.exists()){
					int noOfLines = 0;
					int noOfWords = 0;
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					String str=br.readLine();
					String[] str2 = null;
					while(str!=null){	
						str2 = str.split(" ");
						noOfWords = noOfWords + str2.length;
						noOfLines++;
						str=br.readLine();
					}
					pnt.println("File name: "+fileName[1]+" ,Number of lines in file: "
							+noOfLines+" ,Number of words in file: "+noOfWords);
					br.close();
				}
				else{
					OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					pnt.println("file does not exist!");
					System.exit(1);
				}
				
			}
			else if(userDetails.startsWith("3")){
				String [] fileName = userDetails.split(" ");
				File file = new File(fileName[1]+".txt");
				if(file.exists()){
					int noOfLines = Integer.parseInt(fileName[2]);
					int count = 1;
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					String str=br.readLine();
					StringBuffer sb = new StringBuffer("");
					while(str!=null && count<=noOfLines){	
						count++;
						pnt.println(ServerSocketThread.encode( str, 12 ));
						for (int i = 0; i < str.length(); i++) {
					     	sb.append(Integer.toString((str.indexOf(i) & 0xff) + 0x100, 16).substring(1));
					    }
						str=br.readLine();
					}
					
					pnt.println("checksum");
					pnt.println(sb);
					str = "finished";
					pnt.println(str);
					if(noOfLines>count){
						pnt.println("You have asked more lines than available in the file");
					}
					br.close();
				}
				else{
					OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					pnt.println("file does not exist!");
					System.exit(1);
				}
			}
			else if(userDetails.startsWith("4")){
				String [] fileName = userDetails.split(" ");
				File file = new File(fileName[1]+".txt");
				if(file.exists()){
					file.setWritable(true);
					file.delete();
					OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					pnt.println("file deleted");
				}
				else{
					OutputStream out = socket.getOutputStream();
					PrintStream pnt = new PrintStream(out);
					pnt.println("file does not exist!");
					System.exit(1);
				}
			}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String encode(String enc, int offset) {
	    offset = offset % 26 + 26;
	    StringBuilder encoded = new StringBuilder();
	    for (char i : enc.toCharArray()) {
	        if (Character.isLetter(i)) {
	            if (Character.isUpperCase(i)) {
	                encoded.append((char) ('A' + (i - 'A' + offset) % 26 ));
	            } else {
	                encoded.append((char) ('a' + (i - 'a' + offset) % 26 ));
	            }
	        } else {
	            encoded.append(i);
	        }
	    }
	    return encoded.toString();
	}
}
