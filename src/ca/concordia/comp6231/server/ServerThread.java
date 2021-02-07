package ca.concordia.comp6231.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerThread implements Runnable {

	private Socket client;

	private ArrayList<Socket> totalClients;

	private String message;

	private InputStream is;

	public ServerThread(Socket p_client, ArrayList<Socket> p_totalClients) {

		client = p_client;

		totalClients = p_totalClients;

	}

	@Override
	public void run() {

		try {

			is = client.getInputStream();

			Scanner scan = new Scanner(is);

			while (!client.isClosed()) {

				checkMessage(scan, client);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void checkMessage(Scanner scan, Socket client) {

		if (scan.hasNextLine()) {

			message = scan.nextLine();

			System.out.println("User " + client.getPort() + ":" + message);

			sendMessagesToClients();

		}

	}

	public void sendMessagesToClients() {

		PrintWriter pw;

		// Send data to the clients

		for (Socket indClient : totalClients) {

			try {
				pw = new PrintWriter(indClient.getOutputStream());

				if (pw != null) {

					System.out.println("Sending data to " + indClient.getPort() + message);

					pw.write(message + "\r\n");

					pw.flush();

				}

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

}
