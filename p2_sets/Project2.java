/* class that contains the main method that runs the Set program */
public class Project2
{
   public static void main( String[] args )
   {
      UI ui = new UI( new Set(), new Set() ); //creates a UI object
      ui.start(); //starts the Set program
   }
}
