/* class that creates a singly linked list called Set */
public class Set
{
   private Node head;
   private int size;

   /* constructor that creates a Set object with a dummy head and sets its size to 0 */
   public Set()
   {
      head = new Node( null, null );
      size = 0;
   }

   /* returns true if the given object is contained in the set and false otherwise */
   public boolean contain( Object o )
   {
      Integer data = (Integer) o;
      Node current = head;
      /* traverses the list and searches for the given element */
      while( current != null )
      {
         if( data.equals( current.getElement() )){
            return true;
         }
         current = current.getNext();
      }
      return false;
   }

   /* returns true if the node containing the object is removed from the set and false otherwise */
   public boolean remove( Object o )
   {
      Integer data = (Integer) o;
      Node previous = head;
      Node current = head;
      /* traverses the list and searches for the given element (removes it if found ) */
      while( current != null )
      {
         if( data.equals( current.getElement() ))
         {
            previous.setNext( current.getNext() );
            size--;
            return true;
         }
         else{
            previous = current;
            current = current.getNext();
         }
      }
      return false;
   }

   /* returns false if element not added because it is already in the set and true if added */
   public boolean addElement( Object newData )
   {
      Node tNode = new Node( newData, null );
      /* uses the contain method to find newData */
      if( contain( newData ) ){
         return false;
      }
      Node current = head;
      /* adds newData to the end of the list if it isn't already in the list */
      while( current.getNext() != null )
      {
         current = current.getNext();
      }
      current.setNext( tNode );
      size++;
      return true;
   }

   /* returns an integer equal to the number of distinct objects in the set */
   public int size()
   {
      return size;
   }

   /* returns true if every element in set A is in set B and false otherwise */
   public boolean subsetOf( Set b )
   {
      Node current = head.getNext();
      /* traverses the list and checks if Set B has every element of Set A */
      while( current != null )
      {
         if( b.contain( current.getElement() )){
            current = current.getNext();
         }
         else{
            return false;
         }
      }
      return true;
   }

   /* returns true if set A and set B contain the same elements (order doesn't matter) */
   public boolean isEqual( Set b )
   {
      /* checks if the sets are the same size and checks if Set A is a subset of Set B */
      if( size == b.size() && subsetOf( b ) ){
         return true;
      }
      else{
         return false;
      }
   }

   /* returns a set that contains all elements in set A and B (no duplicate elements) */
   public Set union( Set b )
   {
      Set c = new Set();
      Node current = head.getNext();
      /* traverses Set A and adds all of its elements to Set C */
      while( current != null )
      {
         c.addElement( current.getElement() );
         current = current.getNext();
      }
      current = b.head.getNext();
      /* traverses Set B and adds all of its elements to Set C */
      while( current != null )
      {
         c.addElement( current.getElement() );
         current = current.getNext();
      }
      return c;
   }

   /* returns a set containing only elements that are common to both set A and B */
   public Set intersection( Set b )
   {
      Set c = new Set();
      Node current = head.getNext();
      /* traverses Set A and adds the elements that both Set A and Set B contain to Set C */
      while( current != null )
      {
         if( b.contain( current.getElement() )){
            c.addElement( current.getElement() );
         }
         current = current.getNext();
      }
      return c;
   }

   /* returns a set containing only elements that are in set A but not in B */
   public Set complement( Set b )
   {
      Set c = new Set();
      Node current = head.getNext();
      /* traverses Set A and adds its elements to Set C if they are not in Set B */
      while( current != null )
      {
         if( !b.contain( current.getElement() )){
            c.addElement( current.getElement() );
         }
         current = current.getNext();
      }
      return c;
   }

   /* returns the elements in the set as a String and its current size */
   public String toString()
   {
      Node current = head;
      String str = "{";
      while( current.getNext() != null )
      {
         str += current.getNext().getElement() + ", ";
         current = current.getNext();
      }
      if( str.length() > 2 ){
         str = str.substring( 0, str.length() - 2 );
      }
      str += "}\nSize = " + size;
      return str;
   }
}
