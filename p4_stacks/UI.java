import java.util.Scanner;

/* class that runs the user interface */
public class UI
{
   private Scanner input = new Scanner( System.in );

   /* constructor that begins the user interface */
   public UI()
   {
      intro();
   }

   /* prints the opening line of the program and runs the prompt */
   public void intro()
   {
      System.out.println( "\nWelcome to the Stack Application Program!" );
      prompt();
   }

   /** 
    * prompts the user for an infix expression
    * converts the validated infix expression to postfix and prefix expressions and prints them
    * exits the program when the user enters nothing
    */
   public void prompt()
   {
      System.out.print( "\nEnter the infix expression (enter nothing to quit): " );
      String infix = input.nextLine();
      if( infix.equals( "" ) )
      {
         System.out.println( "\nThank you for using this Stack Application Program! :)\n" );
         input.close();
         System.exit( 0 );
      }
      infix = infix.replaceAll( "\\s", "" );
      if( validate( infix ) )
      {
         Conversion expression = new Conversion( infix );
         System.out.println( "Postfix: " + expression.postfix() );
         System.out.println( "Prefix:  " + expression.prefix() );
      }
      else{
         System.out.println( "Invalid Input" );
      }
      prompt();
   }

   /**
    * checks if the infix expression is invalid
    * returns false if the infix cannot be converted and true otherwise
    */
   public boolean validate( String infix )
   {
      int parentheses = 0;
      int operators = 0;
      int operands = 0;

      /* traverses the infix expression */
      for( int i = 0; i < infix.length(); i++ )
      {
         char c = infix.charAt( i );
         
         if( c == '(' ){
            parentheses++;
         }
         else if( c == ')' ){
            parentheses--;
         }
         else if( c == '*' || c == '/' || c == '%' || c == '+' || c == '-' ){
            operators++;
         }
         else if( c != '(' || c != ')' || c != '*' || c != '/' || c != '%' || c != '+' || c != '-' ){
            operands++;
         }
         
         if( parentheses < 0 || ( ( operators != operands - 1 ) && operators != operands ) ){
            return false;
         }
      }
      if( operators == operands ){
         return false;
      }
      if( parentheses == 0 ){
         return true;
      }
      return false;
   }
}
