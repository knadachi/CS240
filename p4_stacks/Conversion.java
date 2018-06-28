/* class that performs the conversion of an infix expression to postfix and prefix */
public class Conversion
{
   private String expression;
   private NodeStack operators;

   /* creates a Conversion object that sets the infix expression and creates a new stack*/
   public Conversion( String infix )
   {
      expression = infix;
      operators = new NodeStack();
   }

   /**
    * checks if the character is an operator
    * returns true if it is an operator and false otherwise
    */
   public boolean isOperator( char c )
   {
      return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '(' || c == ')';
   }

   /**
    * checks if op1 has a lower or equal precedence to op2
    * returns true if it has a lower or equal precedence and false otherwise
    */
   public boolean precedence( char op1, char op2 )
   {
      switch( op1 )
      {
         case '+':
         case '-':
            return op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == '%' || op2 == ')';
         case '*':
         case '/':
         case '%':
            return op2 == '*' || op2 == '/' || op2 == '%' || op2 == ')';
         case '(':
            return false;
         default:
            return true;
      }
   }

   /* method that converts the infix expression to postfix */
   public String postfix()
   {
      String postfix = "";
      char current = ' ';

      /* traverses the infix expression */
      for( int i = 0; i < expression.length(); i++ )
      {
         current = expression.charAt( i );

         if( !isOperator( current ) ){
            postfix += current;
         }
         else
         {
            if( current == ')' )
            {
               boolean done = false;
               while( !operators.isEmpty() && !done )
               {
                  if( operators.top() == '(' )
                  {
                     operators.pop();
                     done = true;
                  }
                  else{
                     postfix += operators.pop();
                  }
               }
            }
            else
            {
               if( operators.isEmpty() || !precedence( current, operators.top() )){
                  operators.push( current );
               }
               else
               {
                  boolean pushed = false;
                  while( !operators.isEmpty() && !pushed )
                  {
                     if( precedence( current, operators.top()))
                     {
                       char temp = operators.pop();
                       if( current != '(' ){
                          postfix += temp;
                       }
                     }
                     if( operators.isEmpty() || !precedence( current, operators.top()) || operators.top() == '(' )
                     {
                        pushed = true;
                        operators.push( current );
                     }
                  }
               }
            }
         }
      }

      while( !operators.isEmpty() )
      {
         postfix += operators.pop();
      }

      return postfix;
   }

   /* method that converts the infix expression to prefix */
   public String prefix()
   {
      String rev = new StringBuilder( expression ).reverse().toString();
      rev = rev.replace( '(', '|' );
      rev = rev.replace( ')', '(' );
      rev = rev.replace( '|', ')' );

      String prefix = "";
      char current = ' ';

      /* traverses the infix expression */
      for( int i = 0; i < rev.length(); i++ )
      {
         current = rev.charAt( i );

         if( !isOperator( current ) ){
            prefix += current;
         }
         else
         {
            if( current == ')' )
            {
               boolean done = false;
               while( !operators.isEmpty() && !done )
               {
                  if( operators.top() == '(' )
                  {
                     operators.pop();
                     done = true;
                  }
                  else{
                     prefix += operators.pop();
                  }
               }
            }
            else
            {
               if( operators.isEmpty() || !precedence( current, operators.top()) ||((precedence( current, operators.top()) && precedence( operators.top(), current )))){
                  operators.push( current );
               }
               else
               {
                  boolean pushed = false;
                  while( !operators.isEmpty() && !pushed)
                  {
                     if( precedence( current, operators.top() ) || !((precedence( current, operators.top()) && precedence( operators.top(), current ))))
                     {
                        char temp = operators.pop();
                        if( current != ')' ){
                           prefix += temp;
                        }
                     }

                     if( operators.isEmpty() || (!precedence( current, operators.top()) || ((precedence( current, operators.top()) && precedence( operators.top(), current )))) || !precedence( current, operators.top()) || operators.top() == '(' )
                     {
                        pushed = true;
                        operators.push( current );
                     }
                  }
               }
            }
         }
      }

      while( !operators.isEmpty() )
      {
         prefix += operators.pop();
      }

      prefix = new StringBuilder( prefix ).reverse().toString();
      return prefix;
   }
}
