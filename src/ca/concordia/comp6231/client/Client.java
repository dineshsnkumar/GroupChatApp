package ca.concordia.comp6231.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		InetAddress ia;

		Scanner scan;

		try {

			ia = InetAddress.getByName("localhost");

			try {
				Socket client = new Socket(ia, 9999);

				scan = new Scanner(System.in);

				ClientThread clientObj = new ClientThread(client);

				Thread threadObj = new Thread(clientObj);

				threadObj.start();

				System.out.println("Enter Username: ");

				String username = scan.nextLine();

				if (!username.isEmpty()) {

					clientObj.setUsername(username);

				} else {

					clientObj.setUsername("annoymous");

				}

				// Read data from the console

				System.out.println("If you see a cursor it means you can type something");

				while (threadObj.isAlive()) {

					if (scan.hasNextLine()) {

						clientObj.setData(scan.nextLine());

					}

				}

			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

	}

}
