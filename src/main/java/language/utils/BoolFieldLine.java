package language.utils;

/** Represents a fixed-width line of bits with bitwise line operations. */
public class BoolFieldLine {
    private static int SIZE = -1;
    /** Configures the bit width used by newly created language.obj.field lines. */
    public static void SET_PARAMS(int maxVerticesPerLine) {
        if (SIZE != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerLine <= 0) throw new IllegalArgumentException("There must exist at least one vertex.");
        SIZE = maxVerticesPerLine/32 + (maxVerticesPerLine %32 == 0 ? 0 : 1);
    }

    private final int[] line;

    /** Creates a new language.obj.field line. */
    public BoolFieldLine(){
        if (SIZE == -1) throw new IllegalStateException("Size must be > 0.");
        line = new int[SIZE];
    }

    /** Creates a new language.obj.field line. */
    public BoolFieldLine(BoolFieldLine o){
        this();
        System.arraycopy(o.line, 0, this.line, 0, SIZE);
    }

    /** @return a new zero-filled language.obj.field line. */
    public static BoolFieldLine zeroes(){
        if (SIZE == -1) throw new IllegalStateException("Size not set.");
        BoolFieldLine res = new BoolFieldLine();
        for (int i=0; i < SIZE; i++) res.line[i] = 0;
        return res;
    }

    /** @return a new one-filled language.obj.field line. */
    public static BoolFieldLine ones(){
        if (SIZE == -1) throw new IllegalStateException("Size not set.");
        BoolFieldLine res = new BoolFieldLine();
        for (int i=0; i < SIZE; i++) res.line[i] = 0xFFFFFFFF;
        return res;
    }

    /** @return a new randomly initialized language.obj.field line. */
    public static BoolFieldLine rand(){
        if (SIZE == -1) throw new IllegalStateException("Size not set.");
        BoolFieldLine res = new BoolFieldLine();
        for (int i=0; i < SIZE; i++) res.line[i] = (int)(2*(Math.random()-0.5) * Integer.MAX_VALUE);
        return res;
    }

    /** Sets the bit at the given position in the given language.obj.field line. */
    public static void setBit(BoolFieldLine line, int x, boolean bit){
        if (x >= SIZE * 32) throw new IllegalArgumentException("Bit index out of bounds.");
        int blockIndex = x / 32;
        int bitIndex = 1 << (31 - x%32);
        int bitValue = bit ? 0xFFFFFFFF : 0;

        line.line[blockIndex] = (bitIndex & bitValue) | (~bitIndex & line.line[blockIndex]);
    }

    /** @return the value of the bit at the given position in the given language.obj.field line. */
    public static boolean getBit(BoolFieldLine line, int x){
        if (x >= SIZE * 32) throw new IllegalArgumentException("Bit index out of bounds.");
        int blockIndex = x / 32;
        int bitIndex = 1 << (31 - x%32);
        return (line.line[blockIndex] & bitIndex) != 0;
    }

    /** @return the bitwise NOT of the given language.obj.field line. */
    public static BoolFieldLine not(BoolFieldLine a){
        BoolFieldLine res = new BoolFieldLine();
        for (int i = 0; i < SIZE; i++) res.line[i] = ~a.line[i];
        return res;
    }

    /** @return the bitwise AND of the given language.obj.field lines. */
    public static BoolFieldLine and(BoolFieldLine a, BoolFieldLine b){
        BoolFieldLine res = new BoolFieldLine();
        for (int i = 0; i < SIZE; i++) res.line[i] = a.line[i] & b.line[i];
        return res;
    }

    /** @return the bitwise OR of the given language.obj.field lines. */
    public static BoolFieldLine or(BoolFieldLine a, BoolFieldLine b){
        BoolFieldLine res = new BoolFieldLine();
        for (int i = 0; i < SIZE; i++) res.line[i] = a.line[i] | b.line[i];
        return res;
    }

    /** @return the bitwise XOR of the given language.obj.field lines. */
    public static BoolFieldLine xor(BoolFieldLine a, BoolFieldLine b){
        BoolFieldLine res = new BoolFieldLine();
        for (int i = 0; i < SIZE; i++) res.line[i] = a.line[i] ^ b.line[i];
        return res;
    }

    /** @return a left-shifted language.obj.field line. */
    public static BoolFieldLine lShift(BoolFieldLine a, int n){
        if(n < 1 || n > 31) throw new IllegalArgumentException("Shift must be between 1 and 31.");

        BoolFieldLine res = new BoolFieldLine();
        int ninv = 32-n;
        res.line[0] = a.line[0] << n;
        for (int i = 1; i < SIZE; i++){
            res.line[i-1] += (a.line[i] & (~((1 << ninv) - 1))) >>> ninv; //Set the bits that move the previous int
            res.line[i] = a.line[i] << n;
        }
        return res;
    }

    /** @return a right-shifted language.obj.field line. */
    public static BoolFieldLine rShift(BoolFieldLine a, int n){
        if(n < 1 || n > 31) throw new IllegalArgumentException("Shift must be between 1 and 31.");

        BoolFieldLine res = new BoolFieldLine();
        int ninv = 32-n;
        res.line[SIZE-1] = a.line[SIZE-1] >>> n;
        for (int i = SIZE-2; i >= 0; i--){
            res.line[i+1] += (a.line[i] & ((1 << n) - 1)) << ninv; //Set the bits that move the next int
            res.line[i] = a.line[i] >>> n;
        }
        return res;
    }

    /** @return the given line shifted by the specified offset. */
    public static BoolFieldLine shiftLine(BoolFieldLine line, int x) {
        if (x < 0) return lShift(line, -x);
        if (x > 0) return rShift(line, x);
        return line;
    }

    /** @return whether this BoolFieldLine is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolFieldLine other)) return false;
        for (int i = 0; i < SIZE; i++) if (this.line[i] != other.line[i]) return false;
        return true;
    }

    /** @return the string representation of this BoolFieldLine. */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++){
            sb.append(String.format("%32s", Integer.toBinaryString(line[i])).replace(' ', '0'));
        }
        return sb.toString();
    }

    /** @return a deep copy of this BoolFieldLine. */
    public BoolFieldLine copy() { return new BoolFieldLine(this); }
}
