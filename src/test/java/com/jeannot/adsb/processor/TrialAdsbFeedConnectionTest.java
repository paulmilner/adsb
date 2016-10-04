package com.jeannot.adsb.processor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

import org.junit.Ignore;
import org.junit.Test;

/**
 * This is some trying out of stuff related to getting the data feed from
 * http://www.adsbexchange.com/data
 * However, didn't get very far yet, even though that would be a nice example of reacting
 * to things happening in real time...
 * The "Querying Live Position Data" JSON API can give you a snapshot of what planes are in the air
 * (within a given distance of a lat/long etc.)
 * See AdsbJsonApiTest.java for initial tryout...
 *
 */
@Ignore //Don't run these tests every build, they're just for trying things out... 
public class TrialAdsbFeedConnectionTest {
	

	/**
	 * Try to connect and read from a local socket.
	 * Probably my work laptop is all locked down and I won't be able to make it work though...
	 * 
	 * @throws Exception
	 */
	@Test
	public void read_something_from_a_local_tcp_socket_just_to_get_an_idea() throws Exception {
		
		//Create and start local server
		LocalTcpServer.start();
		
		//Connect to local server
		Socket socket = new Socket("localhost", LocalTcpServer.SERVER_PORT);		
		DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//Write something
		outToServer.write("FIRST".getBytes());
		
		//Read it, client-side
		String data = inFromServer.readLine();
		System.out.println("From " + socket.getInetAddress().getHostAddress() 
				+ ":" + socket.getPort() + data);
		
		//Write something else
		outToServer.write("SECOND".getBytes());

		//Read it, client-side
		data = inFromServer.readLine();
		System.out.println("From " + socket.getInetAddress().getHostAddress() 
				+ ":" + socket.getPort() + data);
		
		//Write STOP command
		outToServer.write("STOP".getBytes());

		//Read it, client-side
		data = inFromServer.readLine();
		System.out.println("From " + socket.getInetAddress().getHostAddress() 
				+ ":" + socket.getPort() + data);
		
		//Close everything and shutdown
		outToServer.close();
		inFromServer.close();
		socket.close();
	}

	/**
	 * Try to connect and read from socket. NB won't work behind a proxy!
	 * Using info from: http://www.adsbexchange.com/data
	 * 
	 * @throws Exception
	 */
	@Test
	public void read_something_from_adsb_feed_tcp_socket() throws Exception {
		String data;
		Socket socket = new Socket("pub-vrs.adsbexchange.com", 32010); // port for 10 sec updates
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		data = inFromServer.readLine();
		System.out.println("From " + socket.getInetAddress().getHostAddress() 
				+ ":" + socket.getPort() + data);
		inFromServer.close();
		socket.close();
	}

	/**
	 * Try to connect and read from socket, via a proxy! (If that's possible)
	 * Still not sure it'll work though because of vagaries of proxies...
	 * http://docs.oracle.com/javase/6/docs/technotes/guides/net/proxies.html
	 * Using info from: http://www.adsbexchange.com/data
	 * 
	 * @throws Exception
	 */
	@Test
	public void read_something_from_adsb_feed_tcp_socket_with_proxy() throws Exception {
		String data;
		
		InetAddress proxyHost = InetAddress.getByName("proxy.logica.com");
		int proxyPort = 80;
		SocketAddress proxyAddr = new InetSocketAddress(proxyHost, proxyPort);
		Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddr);
		Socket socket = new Socket(proxy);
		InetAddress serverAddress = InetAddress.getByName("pub-vrs.adsbexchange.com");
		int port = 32010;
		socket.connect(new InetSocketAddress(serverAddress, port));
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		data = inFromServer.readLine();
		System.out.println("From " + socket.getInetAddress().getHostAddress() 
				+ ":" + socket.getPort() + data);
		inFromServer.close();
		socket.close();
	}

}