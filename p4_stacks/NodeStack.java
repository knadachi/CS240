/* class that creates a stack using a singly linked list called NodeStack */
public class NodeStack
{
   private Node top;

   /* constructor that creates an empty NodeStack */
   public NodeStack()
   {
      top = null;
   }

   /** 
    * checks if the stack is empty 
    * returns true if it is empty and false otherwise 
    */
   public boolean isEmpty()
   {
      if( top == null ){
         return true;
      }
      return false;
   }

   /* adds an element to the top of the stack */
   public void push( char element )
   {
      if( isEmpty() ){
         top = new Node( element, null );
      }
      else{
         top = new Node( element, top );
      }
   }

   /* returns the top element on the stack */
   public char top()
   {
      return top.getElement();
   }

   /* removes the element at the top of the stack */
   public char pop()
   {
      if( isEmpty() ){
         return top.getElement();
      }
      char temp = top.getElement();
      top = top.getNext();
      return temp;
   }
}
