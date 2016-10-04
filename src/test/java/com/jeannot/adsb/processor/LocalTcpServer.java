package com.jeannot.adsb.processor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class LocalTcpServer {

	public static final int SERVER_PORT = 80; // fairly random choice of port...

	public static void start() throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			System.out.println("Received: " + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
			
			if (clientSentence.equalsIgnoreCase("STOP")) {
				welcomeSocket.close();
				System.exit(0);
			}
		}
		
	}

}
