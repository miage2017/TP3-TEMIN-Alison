package fr.unice.miage.master1.sd.tp3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurChat {
	int port;
	ServerSocket serveur = new ServerSocket(port);
	ArrayList<Thread> list = new ArrayList<Thread>();
	ArrayList<Socket> mesSocket = new ArrayList<Socket>();
	public ServeurChat() throws IOException, InterruptedException {
		int port = 4200;
		ServerSocket serveur = new ServerSocket(port);
		ArrayList<Thread> list = new ArrayList<Thread>();
		ArrayList<Socket> mesSocket = new ArrayList<Socket>();
		System.out.println("serveur ok");
		while (true) {
			Socket courante = serveur.accept();
			System.out.println("nouveau client connecté sur le port " + courante.getPort());
			Thread t = new Thread(new ServiceClientChat(courante, courante.getPort(), this));
			list.add(t);
			// System.out.println(list);
			t.start();
			mesSocket.add(courante);
		}
	}
	
	public ArrayList<Socket> getSocket() {
		return mesSocket;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		ServeurChat repart = new ServeurChat();
	}
}
