public class BitOperations {
    private long number;
    private int position;
    private boolean value;
    
    public BitOperations(long number) {
        this.number = number;
    }
    public BitOperations(long number, int position) {
        this(number);
        this.position = position;
    }

    public BitOperations(long number, int position, boolean value) {
        this (number, position);
        this.value = value;
    }
    
    
    public void setNumber(long number) {
        this.number = number;
    }

    public long getNumber() {
        return this.number;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    
    public long getBit(long number, int position) {
        return (this.number >> this.position) & 1;
    }

    public long setBit(long number, int position) {
        this.number = this.number | (1 << this.position);
        return this.number;
    }
    
    public long clearBit(long number, int position) {
        this.number = this.number & ~(1 << this.position);
        return this.number;
    }

    public long updateBit(long number, int position, boolean value) {
        if (value)
            this.number = setBit(number, position);
        else 
            this.number = clearBit(number, position);
            
        return this.number;
    }
}

