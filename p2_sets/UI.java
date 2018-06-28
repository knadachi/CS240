import java.util.Scanner;
import java.util.InputMismatchException;

/* class that organizes the user interface */
public class UI

{
   private Scanner input = new Scanner( System.in );
   private Set a = null;
   private Set b = null;

   /* creates a UI object with Set A and Set B */
   public UI( Set a, Set b )
   {
      this.a = a;
      this.b = b;
      intro();
   }

   /* prints the opening line of the program */
   public void intro()
   {
      System.out.println( "\nWelcome to the Set Program!" );
   }

   /* method that starts the program */
   public void start()
   {
      menu();
   }

   /* method that runs the main menu of the program */
   public void menu()
   {
      int decision = 0;
      try
      {
         System.out.println( "\nCurrent Sets:" );
         System.out.println( "A = " + a );
         System.out.println( "B = " + b );
         System.out.println( "\nWhat would you like to do?" );
         System.out.println( "1: Edit a Set" );
         System.out.println( "2: Is Set A a Subset of Set B?" );
         System.out.println( "3: Is Set A Equal to Set B?" );
         System.out.println( "4: Union of Set A and Set B" );
         System.out.println( "5: Intersection of Set A and Set B" );
         System.out.println( "6: Complement of Set A" );
         System.out.println( "7: Quit" );
         System.out.print( "Your Input: " );
         decision = input.nextInt();
         if( decision == 1 ){
            editMenu();
         }
         else if( decision == 2 )
         {
            if( a.subsetOf( b ) ){
               System.out.println( "\nSet A is a subset of Set B." );
            }
            else{
               System.out.println( "\nSet A is not a subset of Set B." );
            }
         }
         else if( decision == 3 )
         {
            if( a.isEqual( b ) ){
               System.out.println( "\nSet A is equal to Set B." );
            }
            else{
               System.out.println( "\nSet A is not equal to Set B." );
            }
         }
         else if( decision == 4 ){
            System.out.println( "\nUnion: C = " + a.union( b ) );
         }
         else if( decision == 5 ){
            System.out.println( "\nIntersection: C = " + a.intersection( b ) );
         }
         else if( decision == 6 ){
            System.out.println( "\nComplement: C = " + a.complement( b ) );
         }
         else if( decision == 7 )
         {
            System.out.println( "\nThank you for using this program! (:\n" );
            input.close();
            System.exit( 0 );
         }
         /* runs the default test (isn't mentioned as an option in the main menu but will run if user enters 8) */
         else if( decision == 8 )
         {
            test();
         }
         else{
            System.out.println( "\nInvalid Input. Please enter a number 1-7." );
         }
         menu();
      }
      catch( InputMismatchException e )
      {
         System.out.println( "\nInvalid Input. Please enter a number." );
         input.nextLine();
         menu();
      }
   }

   /* method that runs the edit menu where you can choose to edit a certain set */
   public void editMenu()
   {
      int decision = 0;
      try
      {
         System.out.println( "\nWhich set would you like to edit?" );
         System.out.println( "1: Set A" );
         System.out.println( "2: Set B" );
         System.out.println( "3: Return to Main Menu" );
         System.out.print( "Your Input: " );
         decision = input.nextInt();
         if( decision == 1 ){
            System.out.println( "Updated Set A = " + edit( a ) );
         }
         else if( decision == 2 ){
            System.out.println( "Updated Set B = " + edit( b ) );
         }
         else if( decision == 3 ){
            menu();
         }
         else{
            System.out.println( "\nInvalid Input. Please enter a number 1-3." );
         }
         editMenu();
      }
      catch( InputMismatchException e )
      {
         System.out.println( "\nInvalid Input. Please enter a number." );
         input.nextLine();
         editMenu();
      }
   }

