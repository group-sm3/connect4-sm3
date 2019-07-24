/*
	This program is designed to provide remote playability to the game Connect Four.  The Client 
	class reaches out to a server via an IP and port number.  Connection granted automatically,
	excluding any errors.  The client will send requests to the server, who then forwards them
	to the opposing player on a remote system.  Thus the game will be synchronized between two
	remote players.
*/

package SockPkg;
import java.net.*;
import java.io.*;

public class Client{

	// initialize data stream and socket
	// depped:  private DataInputStream input = null; 
	private Socket sock = null;
	private DataOutputStream out = null;
	private BufferedReader buffRead = null;

	public Client(String addr, int port){

		try{
			// auto-initiate connection request to server
			sock = new Socket(addr, port);
			System.out.println("\nConnected to IP " + addr + " at port no " + port + ".");

			// prep streams to send data to server
			buffRead = new BufferedReader(new InputStreamReader(System.in));
			out = new DataOutputStream(sock.getOutputStream());

			// send data to server
			String line = "";
			while (!line.equals("endgame")){
				line = buffRead.readLine();
				out.writeUTF(line);
			}

			// close connection
			System.out.println("\nClosing connection.\n");
			buffRead.close();
			out.close();
			sock.close();
		}
		catch(IOException i){
			System.out.println(i);
		}
	}

	public static void main(String args[]){
		Client c = new Client("127.0.0.1", 5050);
	}
}
