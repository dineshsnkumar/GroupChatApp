package ca.concordia.comp6231.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable {

	private Socket client;

	public ClientThread(Socket p_socket) {

		client = p_socket;

	}

	@Override
	public void run() {

		System.out.println("Creating a new user at port " + client.getLocalPort());

		PrintWriter pw;

		try {

			pw = new PrintWriter(client.getOutputStream());

			InputStream in = client.getInputStream();

			Scanner scan = new Scanner(in);

			while (!client.isClosed()) {

				String message = getMessage();

				pw.write(message + "\r\n");

				pw.flush();
				
				if (scan.hasNextLine()) {

					System.out.println(scan.nextLine());

				}

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public String getMessage() {

		String message = "";

		System.out.print("What do you want to say ?  ");

		Scanner scan = new Scanner(System.in);

		if (scan.hasNextLine()) {

			message = scan.nextLine();

		} else {

			message = "";
		}

		return message;
	}

}