   /* method that returns an edited set (dependent on user's input) */
   public Set edit( Set s )
   {
      int decision = 0;
      try
      {
         System.out.println( "\nWould you like to add or remove?" );
         System.out.println( "1: Add" );
         System.out.println( "2: Remove" );
         System.out.println( "3: Return to Main Menu" );
         System.out.print( "Your Input: " );
         decision = input.nextInt();
         if( decision == 1 )
         {
            System.out.print( "\nEnter the number you would like to add: " );
            decision = input.nextInt();
            try
            {
               s.addElement( decision );
               return s;
            }
            catch( InputMismatchException e )
            {
               System.out.println( "\nInvalid Input. Please try again." );
               input.nextLine();
               edit( s );
            }
         }
         else if( decision == 2 )
         {
            System.out.print( "\nEnter the number you would like to remove: " );
            decision = input.nextInt();
            try
            {
               s.remove( decision );
               return s;
            }
            catch( InputMismatchException e )
            {
               System.out.println( "\nInvalid Input. Please try again." );
               input.nextLine();
               edit( s );
            }
         }
         else if( decision == 3 )
         {
            menu();
         }
         else{
            System.out.println( "\nInvalid Input. Please enter a number 1-3." );
         }
         editMenu();
      }
      catch( InputMismatchException e )
      {
         System.out.println( "\nInvalid Input. Please try again." );
         input.nextLine();
         edit( s );
      }
      return s;
   }

