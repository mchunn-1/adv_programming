package lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FastaSequence 
{
	//initiates new StringBuffer objects for headers/sequences 
	private StringBuffer header = new StringBuffer();
	private StringBuffer seq = new StringBuffer();
	
	public static List<FastaSequence> readFastaFile(String filepath) throws Exception
	{
		//initiates list interface.. new list of FastaSequence type  
		List<FastaSequence> list = new ArrayList <FastaSequence>();
		//reads in file 
		BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
		//sets line of file to variable  
		String line = reader.readLine();
		//while line is not null...
		while(line != null)
		{
			//creates new FastaSequence object
			FastaSequence fs = new FastaSequence(); 
			//if the line starts with >
			if(line.startsWith(">")) 
			{
			//take off > and replace with nothing 	
			line = line.replaceAll(">", "");
			//append the line to header
			fs.header.append(line);
			
			}
			
			//add object fs to list 
			list.add(fs);
			line = reader.readLine();
			
			//while the line does not equal null and does not start with >...
			while (line != null && ! line.startsWith(">"))
			{
				//append line to seq
				fs.seq.append(line);
				line = reader.readLine();

			}
			
		}
		reader.close();
		//returns list of FastaSequence objects 
		return list;
	
	}

		//overriding toString() to correctly return contents of header/seq
		@Override
		public String toString() 
		{
		return header + " " + seq;
		}


		// returns the header of this sequence without the “>”
		public String getHeader()
		{
			String head = header.toString();
			return head;
			
		}

		// returns the sequence of this FastaSequence
		public String getSequence() 
		{
			String sequence = seq.toString();
			return sequence;
		}
		
		// returns the number of G’s and C’s divided by the length of this sequence
		public float getGCRatio()
		{
			//initializing the counters 
			float Ccounter = 0;
			float Gcounter = 0;
			
			//looping through the sequence 
			for(int i =0; i < seq.length(); i++) 
			{
				//if the character at the given index is C, add 1 to the respective counter 
				if (seq.charAt(i) == 'C') 
				{
					Ccounter += 1;
				}
				//if the character at the given index is G, add 1 to the respective counter 
				if (seq.charAt(i) == 'G') 
				{
					Gcounter += 1;
				}
			}
			
			
			float total = 0;
			//adding the number of C's and G's together 
			total = Ccounter + Gcounter;
			float ratio = 0;
			//dividing the total C/G count by the sequence length
			ratio = (total / seq.length());
			return ratio;
			
		}
		
		//returns number of A's in the sequence 
		public int getnumA()
		{
			//initializes counter 
			int aCount = 0;
			//loops through sequence 
			for(int i =0; i < seq.length(); i++) 
			{
				//if the character at the given index is equal to A, add 1 to the counter 
				if (seq.charAt(i) == 'A') 
				{
					aCount += 1;
				}
			}
		
			return aCount;
			
		}
		
		//returns number of C's in the sequence 
		public int getnumC()
		{
			int cCount = 0;
			
			for(int i =0; i < seq.length(); i++) 
			{
				if (seq.charAt(i) == 'C') 
				{
					cCount += 1;
				}
			}
		
			return cCount;
			
		}
		
		//returns number of G's in the sequence 
		public int getnumG()
		{
			int gCount = 0;
			
			for(int i =0; i < seq.length(); i++) 
			{
				if (seq.charAt(i) == 'G') 
				{
					gCount += 1;
				}
			}
		
			return gCount;
			
		}
		
		//returns number of T's in the sequence 
		public int getnumT()
		{
			int tCount = 0;
			
			for(int i =0; i < seq.length(); i++) 
			{
				if (seq.charAt(i) == 'T') 
				{
					tCount += 1;
				}
			}
		
			return tCount;
			
		}

		public static void writeTableSummary( List<FastaSequence> list, File outputFile) throws Exception
		{
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			//labels the column.. "\t" =  tab, "\n"= new line
			writer.write("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence\n");
			
			//loops through FastaSequence object in the list 
			 for(FastaSequence fs : list)
		     {
				 //calls on methods for desired output for each fasta sequence and writes to file 
				 writer.write(fs.getHeader() + "\t" + fs.getnumA() + 
						 							"\t" + fs.getnumC() +
						 							"\t" + fs.getnumG() + "\t" + fs.getnumA() + 
						 							"\t" + fs.getSequence() + "\n");		 
		     }
			
			writer.flush();
			writer.close();
		}





public static void main(String[] args) throws Exception
{
     List<FastaSequence> fastaList = FastaSequence.readFastaFile("/Users/morganchunn/Desktop/fasta.txt");

   // loop through each FastaSequence object from the list 
   for(FastaSequence fs : fastaList)
	   
     {
	   //call each method on the given object   
       System.out.println(fs.getHeader());
       System.out.println(fs.getSequence());
       System.out.println(fs.getGCRatio());
     }
     
  
     //for writing out the file 
     File myFile = new File("/Users/morganchunn/Desktop/out.txt");

     writeTableSummary( fastaList,  myFile);
}

}
