
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
public class RC4Algo {
    
    int[] key = {0x1A, 0x2B, 0x3C, 0x4D, 0x5E, 0x6F, 0x77};
    ArrayList<Integer> S = new ArrayList<>(); // Lookup Table
    ArrayList<Integer> K = new ArrayList<>(); // Repeated key bytes till full
    
    /**
     * Implemented from the sudo code in Table 3.1
     */
    public void initialize() {
        for (int i = 0; i < 256; i++) {
            S.add(i, i);
            K.add(i, key[i % key.length]);
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S.get(i) + K.get(i)) % 256;
            swapS(i, j);
        }
        
    }
    
    /**
     * Implemented from the sudo code in Table 3.2
     * @param count 
     */
    public void generateKeyStream(int count) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < count; k++) {
            i = (i + 1) % 256;
            j = (j + S.get(i)) % 256;
            swapS(i, j);
            int t = (S.get(i) + S.get(j)) % 256;
            int keyStreamByte = S.get(t);
        }
    }
    
    public void printSMatrix() {
        for (int i = 0; i < 16 ; i++) {
            System.out.print("\t" + i);
        }
        
        for (int i = 0; i < S.size(); i++) {
            if (i % 16 == 0) {
                System.out.print("\n");
                System.out.print(i/16);
            }
            System.out.print("\t0x" + Integer.toHexString(S.get(i)).toUpperCase());
        }
        System.out.print("\n");
    }
    
    
    
    private void swapS(int i, int j) {
        int temp = S.get(i);
        S.set(i, S.get(j));
        S.set(j, temp);
    }
    
}