   /* method that runs the default test */
   public void test()
   {
      System.out.println( "\n------------------------------------------------------------" );
      System.out.println( "Begin Default Test" );
      
      /* Testing the Add and Remove Methods */
      System.out.println( "------------------------------------------------------------\n" );
      System.out.println( "Testing the Add and Remove Methods: \n" );
      a.addElement( 1 );
      System.out.println( "\nAdded 1 to Set A:\nA = " + a );
      a.addElement( 2 );
      System.out.println( "\nAdded 2 to Set A:\nA = " + a );
      a.addElement( 3 );
      System.out.println( "\nAdded 3 to Set A:\nA = " + a );
      a.addElement( 1 );
      System.out.println( "\nAttempts to Add 1:\nA = " + a );
      a.remove( 3 );
      System.out.println( "\nRemoved 3 from Set A:\nA = " + a );
      a.remove( 1 );
      System.out.println( "\nRemoved 1 from Set A:\nA = " + a );
      a.remove( 1 );
      System.out.println( "\nAttempts to Remove 1 from Set A:\nA = " + a );
      a.addElement( 1 );
      System.out.println( "\nAdded 1 to Set A:\nA = " + a );
      a.remove( 2 );
      System.out.println( "\nRemoved 2 from Set A:\nA = " + a );
      a.remove( 1 );
      System.out.println( "\nRemoved 1 from Set A:\nA = " + a + "\n" );

      /* Testing Contain Method */
      System.out.println( "------------------------------------------------------------\n" );
      System.out.println( "Testing the Contain Method: \n" );
      a.addElement( 1 );
      a.addElement( 2 );
      a.addElement( 3 );
      System.out.println( "\nA = " + a );
      System.out.println( "Set A Contains 1: " + a.contain( 1 ) );
      System.out.println( "Set A Contains 2: " + a.contain( 2 ) );
      System.out.println( "Set A Contains 3: " + a.contain( 3 ) );
      System.out.println( "Set A Contains 4: " + a.contain( 4 ) );
      a.remove( 2 );
      System.out.println( "\nRemoved 2 from Set A:\nA = " + a );
      System.out.println( "Set A Contains 2: " + a.contain( 2 ) + "\n" );
      a.remove( 1 );
      a.remove( 3 );

      /* Case 1 */
      System.out.println( "------------------------------------------------------------\n" );
      System.out.println( "Testing Case 1:" );
      System.out.println( "A and B are equal but distinct sets." );
      a.addElement( 1 );
      a.addElement( 2 );
      a.addElement( 3 );
      b.addElement( 2 );
      b.addElement( 1 );
      b.addElement( 3 );
      
      System.out.println( "\nA = " + a );
      System.out.println( "B = " + b );

      System.out.println( "\nSubset: " + a.subsetOf( b ) );
      System.out.println( "\nEqual Sets: " + a.isEqual( b ) );
      System.out.println( "\nUnion:\nC = " + a.union( b ) );
      System.out.println( "\nIntersection:\nC = " + a.intersection( b ) );
      System.out.println( "\nComplement:\nC = " + a.complement( b ) + "\n" );

      a.remove( 1 );
      a.remove( 2 );
      a.remove( 3 );
      b.remove( 2 );
      b.remove( 1 );
      b.remove( 3 );
      
      /* Case 2 */
      System.out.println( "------------------------------------------------------------\n" );
      System.out.println( "Testing Case 2:" );
      System.out.println( "A and B are sets have different sizes and one is the subset" );
      System.out.println( "of the other." );
      a.addElement( 1 );
      b.addElement( 1 );
      b.addElement( 2 );

      System.out.println( "\nA = " + a );
      System.out.println( "B = " + b );

      System.out.println( "\nSubset: " + a.subsetOf( b ) );
      System.out.println( "\nEqual Sets: " + a.isEqual( b ) );
      System.out.println( "\nUnion:\nC = " + a.union( b ) );
      System.out.println( "\nIntersection:\nC = " + a.intersection( b ) );
      System.out.println( "\nComplement:\nC = " + a.complement( b ) + "\n" );

      a.remove( 1 );
      b.remove( 1 );
      b.remove( 2 );
      
      /* Case 3 */
      System.out.println( "------------------------------------------------------------\n" );
      System.out.println( "Testing Case 3:" );
      System.out.println( "A and B are non-empty sets and different in size but have" );
      System.out.println( "common elements." );
      a.addElement( 1 );
      a.addElement( 2 );
      a.addElement( 3 );
      b.addElement( 2 );
      b.addElement( 3 );
      b.addElement( 4 );
      b.addElement( 5 );

      System.out.println( "\nA = " + a );
      System.out.println( "B = " + b );

      System.out.println( "\nSubset: " + a.subsetOf( b ) );
      System.out.println( "\nEqual Sets: " + a.isEqual( b ) );
      System.out.println( "\nUnion:\nC = " + a.union( b ) );
      System.out.println( "\nIntersection:\nC = " + a.intersection( b ) );
      System.out.println( "\nComplement:\nC = " + a.complement( b ) + "\n" );

      a.remove( 1 );
      a.remove( 2 );
      a.remove( 3 );
      b.remove( 2 );
      b.remove( 3 );
      b.remove( 4 );
      b.remove( 5 );
      
      /* Case 4 */
      System.out.println( "------------------------------------------------------------\n" );
      System.out.println( "Testing Case 4:" );
      System.out.println( "A and B are non-empty sets with nothing in common." );
      a.addElement( 1 );
      b.addElement( 2 );
      b.addElement( 3 );

      System.out.println( "\nA = " + a );
      System.out.println( "B = " + b );

      System.out.println( "\nSubset: " + a.subsetOf( b ) );
      System.out.println( "\nEqual Sets: " + a.isEqual( b ) );
      System.out.println( "\nUnion:\nC = " + a.union( b ) );
      System.out.println( "\nIntersection:\nC = " + a.intersection( b ) );
      System.out.println( "\nComplement:\nC = " + a.complement( b ) + "\n" );

      a.remove( 1 );
      b.remove( 2 );
      b.remove( 3 );
      
      /* Case 5 */
      System.out.println( "------------------------------------------------------------\n" );
      System.out.println( "Testing Case 5:" );
      System.out.println( "Set A is empty and set B is non-empty." );
      b.addElement( 1 );
      b.addElement( 2 );

      System.out.println( "\nA = " + a );
      System.out.println( "B = " + b );

      System.out.println( "\nSubset: " + a.subsetOf( b ) );
      System.out.println( "\nEqual Sets: " + a.isEqual( b ) );
      System.out.println( "\nUnion:\nC = " + a.union( b ) );
      System.out.println( "\nIntersection:\nC = " + a.intersection( b ) );
      System.out.println( "\nComplement:\nC = " + a.complement( b ) + "\n" );

      b.remove( 1 );
      b.remove( 2 );

      /* Case 6 */
      System.out.println( "------------------------------------------------------------\n" );
      System.out.println( "Testing Case 6:" );
      System.out.println( "Set B is empty and set A is non-empty." );
      a.addElement( 1 );
      a.addElement( 2 );

      System.out.println( "\nA = " + a );
      System.out.println( "B = " + b );

      System.out.println( "\nSubset: " + a.subsetOf( b ) );
      System.out.println( "\nEqual Sets: " + a.isEqual( b ) );
      System.out.println( "\nUnion:\nC = " + a.union( b ) );
      System.out.println( "\nIntersection:\nC = " + a.intersection( b ) );
      System.out.println( "\nComplement:\nC = " + a.complement( b ) + "\n" );

      a.remove( 1 );
      a.remove( 2 );

      System.out.println( "------------------------------------------------------------" );
      System.out.println( "Test Complete." );
      System.out.println( "------------------------------------------------------------" );
   }
}
