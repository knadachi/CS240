import java.util.Scanner;

/** class that contains the main method that runs the program */
public class Project1
{
   public static void main( String[] args )
   {
      Scanner input = new Scanner( System.in ); //opens the scanner

      String message = null; //assigned null so that it runs through the while loop at least once

      System.out.print( "\nEnter a word or phrase you would like to encrypt (enter nothing to quit): " ); //prompts the user for a word or phrase to encrypt

      /** continues to prompt the user for input until they enter nothing */
      while( !( ( message = input.nextLine() ).equals( "" ) ) )
      {
         boolean success = false; //assigned false to run through while loop at least once
         int shift = 0; //shift value

         /** checks whether the user inputted anything other than an integer */
         while( !success ) 
         {
            try
            {
               System.out.print( "Enter a value you would like to shift it by: " ); //prompts the user for a shift value
               shift = input.nextInt(); //stores the user input
               success = true; //exits while loop
            }
            catch( Exception e )
            {
               System.out.println( "\nPlease enter a numerical value." ); //printed if user doesn't enter a numberical value
            }
            input.nextLine(); //takes in the leftover line break that nextInt() left
         }

         CaesarCipher conversion = new CaesarCipher( message, shift ); //creates a CaesarCipher object
         String cipherText = conversion.convert(); //calls the convert() method to encrypt the word or phrase
         System.out.println( cipherText + "\n" ); //prints out the encrypted word or phrase

         int decrypt = 0; //assigned 0 so that it runs through the while loop at least once
         
         /** continues to ask the user if they would like to decrypt the word or phrase */
         while( decrypt != 1 && decrypt != 2 )
         {
            try
            {
               System.out.print( "Would you like to decrypt the message? ( 1-Yes / 2-No ): " ); //asks the user if they want to decrypt
               decrypt = input.nextInt(); //stores the user input

               /** decrypts the word or phrase if the user wants to */
               if( decrypt == 1 )
               {
                  CaesarCipher normal = new CaesarCipher( cipherText, -shift ); //creates a CaesarCipher object
                  String plainText = normal.convert(); //calls the convert() method to decrypt the word or phrase
                  System.out.println( plainText ); //prints out the decrypted word or phrase
               }
               else if( decrypt != 1 && decrypt != 2 )
                  System.out.println( "\nPlease enter 1-Yes or 2-No." ); //prints if the user enters a number other than 1 or 2
            }
            catch( Exception e )
            {
               System.out.println( "\nPlease enter 1-Yes or 2-No."); //prints if the user enters something other than an integer
            }
            input.nextLine(); //takes in the leftover line break that nextInt() left
         }

         System.out.print( "\nEnter a word or phrase you would like to encrypt (enter nothing to quit): " ); //prompts the user to enter a word or phrase again
      }

      System.out.println( "\nThank you for using this Caesar Cipher program! (:\n" ); //message printed when user ends program

      input.close(); //closes the scanner
   }
}
