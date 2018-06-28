/* class that creates an Entry */
public class Entry 
{
   private String name;
   private int hash;
   private int count;
   private double score;
   private Entry next;

   /* constructor that creates an Entry */
   public Entry( String n, int h, int c, double s, Entry e )
   {
      name = n;
      hash = h;
      count = c;
      score = s;
      next = e;
   }
   
   /* returns the name of the entry */
   public String getName()
   {
      return name;
   }

   /* returns the hash code of the entry */
   public int getHash()
   {
      return hash;
   }

   /* returns the number of times a single entry was added */
   public int getCount()
   {
      return count;
   }

   /* returns the total score of the entry */
   public double getScore()
   {
      return score;
   }

   /* returns the next entry int the linked list */
   public Entry getNext()
   {
      return next;
   }

   /* increases the count by one when a collision occurs */
   public void incrementCount()
   {
      count++;
   }

   /* adds the new score to score when a collision occurs */
   public void addScore( double newScore )
   {
      score += newScore;
   }

   /* sets the next entry */
   public void setNext( Entry n )
   {
      next = n;
   }
}
