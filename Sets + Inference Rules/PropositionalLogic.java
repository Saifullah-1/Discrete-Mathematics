import java.util.Scanner;
import java.util.Stack;

class Expression implements IExpression{
    
    private String exp;
    
    public String getRepresentation(){
        return exp;
    }
    public void setRepresentation(String representation){
        this.exp = representation;
    }
    
}

class LogicalExpressionSolver implements ILogicalExpressionSolver{
    public boolean evaluateExpression (Expression expression){
        
        if (!checker (expression)){
            System.out.println("Wrong Expression");
            System.exit(0);
        }
        postfix(expression);
        boolean [] values = getValues();
        Stack <Boolean> operands = new Stack <Boolean> ();
        
        char []exp=expression.getRepresentation().toCharArray();
        for (int i=0;i<exp.length;i++){
            if (exp[i]>='A' && exp[i]<='Z') operands.add(values[exp[i]-'A']);
            else{
                if (exp[i]=='~'){
                    boolean temp = operands.pop();
                    operands.add(!temp);
                }
                else{
                    boolean b= operands.pop();
                    boolean a= operands.pop();
                    if (exp[i]=='^') operands.add(a&&b);
                    else if (exp[i]=='v') operands.add(a||b);
                    else if (exp[i]=='>') operands.add((!a)||b);
                }
            }
        }
    return (operands.pop());    
    }
    
    public static void postfix (Expression e){
        
        String s = e.getRepresentation();
        char[] exp = s.toCharArray();
        String posted="";
        Stack <Character> stk = new Stack <Character> ();
        for (int i=0;i<exp.length;i++){
            if (exp[i]>='A' && exp[i]<='Z') posted+=exp[i];
            else if (exp[i]==' ') continue;
            else{
                if (stk.empty()) stk.add(exp[i]);
                else {
                    char b=stk.peek();
                    if (precedence(exp[i],b)) stk.add(exp[i]);
                    else{
                        while (!stk.empty()){
                            char c = stk.pop();
                            if (c!='(') posted+=c;
                        }
                    }
                }
            }
        }
        while (!stk.empty()){
            char c = stk.pop();
            posted+=c;
        }
        e.setRepresentation(posted);
    }
    
    
    public static boolean precedence (char a, char b){
        if (a == '~' || a=='(' || b=='(') return true;
        else if (a== '^' && b!='~') return true;
        else if (a== 'v' && b!='~' && b!='^') return true;
        else if (b==')') return false;
        else return false;
    }
    
    public static boolean checker (Expression e){
        String s = e.getRepresentation();
        char [] ch= s.toCharArray();
        for (int i=0; i<ch.length;i++){
            if (ch[i]==' ' || ch[i]=='(' || ch[i]==')') continue;
            else if (!(ch[i]>='A' && ch[i]<='Z')){
                int j=i+1;
                while ((ch[j]==' '||ch[j]=='('||ch[j]==')') && j<ch.length){
                    j++;
                }
                char a = ch[i];
                char b = ch[j];
                if (!(a>='A' && a<='Z') && !(b>='A' && b<='Z') && a!='~' && b!='~'){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean[] getValues(){
        
        Scanner in = new Scanner (System.in);
        System.out.println("Enter the Values of All Propositional Variables : ");
        String read = in.nextLine();
        char [] ch = read.toCharArray();
        boolean [] values = new boolean[26];
        for (int i=0;i<ch.length;i++){
            if (ch[i]>='A' && ch[i]<='Z'){
                char letter=ch[i];
                int j=i+1;
                while (j<ch.length-1 && (ch[j]!='t' && ch[j]!='f')) j++;
                if (ch[j]=='t') {
                    values[letter-'A']=true;
                }
            }
        }
        return values;
    }  
    
}

interface IExpression {
    String getRepresentation();
    void setRepresentation(String representation);
}

interface ILogicalExpressionSolver{
    boolean evaluateExpression (Expression expression);
}

public class PropositionalLogic {
    public static void main(String[] args) {
    
        Expression exp = new Expression();
        Scanner in = new Scanner (System.in);
        System.out.println("Enter the Proposition : ");
        String proposition = in.nextLine();
        exp.setRepresentation(proposition.substring(15, proposition.length()-1));
        LogicalExpressionSolver result = new LogicalExpressionSolver();
        System.out.println(result.evaluateExpression(exp));
        
    } 
}


// Asumption : All variables are capital letters
// Asumption : Inputs are only spaces,Letters,(,). No ' or "
// Asumption : All variables will be given values, the user must give values to all the variables
// Asumption : The input of the values is as it is in the pdf
// Asumption : Max num of variables = 26