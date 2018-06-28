/* class that creates a Node object that contains methods to access it */
public class Node
{
   private Object element;
   private Node next;

   /* constructor that assigns element and next to a Node object */
   public Node( Object e, Node n )
   {
      element = e;
      next = n;
   }

   /* returns the Node's element */
   public Object getElement()
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
