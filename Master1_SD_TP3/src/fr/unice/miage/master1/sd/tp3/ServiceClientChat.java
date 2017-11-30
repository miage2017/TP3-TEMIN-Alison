package fr.unice.miage.master1.sd.tp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServiceClientChat implements Runnable {
	private Socket unClient;
	private int port;
	final String Finish = "" + (char) 4;
	private ServeurChat serveur;

	public ServiceClientChat(Socket unClient, int cport, ServeurChat serveur) throws IOException {
		this.unClient = unClient;
		this.serveur = serveur;
		port = cport;
		// this.mon_connecteur = new ServerSocket(port);
		System.out.format("Serveur Amélioré  lancé sur le  port %d\n", port);
	}

	private void terminer(Socket ma_connection) {
		if (ma_connection != null) {
			try {
				ma_connection.close();
				System.out.format("Socket fermee \n");
			} catch (IOException e) {
				System.out.println("weird, nawak .... \n ");
			}
		}
	}

	private Boolean Service_Client(Socket la_connection, ServeurChat serveur) throws IOException {

		InputStreamReader isr = new InputStreamReader(la_connection.getInputStream());
		BufferedReader flux_entrant = new BufferedReader(isr);
		System.out.println("Tampon entree attache ");
		PrintWriter ma_sortie = new PrintWriter(la_connection.getOutputStream(), true);
		System.out.println("Sortie attachée");
		System.out.println("Prêt à servir le Client : " + la_connection.getRemoteSocketAddress());

		String clientName = la_connection.getRemoteSocketAddress().toString();
		String message_lu = new String();
		int line_num = 0;

		ma_sortie.format("Bonjour %s j attends tes données  \n", clientName);
		while ((message_lu = flux_entrant.readLine()) != null && !Thread.currentThread().interrupted()) {
			System.out.format("%d: ->  [%s]\n", line_num, message_lu);
			for (Socket socket : serveur.getSocket()) {
				PrintWriter ma_sortieClient;
				ma_sortieClient = new PrintWriter(socket.getOutputStream(), true);
				ma_sortieClient.println(message_lu);
			}

			if (message_lu.contains(Finish)) {
				System.out.println("Reception de  " + Finish + " -> Transmission finie");

				System.out.format("Je clos la connection  %s :\n", clientName);
				terminer(la_connection);
				return (true);
			}
		}
		return false;
	}

	@Override
	public void run() {

		try {
			Service_Client(unClient, serveur);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		/* On crée puis lance le serveur */
		ServiceClient Mon_serveur = null;

		if (args.length != 1) {
			System.err.println("usage: java " + ServiceClient.class.getCanonicalName() + " serverPort");
			System.exit(-1);
		}
		try {
			Socket unClient = new Socket();
			int port = 4200;
			Mon_serveur = new ServiceClient(unClient, port);
		} catch (NumberFormatException e) {
			System.out.println("Format du port incorrect \n:  format exception for " + e.getMessage());
			System.exit(-1);
		}
		Mon_serveur.run();
	}
}
