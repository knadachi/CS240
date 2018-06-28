import java.util.Scanner;

/* class that initializes and runs the user interface */
public class UI
{
   private Scanner input = new Scanner( System.in );
   
   /* constructor that passes in a HashTable object */
   public UI( HashTable table )
   {
      intro( table );
      prompt( table );
   }

   /* prints the number of collisions, size of the table, number of names and the min/max averages */
   public void intro( HashTable table )
   {
      System.out.println( "\n# of Collisions: " + table.getCollisions() );
      System.out.println( "Size of Table: " + table.getSize() + "\n" );
      System.out.println( "# of Names: " + table.getSize() );
 
      LinkedList list = minAvg( table );
      System.out.println( "Minimum Average: " + average( list.getHead() ));
      Entry current = list.getHead();
      while( current != null )
      {
         System.out.println( "  " + current.getName() );
         current = current.getNext();
      }

      list = maxAvg( table );
      System.out.println( "Maximum Average: " + average( list.getHead() ));
      current = list.getHead();
      while( current != null )
      {
         System.out.println( "  " + current.getName() + "\n" );
         current = current.getNext();
      }

      System.out.println( "Enter a name or enter nothing to quit." );
   }

   /** 
    * allows the user to enter and search for names in the hash table
    * prints their average score and the number of times they were added
    * exits the program when the user enters nothing
    */
   public void prompt( HashTable table )
   {
      System.out.print( "Enter Name: " );
      String name = input.nextLine().trim();
      if( !( name.equals( "" ) ))
      {
         Entry e = table.get( name );
         if( e == null ){
            System.out.println( "  " + name + " not found" );
         }
         else{
         System.out.println( "  " + name + ": Avg: " + average( e ) + "   # Scores: " + e.getCount() );
         }
         prompt( table );
      }
      else
      {
         System.out.println( "\nThank you for using this hash table program! (:\n" );
         input.close();
      }
   }

   /* returns the average of an individual entry */
   public double average( Entry e )
   {
      if( e == null ){
         return -1;
      }
      return e.getScore() / e.getCount();
   }

   /* returns the maximum average of the directory */
   public LinkedList maxAvg( HashTable t )
   {
      LinkedList[] table = t.getHashTable();
      LinkedList highest = new LinkedList();
      for( int i = 0; i < table.length; i++ )
      {
         LinkedList list = table[ i ];
         Entry current = list.getHead();
         while( current != null )
         {
            double newAvg = average( current );
            if( newAvg == -1 ){
               break;
            }
            else if( newAvg > average( highest.getHead() ))
            {
               highest = new LinkedList();
               highest.add( current.getName(), current.getHash(), newAvg );
            }
            else if( newAvg == average( highest.getHead() ) )
            {
               highest.add( current.getName(), current.getHash(), newAvg );
            }
            current = current.getNext();
         }
      }
      return highest;
   }

   /* returns the minimum average of the directory */
   public LinkedList minAvg( HashTable t )
   {
      LinkedList[] table = t.getHashTable();
      LinkedList lowest = new LinkedList();
      for( int i = 0; i < table.length; i++ )
      {
         LinkedList list = table[ i ];
         Entry current = list.getHead();
         while( current != null )
         {
            double newAvg = average( current );
            if( newAvg == -1 ){
               break;
            }
            else if( newAvg < average( lowest.getHead() ))
            {
               lowest = new LinkedList();
               lowest.add( current.getName(), current.getHash(), newAvg );
            }
            else if( average( lowest.getHead() ) == -1 || newAvg == average( lowest.getHead() ) )
            {
               lowest.add( current.getName(), current.getHash(), newAvg );
            }
            current = current.getNext();
         }
      }
      return lowest; 
   }
}
