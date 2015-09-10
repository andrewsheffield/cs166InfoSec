/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
public class A51Algo {
    
    private int X, Y, Z;
    private long keyStream;
    
    public A51Algo() {
        X = Integer.parseInt("1010101010101010101", 2); // holds 19 bits
        Y = Integer.parseInt("1100110011001100110011", 2); // holds 22
        Z = Integer.parseInt("11100001111000011110000", 2); // holds 23
        keyStream = 0;
    }
    
    public void runAlgo(int runNumber) {
        for (int i = 0; i < runNumber; i++) {
            
            //Gets bits at step locations
            int x8 = getBit(X, 10); // gets the 11th bit (0-10) from the right 8 from the left
            int y10 = getBit(Y, 11);
            int z10 = getBit(Z, 12);
            //Get the maj of the three bits
            int m = maj(x8, y10, z10);
            //Steps the bit if the maj matches the bit
            if (x8 == m) xSteps();
            if (y10 == m) ySteps();
            if (z10 == m) zSteps();
            
            //Gets the first bit from the right of each
            int x18 = getBit(X, 0);
            int y21 = getBit(Y, 0);
            int z22 = getBit(Z, 0);
            //creates a single keystream bit by XOR each
            int s = x18 ^ y21 ^ z22;
            addToKeyStream(s);
        }
    }
    
    public void printKeyStream() {
        System.out.println("Keystream:\t" + Long.toBinaryString(keyStream));
    }
    
    public void printXYZ() {
        System.out.println("X:\t\t" + Integer.toBinaryString(X));
        System.out.println("Y:\t\t" + Integer.toBinaryString(Y));
        System.out.println("Z:\t\t" + Integer.toBinaryString(Z));
    }
    
    private int getBit(int N, int place) {
        return (N >> place) & 1;
    }
    
    private int maj(int x, int y, int z) {
        return (x + y + z < 2) ? 0 : 1;
    }
    
    private void xSteps() {
        int x13 = getBit(X, 5);
        int x16 = getBit(X, 2);
        int x17 = getBit(X, 1);
        int x18 = getBit(X, 0);
        int t = (x13 ^ x16 ^ x17 ^ x18) << 18;
        X = (X >> 1) | t; 
    }
    
    private void ySteps(){
        int y20 = getBit(Y, 1);
        int y21 = getBit(Y, 0);
        int t = (y20 ^ y21) << 21;
        Y = (Y >> 1) | t;
    }
    
    private void zSteps() {
        int z7 = getBit(Z, 15);
        int z20 = getBit(Z, 2);
        int z21 = getBit(Z, 1);
        int z22 = getBit(Z, 0);
        int t = (z7 ^ z20 ^ z21 ^ z22) << 22;
        Z = (Z >> 1) | t;
    }

    private void addToKeyStream(int s) {
        keyStream = (keyStream << 1) | s;
    }
    
    
}
