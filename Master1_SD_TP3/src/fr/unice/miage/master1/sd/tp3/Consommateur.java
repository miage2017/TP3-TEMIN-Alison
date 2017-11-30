package fr.unice.miage.master1.sd.tp3;

import java.util.ArrayList;
import java.util.List;

public class Consommateur implements Runnable {
	private final List<String> nosJobs;
	private final int maxJobs = 4;
	private final String iD;

	public Consommateur(List<String> nosJobs, String iD) {
		this.nosJobs = nosJobs;
		this.iD = iD;
}
	private void cons(int i) throws InterruptedException{
	      synchronized (nosJobs)
	      {
	         while (nosJobs.size() == 0)
	         {
	            System.out.format("File vide, %s  is waiting\n",  iD);
	            nosJobs.wait();
	            System.out.format("%s is awake now \n",  iD);

	         }
	         String jobDone = nosJobs.remove(0);
	         System.out.format("%s prend le Job %s\n" ,iD, jobDone  );
	         System.out.print("File :");
	         for (String it: nosJobs)
	        	 System.out.format(" %s ",it);
	         System.out.println("");
		 nosJobs.notify();
	      }
	}
	public static void main(String[] args) {
	    List<String> theJobs = new ArrayList<String>(); 
	    Thread theTask ;
	    for (int i=0; i<5; i++){
		Consommateur consume = new Consommateur(theJobs, String.format("C_%d",i));
		theTask=new Thread(consume); 
		theTask.start();
		Producteur produce = new Producteur(theJobs, String.format("P_%d",i));
		theTask =new Thread(produce); 
		theTask.start();		
	    }
}
	@Override
	public void run() {
		int i = 0;
	      while (true) {
	         try
		     {  
		    i++;
	            cons(i);
	            Thread.sleep((long)  (2000* Math.random()));

	         } 
	         catch (InterruptedException ex)
	         {
	            ex.printStackTrace();
	         }
	      }		
	}
}
