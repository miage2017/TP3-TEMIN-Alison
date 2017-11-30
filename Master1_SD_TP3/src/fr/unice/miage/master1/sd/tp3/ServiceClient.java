package fr.unice.miage.master1.sd.tp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServiceClient implements Runnable {
	
	private Socket unClient;
	private int port;
	final String Finish = "" + (char) 4;

	public ServiceClient(Socket unClient,int cport) throws IOException {
		this.unClient = unClient;
		port = cport;
		//this.mon_connecteur = new ServerSocket(port); 		
		System.out.format("Serveur Amélioré  lancé sur le  port %d\n", port);
	}
	private  void terminer(Socket ma_connection){
	    if (ma_connection != null)       	
	    {
		    try 	{
			ma_connection.close(); 
			System.out.format("Socket fermee \n"); 
		    }
		    catch ( IOException e )   { System.out.println("weird, nawak .... \n ");}      
		}}
	
        private Boolean Service_Client(Socket la_connection) throws IOException {
            
            InputStreamReader isr = new InputStreamReader(la_connection.getInputStream());
            BufferedReader flux_entrant = new BufferedReader(isr);
            System.out.println("Tampon entree attache ");
            PrintWriter ma_sortie = new PrintWriter(la_connection.getOutputStream(), true);
            System.out.println("Sortie attachée");
            System.out.println("Prêt à servir le Client : "+ la_connection.getRemoteSocketAddress());

            String clientName = la_connection.getRemoteSocketAddress().toString();
            String message_lu = new String();
            int line_num = 0;

            
            ma_sortie.format("Bonjour %s j attends tes données  \n",clientName);
            while  ((message_lu = flux_entrant.readLine()) != null && !Thread.currentThread().interrupted())  {
                    System.out.format("%d: ->  [%s]\n", line_num, message_lu);
                    line_num++;
                    
                    if (message_lu.contains(Finish)) {
                            System.out.println("Reception de  " + Finish
                                            + " -> Transmission finie");
                            
                            System.out.format("Je clos la connection  %s :\n",clientName);
                            terminer(la_connection);
                            return (true);
                    }
            }
            return false;
}
		@Override
		public void run() {
			
	                        try {
								Service_Client(unClient);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			
		}
		
		public static void main(String[] args) throws IOException {
	        /* On crée puis lance le serveur */
	        ServiceClient Mon_serveur = null;
	        
	        if (args.length != 1) {
	                System.err.println("usage: java "+ ServiceClient.class.getCanonicalName()
	                                                + " serverPort");
	                System.exit(-1);
	        }
	        try {
	        	Socket unClient = new Socket();
	        	int port = 4200;
	                Mon_serveur = new ServiceClient(unClient, port);
	        } catch (NumberFormatException e) {
	                System.out.println("Format du port incorrect \n:  format exception for "
	                                                + e.getMessage());
	                System.exit(-1);
	        }
	        Mon_serveur.run();
	}
		
}
