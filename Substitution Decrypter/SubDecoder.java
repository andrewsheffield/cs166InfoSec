
import java.util.Scanner;

/**
 *
 * @author Sheff
 */
public class SubDecoder {
    
    static LetterList ll;
    static Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Enter in data to be broken down for frequency:");
        
        String userData = scanner.nextLine();
        
        ll = new LetterList(userData);
        ll.print();
        
        tryKey();
    }
    
    public static  void tryKey() {
        System.out.println("Type a key with no spaces: ");
        String key = scanner.nextLine();
        
        ll.tryKey(key);
        ll.print();
        
        tryKey(); //rerun 
    }
    
}
