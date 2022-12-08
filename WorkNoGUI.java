package lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class WorkNoGUI
{
	//initiates variables to use for time tracking 
	static long startTime = 0;
	static long endTime = 0;
	//array to add updates from threads before they complete 
	static List<Integer> updates = Collections.synchronizedList(new ArrayList<Integer>());


	
	private static class Worker implements Runnable
	{
		//initializing variables  
		private Semaphore s;
		private int userNum;
		private int divisionNum;
		private int threadCount;
		//method to add primes found by workers
		public List<Integer> getPrimes() 
		{
			return Collections.unmodifiableList(primes);
		}
		//list to add primes found by workers
		private List<Integer> primes = new ArrayList<>();
		//constructor for worker
		public Worker(Semaphore s, int userNum, int threadCount, int divisionNum)
		{
			this.s = s;
			this.userNum = userNum;
			this.divisionNum = divisionNum;
			this.threadCount = threadCount;
		}
		
		@Override
		public void run()
		{	
			//determining if the number is prime
			for(int x = 0; x <= userNum; x++)
				if(x % threadCount == divisionNum)
				{
					//calls method to check if number is prime 
					if(primeCheck(x))
					{
						//add to the arrays if the number is prime 
						primes.add(x);	
						updates.add(x);

					}
				}
			//when the work has finished, release the semaphore 
			s.release();
			
		}
		
	}

	//method to provide GUI with updated count of primes as the program runs 
	public static void updater() 
	{
		WorkGUI.progress.setText(updates.size() + " prime numbers found so far.");
		
	}
	
	//method to determine primes 
	static boolean primeCheck(int n)
	    {
			//less than 1 cannot be prime 
	       	if (n <= 1)
	            return false;
	       	//numbers greater than 2 can be prime, loop through and check them all 
	        for (int i = 2; i < n; i++)
	            if (n % i == 0)
	                return false;
	        return true;
	    }
   
	
	public static void control(int userNum, int threadCount) throws Exception 
	{
		//final list for all primes to be added to
		List<Integer> results = new ArrayList<>();
		//start time of calculation 
		long startTime = System.currentTimeMillis();
		//creating licenses
		Semaphore s = new Semaphore(threadCount);
		//creating list of workers
		List<Worker> workerList = new ArrayList<>();
		
		//distributes the licenses
		for(int x = 0; x < threadCount; x++)
		{
		
			s.acquire();
			//creates to worker 
			Worker w = new Worker(s, userNum, threadCount, x);
			workerList.add(w);
			//worker thread starts 
			new Thread(w).start();
			
			
		}
		
		//collect back licenses 
		for(int x = 0; x < threadCount; x++)
			s.acquire();
		//end time of calculation 
		long endTime = System.currentTimeMillis();
		//collect all primes from every worker and add to the results 			
		for(Worker w: workerList)
				results.addAll(w.getPrimes());
		//sort the results in numerical order 	
		Collections.sort(results);
		//if the cancel button on the GUI is not pressed, do the following 
		if(! WorkGUI.isCancel)	
		{
			for(Integer n: results)
			{
				//show every prime number found
				WorkGUI.output.append(n +"\n");
			}
			//report how many primes were found 
			WorkGUI.progress.setText(results.size() + " prime numbers found.");
			//report total time 
			WorkGUI.output.append("The thread(s) took " + (endTime - startTime)/1000f + " seconds.");
		}
		
		
	}
	
	//called on if the cancel button is pressed on the GUI
	public static void canceled()
	{
			
			WorkGUI.output.append("You hit cancel. Please click reset.");

	}
	
	//main method to run without the GUI 
	public static void main(String[] args) throws Exception
	{
		int threadCount = 4;
	
		int userNum= 200;		
		
		control(userNum,threadCount);
	}


	
}
