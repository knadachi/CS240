import java.util.Arrays;

/** class that creates a CaesarCipher object with a method that converts plaintext to or from ciphertext */
public class CaesarCipher
{
   private String message;
   private int shift;

   /** constructor that assigns message and shift to a CaesarCipher object */
   public CaesarCipher( String m, int s )
   {
      message = m;
      shift = s;
   }

   /** converts plaintext to ciphertext */
   public String convert()
   {
      char[] temp = message.toCharArray(); //converts message to a character array
      String alphabet = "abcdefghijklmnopqrstuvwxyz"; //alphabet used if the shift value is positive
      char[] a = alphabet.toCharArray(); //converts alphabet to a character array
      String backwardsAlph = "zyxwvutsrqponmlkjihgfedcba"; //backwards alphabet used if the shift value is negative
      char[] b = backwardsAlph.toCharArray(); //converts backwardsAlph to a character array

      /** for loop that converts each letter to its shifted value */
      for( int i = 0; i < temp.length; i++ )
      {
         char letter = temp[ i ]; //stores the letter at index i of the temp array
         
         /** checks if the character at the current index is a space and skips it if it is */
         if( letter != ' ' )
         {
            /** checks if the shift value is positive or negative to determine which alphabet to use */
            if( shift > 0 )
               letter = a[ ((alphabet.indexOf( letter )) + shift) % 26 ]; //uses the remainder and shift value to determine which index of array a or b to use
            else if( shift < 0 )
                letter = b[ ((backwardsAlph.indexOf( letter )) - shift) % 26 ];
            temp[ i ] = letter; //stores the updated letter
         }   
      }

      return new String( temp ); //returns the encrypted or decrypted message as a String
   }
}
