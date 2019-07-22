/*
	This program is to provide remote playability to the game Connect Four.  It will be built and tested based on the
	iterative, small steps of the agile process.  The Server class listens for a client request on port 5050.  
	Connection automatically granted and the client terminates the connection with a keyphrase: "endgame".
*/
package SockPkg;
import java.net.*;
import java.io.*;

public class Server{

	// initialize
	private DataInputStream in = null;
	private ServerSocket servSock = null;
	private Socket sock = null;

	public Server(int port){

		// start listen-wait mode automatically
		try{
			servSock = new ServerSocket(port);
			System.out.println("\nServer online.  Waiting for client...");

			// once client requests cnx, accept
			sock = servSock.accept();
			System.out.println("Client connection accepted.");

			// initiate stream to receive client input
			// maybe input will simply forward request packets as instances of a Request class
			// for now simply a reflection until command "endgame" received
			in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));

			// process data client sent
			String line = "";
			while (!line.equals("endgame")){
				line = in.readUTF();
				System.out.println(line);
			}

			// close connection after client requests termination
			System.out.println("Game ended.  Closing connection.");
			sock.close();
			in.close();
		}
		catch(IOException i){
			System.out.println(i);
		}
	}

	public static void main(String args[]){
		Server server = new Server(5050);
	}
}