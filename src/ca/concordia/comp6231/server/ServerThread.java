package ca.concordia.comp6231.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerThread implements Runnable {

	private ServerSocket server;

	private Socket client;

	private ArrayList<Socket> totalClients;

	private String message;

	public ServerThread(ServerSocket p_server, Socket p_client, ArrayList<Socket> p_totalClients) {

		server = p_server;

		client = p_client;

		totalClients = p_totalClients;

	}

	@Override
	public void run() {

		try {

			InputStream is = client.getInputStream();

			Scanner scan = new Scanner(is);

			PrintWriter pw;

			while (!client.isClosed()) {
				
				if (scan.hasNextLine()) {

					message = scan.nextLine();
				}

				System.out.println("User " + client.getPort() + ":" + message);

				// Send data to the clients

				for (Socket indClient : totalClients) {

					pw = new PrintWriter(indClient.getOutputStream());

					if (pw != null) {

						System.out.println("Sending data to " + indClient.getPort() + message);

						pw.write(message + "\r\n");

						pw.flush();

					}

				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
