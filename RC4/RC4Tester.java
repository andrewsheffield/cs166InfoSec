/**
 *
 * @author sheff
 */
public class RC4Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        RC4Algo rc4 = new RC4Algo();
        rc4.initialize();
        System.out.println("\033[1mAfter Initialization:\033[0m");
        rc4.printSMatrix();
        rc4.generateKeyStream(100);
        System.out.println();
        System.out.println("\033[1mAfter 100 Bytes of keystream generated:\033[0m");
        rc4.printSMatrix();
        rc4.generateKeyStream(900);
        System.out.println();
        System.out.println("\033[1mAfter 1000 Bytes of keystream generated:\033[0m");
        rc4.printSMatrix();
    }
    
}
