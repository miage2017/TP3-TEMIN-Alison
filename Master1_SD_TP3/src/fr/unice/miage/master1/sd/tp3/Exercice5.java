package fr.unice.miage.master1.sd.tp3;

import java.util.Arrays;

public class Exercice5 {
	public static void tri(int[] a, int AvailCPU, String branch) throws InterruptedException  {
	    if (a.length >= 2) {
		
		int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
		int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
		
		if (AvailCPU <= 1)
		    {
			tri(left, AvailCPU, branch+"0");
			tri(right, AvailCPU, branch+"1");
		    }
		else
		    {
			Thread lThread = new Thread(new Trier(left,  AvailCPU / 2, branch+"0" ));
			Thread rThread = new Thread(new Trier(right, AvailCPU / 2, branch+"1" ));
			
			lThread.start();
			rThread.start();
			
			lThread.join();
			rThread.join();
		    } 
		// fusionne les tableaux left et right
		// La fusion est sequentielle 
		Trier.fusion(left, right, a);
	    }
	}
}
