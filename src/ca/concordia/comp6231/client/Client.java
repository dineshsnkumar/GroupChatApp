package ca.concordia.comp6231.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		Client mainClientObj = new Client();

		mainClientObj.startClient();

	}

	public void startClient() {

		InetAddress ia;

		try {

			ia = InetAddress.getByName("localhost");

			try {
				Socket client = new Socket(ia, 9999);

				ClientThread clientObj = new ClientThread(client);

				Thread threadObj = new Thread(clientObj);

				threadObj.start();

				readDataFromClient(clientObj);

			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
	}

	/***
	 * Reading username from console if the user doesn't type the default is
	 * annoymous
	 * 
	 * @param clientObj
	 */

	public void readDataFromClient(ClientThread clientObj) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter Username: ");

		String username = scan.nextLine();

		if (!username.isEmpty()) {

			clientObj.setUsername(username);

		} else {

			clientObj.setUsername("annoymous");

		}

		System.out.println("If you see a cursor it means you can type something");

		while (true) {

			if (scan.hasNextLine()) {

				clientObj.setData(scan.nextLine());

			}

		}

	}

}
