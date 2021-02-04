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

		try {

			ia = InetAddress.getByName("localhost");

			try {
				Socket client = new Socket(ia, 9999);

				ClientThread clientObj = new ClientThread(client);

				Thread threadObj = new Thread(clientObj);

				threadObj.start();

			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

	}

}
