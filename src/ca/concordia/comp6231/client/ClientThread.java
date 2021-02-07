package ca.concordia.comp6231.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable {

	private Socket client;

	private String data;

	private String username;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ClientThread(Socket p_socket) {
		client = p_socket;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void run() {

		PrintWriter pw;

		try {

			pw = new PrintWriter(client.getOutputStream());

			InputStream in = client.getInputStream();

			Scanner scan = new Scanner(in);

			data = "";

			while (!client.isClosed()) {
				
				if (in.available() > 0) {

					if (scan.hasNextLine()) {

						System.out.println(scan.nextLine());

					}

				}

				if (!data.isEmpty()) {

					String message = getUsername() + " says " + data;

					pw.write(message + "\r\n");

					pw.flush();

					data = "";

				}

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
