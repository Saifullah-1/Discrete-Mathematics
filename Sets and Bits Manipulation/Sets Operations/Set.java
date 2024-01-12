import java.util.ArrayList;
 
public class Set {
    private long value;
    private int cardinality;
    private static ArrayList<String> universe;
    
    public Set(){
    }
    
    public Set(ArrayList<String> universe){
        Set.universe = universe;
    }
    public void addStringToSet(String element) {
        this.value ^= (long)Math.pow(2, universe.lastIndexOf(element));
        cardinality++;
    }
    
    public Set union(Set b) { //Xor
        Set unionSet = new Set();
        unionSet.setValue(this.value ^ b.getValue());
        return unionSet;
    }
    
    public Set intersection(Set b) { //And
        Set intersectionSet = new Set();
        intersectionSet.setValue(this.value & b.getValue());
        return intersectionSet;
    }
    
    public Set complement() {
        Set complementSet = new Set();
        complementSet.setValue(~this.value);
        return complementSet;
    }
    
    public Set difference(Set b) {
        Set differenceSet = new Set();
        differenceSet.setValue(this.value & ~b.getValue());
        return differenceSet;
    }
    
    
    public void getElementsOfSet() {
        BitOperations bit = new BitOperations(value);
        System.out.print("{");
        int indx = (int)Math.sqrt(this.value) + 1;
        for (int i = 0; i < indx; i++) {
            String str;
            if (bit.getBit(this.value, i) == 1) {
                str = universe.get(i);
                System.out.print(str + " ");
            }
        }
        System.out.println("}");
    }
    
    public long getValue() {
        return this.value;
    }
    
    public void setValue(long value) {
        this.value = value;
    }
    public int getCardinality() {return this.cardinality;}
}
