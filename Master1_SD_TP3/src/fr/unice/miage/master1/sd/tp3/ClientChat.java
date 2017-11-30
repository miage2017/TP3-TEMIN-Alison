package fr.unice.miage.master1.sd.tp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientChat implements Runnable {
	private final String hote;
	private final int port;
	private final String id;
	private Scanner console_input;
	final String Finish = "" + (char) 4; // Signal de fin de connection aussi nommé EOT ctrl-d
	byte[] datas = null;
	private String fileName;
	private Socket laConnection;

	public ClientChat(String hote, int port, String mon_id) throws UnknownHostException, IOException {
		this.hote = hote;
		this.port = port;
		this.id = mon_id;
		this.laConnection = new Socket(this.hote, this.port);
	}

	@Override
	public void run() {
		Thread t1 = new Thread(new Thread2(this.laConnection));
		t1.start();
		Thread t2 = new Thread(new Ecouter(this.laConnection));
		t2.start();
	}

	public class Thread2 implements Runnable {

		private Socket la_connection;

		public Thread2(Socket la_connection) {
			this.la_connection = la_connection;
		}

		@Override
		public void run() {
			OutputStream os = null;
			try {
				os = la_connection.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter ma_sortie;
			while (true) {
				Scanner scan = new Scanner(System.in);
				String str = scan.nextLine();
				ma_sortie = new PrintWriter(os, true);
				ma_sortie.println(str);
			}

		}
	}

	public class Ecouter implements Runnable {
		private Socket la_connection;

		public Ecouter(Socket la_connection) {
			this.la_connection = la_connection;
		}

		@Override
		public void run() {
			while (true) {
				try {
					InputStreamReader isr = new InputStreamReader(la_connection.getInputStream());
					BufferedReader flux_entrant = new BufferedReader(isr);
					String message_lu = flux_entrant.readLine();
					System.out.println(message_lu);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		ClientChat container = new ClientChat("127.0.0.1", 4200, "id1");
		Thread receveur1 = new Thread(container);
		receveur1.start();
	}

}
