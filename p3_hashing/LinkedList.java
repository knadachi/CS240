/* class that creates a singly linked list */
public class LinkedList 
{
   private Entry head;

   /* constructor that creates a singly linked list without a dummy head */
   public LinkedList()
   {
      head = null;
   }

   /* returns the head of the linked list */
   public Entry getHead()
   {
      return head;
   }

   /**  
    * returns the entry if it is already in the list
    * returns null otherwise
    */
   public Entry contains( String name )
   {
      Entry current = head;
      while( current != null )
      {
         if( name.equals( current.getName() )){
            return current;
         }
         current = current.getNext();
      }
      return null;
   }

   /**
    * returns the old value if a name is already in the list 
    * returns -1 if the name is added to the list
    */
   public double add( String name, int hash, double score )
   {
      Entry temp = contains( name );
      if( temp == null )
      {
         temp = new Entry( name, hash, 1, score, null );
         if( head == null ){
            head = temp;
         }
         else
         {
            Entry current = head;
            while( current.getNext() != null ){
               current = current.getNext();
            }
            current.setNext( temp ); 
         }
         return -1;
      }
      double oldValue = temp.getScore();
      temp.incrementCount();
      temp.addScore( score );
      return oldValue;
   }
}
