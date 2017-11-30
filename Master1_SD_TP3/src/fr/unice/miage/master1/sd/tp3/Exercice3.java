package fr.unice.miage.master1.sd.tp3;

public class Exercice3 implements Runnable{
	 private volatile boolean done = false;
	 public void seterminer() {done = true;}
	 int i;
	 
	 

	@Override
	public void run() {
		while (!done)
	      {
	    	 System.out.println(i++);
	    	 }
	     }
	
	public static void main(String[] args) throws InterruptedException{
		 Exercice3 container = new Exercice3();
		  Thread myThread = new Thread(container);
		  myThread.start();
		  Thread.sleep(10000);
		  container.seterminer();
	 }
}
