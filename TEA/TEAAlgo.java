/**
 *
 * @author sheff
 */
public class TEAAlgo {
    int[] K = {0x1234abcd, 0x2345bcde, 0x3456cdef, 0x4567def1}; //made up 128bit key
    
    public TEAAlgo() {
    }
    
    //Implemented using the sudo code in table 3.6 of the book
    public int[] encrypt(int L, int R) {
        int delta = 0x9e3779b9;
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum = sum + delta;
            L = L + (((R << 4) + K[0]) ^ (R + sum) ^ ((R >> 5) + K[1]));
            R = R + (((L << 4) + K[2]) ^ (L + sum) ^ ((L >> 5) + K[3]));
        }
        int[] result = {L, R};
        return result;
    }

    byte[] encrypt(byte[] block) {
        int L = 0;
        int R = 0;
        for (int i = 0; i < block.length; i++) {
            if (i < 4) {
                //L = block[0] << 24 | block[1] << 16 | block[2] << 8 | block[3];
                L = L | block[i] << (24 - (i * 8));
            } else if (i >= 4) {
                //R = block[4] << 24 | block[5] << 16 | block[6] << 8 | block[7];
                R = R | block[i] << (24 - ((i-4) * 8));
            }
        }

        int delta = 0x9e3779b9;
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum = sum + delta;
            L = L + (((R << 4) + K[0]) ^ (R + sum) ^ ((R >> 5) + K[1]));
            R = R + (((L << 4) + K[2]) ^ (L + sum) ^ ((L >> 5) + K[3]));
        }
        return new byte[] {
            (byte) (L >> 24),
            (byte) (L >> 16),
            (byte) (L >> 8),
            (byte) (L),
            (byte) (R >> 24),
            (byte) (R >> 16),
            (byte) (R >> 8),
            (byte) (R)
        };
    }
}
