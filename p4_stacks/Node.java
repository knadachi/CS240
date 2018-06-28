/* class that creates a Node object and contains methods to access it */
public class Node
{
   private char element;
   private Node next;

   /* default constructor that assigns element to a apace and next to null */
   public Node()
   {
      this( ' ', null );
   }

   /* constructor that assigns an element to a character and next to a Node */
   public Node( char c, Node n )
   {
       element = c;
       next = n;
   }

   /* returns the Node's element */
   public char getElement()
   {
      return element;
   }

   /* returns the next Node */
   public Node getNext()
   {
      return next;
   }

   /* sets the next Node */
   public void setNext( Node n )
   {
      next = n;
   }
}
