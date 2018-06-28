import java.util.Random;

/* class that creates a hash table */
public class HashTable
{
   private int size;
   private int collisions;
   private int threshold;
   private LinkedList[] hashTable;
   private final float LOAD_FACTOR = 0.75F;

   /* default constructor that creates a hash table of capacity 16 */
   public HashTable()
   {
      this( 16 );
   }

   /* constructor that creates a hash table of any capacity */
   public HashTable( int capacity )
   {
      size = 0;
      collisions = 0;
      threshold = (int)( capacity * LOAD_FACTOR );
      hashTable = new LinkedList[ capacity ];
      initializeArray( hashTable );
   }

   /* initializes array of linked lists */
   public void initializeArray( LinkedList[] table )
   {
      for( int i = 0; i < table.length; i++ )
      {
         table[ i ] = new LinkedList();
      }
   }

   /* returns the array of linked lists */
   public LinkedList[] getHashTable()
   {
      return hashTable;
   }

   /* returns the size */
   public int getSize()
   {
      return size;
   }

   /* returns the number of collisions */
   public int getCollisions()
   {
      return collisions;
   }

   /* returns a hash code for a specified String */
   static int hash( String s )
   {
      int hashCode = 0;
      for( int i = 0; i < s.length(); i++ )
      {
         hashCode = ( hashCode << 27 ) | ( hashCode >>> 5 );
         hashCode += (int) s.charAt( i );
      }
      return hashCode;
   }

   /**
    * checks if num is a prime number
    * returns true if it is prime
    * returns false otherwise
    */
   static boolean isPrime( int num )
   {
      for( int i = 2; i < Math.sqrt( num ); i++ )
      {
         if( num % i == 0 ){
            return false;
         }
      }
      return true;
   }

   /**
    * finds the next prime
    * Bertrand's Postulate confirms that there exists a prime number between n and 2n
    */
   static int nextPrime( int length )
   {
      for( int i = length + 1; i < ( 2 * length ); i++ )
      {
         if( isPrime( i ) ){
            return i;
         }
      }
      return 0;
   }

   /* returns an index for the specified hash code within the given length using the MAD method */
   static int compression( int hashCode, int length )
   {
      Random randNum = new Random( hashCode );
      int prime = nextPrime( length );
      int a = randNum.nextInt( prime );
      int b = randNum.nextInt( prime );
      int index = ((( a * hashCode ) + b ) % prime ) % length;
      if( index < 0 ){
         index *= -1;
      }
      return index;
   }

   /** 
    * returns the entry if it exists
    * returns null if the hash table contains no mapping for the given key
    */
   public Entry get( String key )
   {
      int hash = hash( key );
      int index = compression( hash, hashTable.length );
      Entry temp = hashTable[ index ].contains( key );
      return temp;
   }

   /**
    * returns 0 if the key value pair is successfully put in the hash table
    * returns the old value otherwise
    */
   public double put( String key, double value )
   {
      int hash = hash( key );
      int index = compression( hash, hashTable.length );
      double oldValue = add( hash, key, value, index );
      return oldValue;
   }

   /** 
    * adds a new entry with a specified hash code, key, and value to the specified index 
    * resizes hash table if necessary
    * returns the old value in the table if it was a duplicate
    */
   public double add( int hash, String key, double value, int index )
   {
      if( hashTable[ index ].getHead() != null ){
         collisions++;
      }
      double oldValue = hashTable[ index ].add( key, hash, value );
      if( oldValue != -1 )
      {
         size--;
         collisions--;
      }
      if( ++size >= threshold ){
         resize( 2 * hashTable.length );
      }
      return oldValue;
   }

   /* resizes the hash table into an array with a larger capacity */
   public void resize( int newCapacity )
   {
      LinkedList[] oldTable = hashTable;
      int oldCapacity = oldTable.length;
      if( oldCapacity == Integer.MAX_VALUE )
      {
         threshold = Integer.MAX_VALUE;
         return;
      }
      LinkedList[] newTable = new LinkedList[ newCapacity ];
      initializeArray( newTable );
      size = 0;
      collisions = 0;
      transfer( newTable );
      hashTable = newTable;
      threshold = (int)( newCapacity * LOAD_FACTOR );    
   }

   /* transfer all entries from the current table to the new table, reindexing all entries */
   public void transfer( LinkedList[] newTable )
   {
      LinkedList[] table = hashTable;
      int newCapacity = newTable.length;
      
      /* traverses the linked list array */
      for( int i = 0; i < table.length; i++ )
      {
         LinkedList list = table[ i ];
         Entry current = list.getHead();
         /* traverses the current linked list */
         while( current != null )
         {
            int index = compression( current.getHash(), newCapacity );
            if( newTable[ index ].getHead() != null ){
               collisions++;
            }
            double oldValue = newTable[ index ].add( current.getName(), current.getHash(), current.getScore() );
            if( oldValue != -1 ){
               collisions--;
            }
            current = current.getNext();
            size++;
         }
      }
   }
}
