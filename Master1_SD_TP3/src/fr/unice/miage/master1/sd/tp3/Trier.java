package fr.unice.miage.master1.sd.tp3;

public class Trier implements Runnable{
	
	    private int[] a;
	    private int AvailCPU;
	    private String branch;
	    public Trier(int[] a, int AvailCPU, String branch) {
			this.a = a;
			this.AvailCPU = AvailCPU;
			this.branch=branch;
	    }

	    public void run()  {
		try{
		    System.out.format("Thread %s sorting an array with size %d\n", branch , a.length);
		    Exercice5.tri(a, AvailCPU, branch);
		    System.out.format("Thread %s done\n", branch);

		}
		catch(Exception e) {;}
	    }
	    
	    public static void fusion(int[] left, int[] right, int[] a) {
	        int leftI = 0;
	        int rightI = 0;
	        for (int i = 0; i < a.length; i++) {
	  	 if (rightI >= right.length || (leftI < left.length && left[leftI] < right[rightI])) {
	  	     a[i] = left[leftI];
	  	     leftI++;			}
	  	else {
	  	    a[i] = right[rightI];
	  	    rightI++;
	  	}
	        }
	  }
	}

