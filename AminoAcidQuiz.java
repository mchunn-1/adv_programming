package lab2quiz;

import java.util.Random; 

public class AminoAcidQuiz {

	public static void main(String[] args) {
		
		//sets up array of amino acid 1 letter code 
		String[] SHORT_NAMES = 
			{ "A","R", "N", "D", "C", "Q", "E", 
			"G",  "H", "I", "L", "K", "M", "F", 
			"P", "S", "T", "W", "Y", "V" };
		
		//sets up array of amino acid full name  
		String[] FULL_NAMES = 
			{
			"alanine","arginine", "asparagine", 
			"aspartic acid", "cysteine",
			"glutamine",  "glutamic acid",
			"glycine" ,"histidine","isoleucine",
			"leucine",  "lysine", "methionine", 
			"phenylalanine", "proline", 
			"serine","threonine","tryptophan", 
			"tyrosine", "valine"};

		//sets up start time variable 
		long start = System.currentTimeMillis();
		
		//sets the stop time as 30 seconds 
		long stop = start + 30000;
		
		//sets up empty count variable to add score to
		int count = 0;
		
		// says to execute the code while the time is less than or equal to the stop variable of 30 seconds
		while (System.currentTimeMillis() <= stop) {
			
			Random random = new Random();
			
			//gets random index from the full names array  
			int q = random.nextInt(FULL_NAMES.length);
				
			System.out.println(FULL_NAMES[q]);
			
			//takes user input and assigns to a variable 
			String aString = System.console().readLine().toUpperCase();
			
			//if the users input correlates to the correct amino acid then run the code 
			if(aString.equals(SHORT_NAMES[q])) {
				
				//add 1 to the score when correct
				count = count + 1;
				System.out.println("Number correct: " + count);
				
				//calculates the time elapsed 
				long current = System.currentTimeMillis();
				long totalTime = (current - start)/1000;
			
				System.out.println("Time elapsed: " + totalTime + " seconds");

						}
				//if the user inputs 'quit', the code will end 
				else if (aString.equals("QUIT")) {
					
					System.out.println("You have selected to quit. Quiz is now over");
					return; 
					
					}
				//if the users input does not correlate to the correct amino acid then run the code 
				else {
					
					long current = System.currentTimeMillis();
					long totalTime = (current - start)/1000;
					System.out.println("That was incorrect. Total correct: " + count + " Time elapsed: " + totalTime + " seconds");
					//end the loop
					return;
					
			}
							
		}
		
	}

}

