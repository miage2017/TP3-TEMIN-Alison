package fr.unice.miage.master1.sd.tp3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Repartiteur {
	public Repartiteur() throws IOException, InterruptedException {
		int port = 4200;
		ServerSocket serveur = new ServerSocket(port);
		ArrayList<Thread> list = new ArrayList<Thread>();
		System.out.println("serveur ok");
		while (true) {
			Socket courante = serveur.accept();
			System.out.println("nouveau client connecté sur le port " + courante.getPort());
			Thread t = new Thread(new ServiceClient(courante, courante.getPort()));
			list.add(t);
			// System.out.println(list);
			t.start();
			Thread.currentThread().sleep(1000);
			for (Thread element : list) {
				element.interrupt();
				System.out.println("thread" + element + "interrompu");
			}
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Repartiteur repart = new Repartiteur();
	}
}
