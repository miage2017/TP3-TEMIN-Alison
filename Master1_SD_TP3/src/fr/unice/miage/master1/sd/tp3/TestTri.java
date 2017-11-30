package fr.unice.miage.master1.sd.tp3;

import java.util.Random;

public class TestTri {
	private static final Random RAND = new Random(33);   // random number generator
	
	public static int[] createTab(int length) {
		int[] a = new int[length];
		for (int i = 0; i < a.length; i++) {
			a[i] = RAND.nextInt(10000);
		}
		return a;
	}
	public static void main(String[] args) throws Throwable {
		int mTaille = 1000000;   
		int maxTrial=5;
		long totalTime=0 ;
		int maxCPU=8;
		for(int cpuNum=1; cpuNum<=maxCPU;cpuNum*=2)
		{
		  totalTime=0;
		  for(int cTry=0; cTry< maxTrial; cTry++)
			{
			  int[] a = createTab(mTaille);
			  long begin = System.currentTimeMillis();
			  MultiThreadSort(a);
			  long end= System.currentTimeMillis();
			  totalTime+= end -begin ;
			}
	System.out.printf("%d cpus, %10d elements  =>  %6d ms \n", cpuNum, mTaille, totalTime/maxTrial);
		}
}
	private static void MultiThreadSort(int[] a) {
		// TODO Auto-generated method stub
		
	}
}
