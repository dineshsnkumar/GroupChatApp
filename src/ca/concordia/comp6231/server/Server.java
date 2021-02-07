package ca.concordia.comp6231.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Server Class
 * 
 * @author dinesh
 * @version 1.0.0
 *
 */

public class Server {

	ArrayList<Socket> clients;

	public ArrayList<Socket> getClients() {

		return clients;
	}

	public static void main(String[] args) {

		Server serverObj = new Server();

		serverObj.startServer();

	}

	public void startServer() {

		try {
			ServerSocket server = new ServerSocket(9999);

			System.out.println("Server started at port 9999");

			clients = new ArrayList<Socket>();

			while (true) {

				Socket client = server.accept();

				System.out.println("New connection from " + client.getRemoteSocketAddress());

				clients.add(client);

				ServerThread serverThreadObj = new ServerThread( client, clients);

				Thread thread = new Thread(serverThreadObj);

				thread.start();

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
