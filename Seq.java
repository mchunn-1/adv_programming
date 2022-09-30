package lab3;

import java.util.Random; 
 
public class Seq {

	public static String generateRandomSequence(char[] alphabet, float[] weights, int length) throws Exception 
	
		{
			//empty string variable to add to 
			String seq  = "";
			//initializing sum  
			float sum = 0;
			//initializing the out 
			char out = 0;
			Random random = new Random();
			float f = random.nextFloat();
			//for all of the weights... loop through, add them up, and assign to the sum variable  	
			for (int i = 0; i < weights.length; i++) 
				{
					sum += weights[i];
					
						}		
			//checks that the round off error is correct... if/els
            if(Math.abs(sum-1) > 0.001) throw new Exception("sum of weights is not within round off error of 1");
			//checks that alphabet and weights have the same length 
			if(alphabet.length != weights.length) throw new Exception ("lengths are not equal");
			//checks that the length is >= 0
			if(length < 0) throw new Exception("legnth is less than 0");
					//loop for 30 mer
					for(int x = 0; x < length; x++)
					
					{
						//generating random float 
						f = random.nextFloat();
						//initializing sumWeights
						float sumWeights = 0;
						//re-establishing count as 0
						int count = 0;
						//loop through length of array 
						for(int i = 0; i < weights.length; i++)
		
						{			
							//while the random float is greater than sumWeights
							while(f > sumWeights) 
							{
							//add the current weight to sumWeights
							sumWeights = sumWeights + weights[i];
							//increase count by 1
							count = count + 1;
							//character at index count-1 is stored 
							out = alphabet[count-1];
							
							}
						
						}
						//add stored character to seq
						seq+= out;
						
					}
					//return the seq of added characters 
					return seq;
			
		}

	public static void main(String[] args) throws Exception {
		
		//initializing the DNA variables 
		float[] dnaWeights = { .3f, .3f, .2f, .2f };
		char[] dnaChars = { 'A', 'C', 'G', 'T'  };
		
		// a random DNA 30 mer
		System.out.println(generateRandomSequence(dnaChars, dnaWeights,30));
		
		//initializing the protein variables 
		float proteinBackground[] =
			{0.072658f, 0.024692f, 0.050007f, 0.061087f,
		        0.041774f, 0.071589f, 0.023392f, 0.052691f, 0.063923f,
		        0.089093f, 0.023150f, 0.042931f, 0.052228f, 0.039871f,
		        0.052012f, 0.073087f, 0.055606f, 0.063321f, 0.012720f,
		        0.032955f}; 
		char[] proteinResidues = 
				new char[] { 'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
							 'V', 'W', 'Y' };
		
		// a random protein with 30 residues
		System.out.println(generateRandomSequence(proteinResidues, proteinBackground, 30));
		
	}


}
