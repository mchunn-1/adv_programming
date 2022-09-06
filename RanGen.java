package lab1;

import java.util.Random; 

public class RanGen {
	
	
	public static void main(String[] args) {
		
		// sets up variable for counting 
		int count = 0;
		
		//for loop to give 1000 3 mers
		for (int s =0; s <= 1000; s++) {
		
			Random random = new Random();
			
			//sets up string of bases to loop through 
			String bases = "ATCG";
		
			for (int i=0; i < 1; i++) {
			
				//establishes each character of the 3 mer
				char x = (bases.charAt(random.nextInt(bases.length()))); 
				char y = (bases.charAt(random.nextInt(bases.length()))); 
				char z = (bases.charAt(random.nextInt(bases.length()))); 
				
				//puts each character into one string 
				String ss = String.valueOf(x) +  String.valueOf(y) +  String.valueOf(z);
			
				//this print statement will print out the 1000 randomly generated DNA 3 mers when not commented out 
				//System.out.println((ss));
				
				//if the 3 mer is equal to "AAA", the count increases by one
				if (ss.equals("AAA"))
					count = count + 1;
			}
			
		}
		//We would expect around 15.6 times (.25 * .25 * .25 = 0.0156 * 1000 = 15.625).. Java is close to the number expected
		System.out.println(("Question 2: The 3 mer AAA was printed " +  count + " times."));
		
		int count2 = 0;
		
		for (int s =0; s <= 1000; s++) {
		
			//sets up empty string for the bases to be added to 
			String base = "";
			
			//loop to make 3 mer
			while (base.length() < 3 ) {
		
				Random ran = new Random();
				//gives random number between 0 and 1
				float f = ran.nextFloat();	
		
				String a = "";
				//if statements to establish frequency 
				if (f < .13) 
					a = a + "A";
				else if (f < .51 && f > .12 )
					a = a + "C";
				else if(f < .90 && f > .50 )
					a = a + "G";
				else if (f >= .90 )
					a = a + "T";
				base = base + a;
		
			}
		
			//this will print out 1000 DNA 3 mers of the modified frequency  
		//System.out.println((base));
			
	
		if (base.equals("AAA"))
			count2 = count2 + 1;

		} 
		
		//we would expect AAA to appear around 1.7 times... Java produces AAA at a similar frequency  
		System.out.println(("Question 3 (modified frequency): The 3 mer AAA was printed " +  count2 + " times."));
	}

}



