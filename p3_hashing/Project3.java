/*
 * This program provides the skeleton to process multiple files from a directory
 * The directory name is provided in args[0] as I call "java Testdir hashingdata"
 */
import java.io.*;
import java.util.*;

class Project3 
{
   public static void main(String[] args) throws IOException 
   {
      if (args.length < 1)
      {
         System.out.println("Error: Directory name is missing");
         System.out.println("Usage: java scoreProcess directory_name");
         return;
      }

      /* creates the hash table */
      HashTable table = new HashTable();

      File directory = new File(args[0]); // args[0] contains the directory name
      File[] files = directory.listFiles(); // get the list of files from that directory

      File file;
      Scanner input;

      // process the arguments stores in args
      for (int i = 0; i < files.length; i++) 
      {
         input = new Scanner(files[i]);
 
         /* error checking occurs here */
         String key;
         Double value;
         while (input.hasNext())
         {
      	    key = input.nextLine();
            key = key.trim();
	    int end = key.lastIndexOf( " " );
            String s = key.substring( end + 1 );
            
            /* inserts the (key, value) pair into the hash table */
            try
            {
               value = new Double( Double.parseDouble( s ) );
               key = key.substring( 0, end );
               key = key.trim();
               table.put( key, value );
            }
            catch( NumberFormatException e )
            {
               System.out.println( "\nInvalid Line: \"" + key + "\" not added."  ); 
            }	
         }
      }
      /* initializes runs the user interface */
      UI ui = new UI( table );
   }
}
