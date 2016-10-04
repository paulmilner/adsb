package com.jeannot.adsb.processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import org.junit.Test;

public class TrialAdsbFeedConnection {

	/**
	 * Try to connect and read from socket. NB prob won't work behind a proxy!
	 * Using info from: http://www.adsbexchange.com/data
	 * 
	 * @throws Exception
	 */
	@Test
	public void read_something_from_tcp_socket() throws Exception {
		System.setProperty("java.net.useSystemProxies", "true");
		String data;
		Socket clientSocket = new Socket("pub-vrs.adsbexchange.com", 32010); // port for 10 sec updates
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		data = inFromServer.readLine();
		System.out.println("From " + clientSocket.getInetAddress().getHostAddress() 
				+ ":" + clientSocket.getPort() + data);
		clientSocket.close();
	}

}
