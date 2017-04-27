import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Driver class, that calls methods and invokes constructors from NTuple.java and PlagiarismChecker.java.
 * Files to be compared are opened and read here. In case of other source of input, driver can be modified. Others don't need to be.
 * Created by gautambhat on 2/3/17.
 */
public class Driver {

    public static void main (String[] args) throws IOException {
    	
        if ( args.length == 0) {
        	
        	System.out.println("No command-line arguments provided!\nPlease re-run the program with 3 required arguments, and one optional argument, as described below (space-separated).");
        	System.out.println("1.\tfile name for a list of synonyms\n2.\tinput file 1\n3.\tinput file 2\n4.\t(optional) the number N, the tuple size.  If not supplied, the default (N=3) will be selected.");
        	System.out.println("\nExample:\tjava Driver syns.txt file1.txt file2.txt\n\n...END");
            System.exit(0);
            
        } else if( args.length < 3 || args.length > 4) {
        	
            System.out.println("Incorrect Number Of Arguments Provided!\tExiting...");
            System.out.println("\n...END");
            System.exit(0);
        }
        
        String synFile = args[0];
        String file1File = args[1];
        String file2File = args[2];
        
        int N = 3;
        try {
        	
        	N = args.length == 4 ? Integer.parseInt(args[3]) : 3;
        	
        } catch(Exception ex) {
        	
            System.err.println("Exception occured trying to parse input number. Make sure not to enter illegal chatracters.");
            System.out.println("\n...END");
            ex.printStackTrace();
            System.exit(0);
            
        }
        
        /*
         * In case location of file is not clear, print absolute path to help with debugging.
         */
        //System.out.println(new File(".").getAbsolutePath()); 
        
        String file1Contents, file2Contents;
        
        try {
        	
        	file1Contents = new String(Files.readAllBytes(Paths.get(file1File)));
            file2Contents = new String(Files.readAllBytes(Paths.get(file2File))); 
            
            /*
             * Call the constructor of plagiarism checker. Since we want to calculate percentage,
             * we've set the percentage argument (last argument) to true.
             * 
             */
            PlagiarismChecker P = new PlagiarismChecker(file1Contents, file2Contents, synFile, N, true);
            //System.out.println(P);
            
            int matchPercent = P.checkPlagiarism();
            
            System.out.println(matchPercent + "%");
            
        } catch (Exception ex) {
        	
        	System.err.println("Exception occurred trying to read the files. " + ex);
			ex.printStackTrace();

        }
        
    }
}
