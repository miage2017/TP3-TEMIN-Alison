package fr.unice.miage.master1.sd.tp3;

public class GenClients {
	public GenClients() {
		String hote = "127.0.0.1";
		int port = 4200;
		String mon_id = "id";
		for (int i = 0; i < 5; i++) {
			System.out.println("création du client");
			mon_id = mon_id + i;
			Thread t = new Thread(new Client(hote, port, mon_id));
			t.start();
			System.out.println("nouveau client " + i);
		}
	}

	public static void main(String[] args) {
GenClients genclient = new GenClients();
	}


}
