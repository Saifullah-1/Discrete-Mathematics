import java.util.ArrayList;
import java.util.Scanner;

interface InferenceRule{
    boolean matches(Expression exp1, Expression exp2);
    Expression apply(Expression exp1, Expression exp2);
}

interface InferenceEngine{
    void addRule(InferenceRule rule);
    void addExpression(Expression exp);
    Expression applyRules();
}


// >>>>>>> Rules <<<<<<<<
class ModusPonens implements InferenceRule {
   
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        boolean match = false;
        String str1 = exp1.getRepresentation(), str2 = exp2.getRepresentation();
        if(str1.length() == 5 && str2.length() == 1){ // exp1(P > Q) and exp2(P)
            if(str1.charAt(2) == '>')
                if (str1.charAt(0) == str2.charAt(0))  match = true; 
        } else if(str2.length() == 5 && str1.length() == 1){ // exp1(P) and exp2(P > Q)
            if(str2.charAt(2) == '>')
                if (str2.charAt(0) == str1.charAt(0))  match = true; 
        }
        return match;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        Expression exp = new Expression();
        String str;
        if(exp1.getRepresentation().length() == 5)
            str = exp1.getRepresentation().substring(4); 
        else
            str = exp2.getRepresentation().substring(4);
        exp.setRepresentation(str);
        return exp;
    }
    
}

class ModusTollens implements InferenceRule {

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        boolean match = false;
        String str1 = exp1.getRepresentation(), str2 = exp2.getRepresentation();
        if(str1.length() == 5 && str2.length() == 2){// exp1(P > Q) and exp2(~Q)
            if(str1.charAt(2) == '>' && str2.charAt(0) == '~')
                if (str1.charAt(4) == str2.charAt(1))  match = true;
        } else if(str2.length() == 5 && str1.length() == 2){// exp1(~Q) and exp2(P > Q)
            if(str2.charAt(2) == '>' && str1.charAt(0) == '~')
                if (str2.charAt(4) == str1.charAt(1))  match = true;
        }
        return match;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        Expression exp = new Expression();
        String str;
        if(exp1.getRepresentation().length() == 5)
            str = exp1.getRepresentation().substring(0, 1);
        else
            str = exp2.getRepresentation().substring(0, 1);
        exp.setRepresentation("~" + str);
        return exp;
    }
    
}

class HypotheticalSyllogism implements InferenceRule {

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        boolean match = false;
        String str1 = exp1.getRepresentation(), str2 = exp2.getRepresentation();
        if(str1.length() == 5 && str2.length() == 5){
            if(str1.charAt(2) == '>' && str1.charAt(2) == str2.charAt(2)){
                if (str1.charAt(4) == str2.charAt(0) && str1.charAt(0) != str2.charAt(4))  match = true; // exp1(P > Q) and exp2(Q > R)
                else if (str2.charAt(4) == str1.charAt(0) && str2.charAt(0) != str1.charAt(4))  match = true; // exp1(Q > R) and exp2(P > Q)
            }
        }  
        return match;
        
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        Expression exp = new Expression();
        String str1;
        String str2;
        if(exp1.getRepresentation().charAt(4) == exp2.getRepresentation().charAt(0)){
            str1 = exp1.getRepresentation().substring(0, 1);
            str2 = exp2.getRepresentation().substring(4);
        }else {
            str2 = exp1.getRepresentation().substring(4);
            str1 = exp2.getRepresentation().substring(0, 1);
        }
        exp.setRepresentation(str1 + " > " + str2);
        return exp;
    }
    
}

class DisjunctiveSyllogism implements InferenceRule {

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        boolean match = false;
        String str1 = exp1.getRepresentation(), str2 = exp2.getRepresentation();
        if(str1.length() == 5 && str2.length() == 2){// exp1(P v Q) and exp2(~P)
            if(str1.charAt(2) == 'v' && str2.charAt(0) == '~'){
                if (str1.charAt(0) == str2.charAt(1))  match = true; // exp1(P v Q) and exp2(~P)
                else if(str1.charAt(4) == str2.charAt(1)) match = true; // exp1(P v Q) and exp2(~Q)
            }
        } else if(str2.length() == 5 && str1.length() == 2){// exp1(~P) and exp2(P v Q)
            if(str2.charAt(2) == 'v' && str1.charAt(0) == '~'){
                if (str2.charAt(0) == str1.charAt(1))  match = true; // exp1(~P) and exp2(P v Q)
                else if(str2.charAt(4) == str1.charAt(1)) match = true; // exp1(~Q) and exp2(P v Q)
            }
        }
        return match;
        
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        Expression exp = new Expression();
        String str;
        if(exp1.getRepresentation().length() == 5){
            if (exp2.getRepresentation().charAt(1) == exp1.getRepresentation().charAt(0)) 
                 str = exp1.getRepresentation().substring(4);
            else 
                str = exp1.getRepresentation().substring(0, 1);
        } else {
            if (exp1.getRepresentation().charAt(1) == exp2.getRepresentation().charAt(0)) 
                 str = exp2.getRepresentation().substring(4);
            else 
                str = exp2.getRepresentation().substring(0, 1);
        }
        exp.setRepresentation(str);
        return exp;
    }
    
}

class Resolution implements InferenceRule {

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        boolean match = false;
        String str1 = exp1.getRepresentation(), str2 = exp2.getRepresentation();
        if(str1.length() == 5 && str2.length() == 6){
            if(str1.charAt(2) == 'v' && str1.charAt(2) == str2.charAt(3) && str2.charAt(0) == '~'){
                if (str1.charAt(0) == str2.charAt(1))  match = true; // exp1(P v Q) and exp2(~P v R)
                else if(str1.charAt(4) == str2.charAt(1)) match = true; // exp1(Q v P) and exp2(~P v R)
            } else if(str1.charAt(2) == 'v' && str1.charAt(2) == str2.charAt(2) && str2.charAt(4) == '~'){
                if (str1.charAt(0) == str2.charAt(5))  match = true; // exp1(P v Q) and exp2(R v ~P)
                else if(str1.charAt(4) == str2.charAt(5)) match = true; // exp1(Q v P) and exp2(R v ~P)
            }
        } else if(str2.length() == 5 && str1.length() == 6){
            if(str2.charAt(2) == 'v' && str2.charAt(2) == str1.charAt(3) && str1.charAt(0) == '~'){
                if (str2.charAt(0) == str1.charAt(1))  match = true; // exp1(P v Q) and exp2(~P v R)
                else if(str2.charAt(4) == str1.charAt(1)) match = true; // exp1(Q v P) and exp2(~P v R)
            } else if(str2.charAt(2) == 'v' && str2.charAt(2) == str1.charAt(2) && str1.charAt(4) == '~'){
                if (str2.charAt(0) == str1.charAt(5))  match = true; // exp1(P v Q) and exp2(R v ~P)
                else if(str2.charAt(4) == str1.charAt(5)) match = true; // exp1(Q v P) and exp2(R v ~P)
            }
        }
        return match;
        
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        Expression exp = new Expression();
        String str1;
        String str2;
        if (exp1.getRepresentation().length() == 5) {
            if (exp1.getRepresentation().charAt(0) == exp2.getRepresentation().charAt(1)){
                str1 = exp1.getRepresentation().substring(4);
                str2 = exp2.getRepresentation().substring(5);
            } else if(exp1.getRepresentation().charAt(4) == exp2.getRepresentation().charAt(1)){
                str1 = exp1.getRepresentation().substring(0, 1);
                str2 = exp2.getRepresentation().substring(5);
            } else if(exp1.getRepresentation().charAt(0) == exp2.getRepresentation().charAt(5)){
                str1 = exp1.getRepresentation().substring(4);
                str2 = exp2.getRepresentation().substring(0, 1);
            } else { //if(exp1.getRepresentation().charAt(4) == exp2.getRepresentation().charAt(5))
                str1 = exp1.getRepresentation().substring(0, 1);
                str2 = exp2.getRepresentation().substring(0, 1);
            }
        } else{
            if (exp2.getRepresentation().charAt(0) == exp1.getRepresentation().charAt(1)){
                str1 = exp2.getRepresentation().substring(4);
                str2 = exp1.getRepresentation().substring(5);
            } else if(exp2.getRepresentation().charAt(4) == exp1.getRepresentation().charAt(1)){
                str1 = exp2.getRepresentation().substring(0, 1);
                str2 = exp1.getRepresentation().substring(5);
            } else if(exp2.getRepresentation().charAt(0) == exp1.getRepresentation().charAt(5)){
                str1 = exp2.getRepresentation().substring(4);
                str2 = exp1.getRepresentation().substring(0, 1);
            } else { //if(exp2.getRepresentation().charAt(4) == exp1.getRepresentation().charAt(5))
                str1 = exp2.getRepresentation().substring(0, 1);
                str2 = exp1.getRepresentation().substring(0, 1);
            }
        }
        exp.setRepresentation(str1 + " v " + str2);
        return exp;
    }
}

class Engine implements InferenceEngine {
    private ArrayList<Expression> expressions;
    private ArrayList<InferenceRule> rules;
    private String matched;
    public Engine() {
        this.expressions = new ArrayList<>();
        this.rules = new ArrayList<>();
    }

    @Override
    public void addRule(InferenceRule rule) {
        this.rules.add(rule);
    }

    @Override
    public void addExpression(Expression exp) {
        this.expressions.add(exp);
    }

    @Override
    public Expression applyRules() {
        Expression exp = new Expression();
        Expression exp1 = expressions.get(0);
        Expression exp2 = expressions.get(1);
        for (int i = 0; i < rules.size(); i++) {
            InferenceRule rule = rules.get(i);
            if (rule.matches(exp1, exp2)) {
                exp = rule.apply(exp1, exp2);
                exp.setRepresentation(exp.getRepresentation());
                this.matched = rule.getClass().getSimpleName();
                return exp;
            }
        }
        return null;
    }
    
    public String matchedRule() {
        return matched;
    }
}

public class Inference {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner (System.in);
        Engine engine = new Engine();
        engine.addRule(new ModusPonens());
        engine.addRule(new ModusTollens());
        engine.addRule(new HypotheticalSyllogism());
        engine.addRule(new DisjunctiveSyllogism());
        engine.addRule(new Resolution());

        Expression exp1 = new Expression();
        Expression exp2 = new Expression();
        System.out.println("Enter expressions : ");
        String proposition = sc.nextLine();
        String[] input = proposition.split("\""); // ["expressions are ", "exp1", " and ", "exp2"]
    //        for (int i = 0; i < input.length; i++) {
    //            System.out.println(input[i] + "|");
    //        }
        exp1.setRepresentation(input[1]);
        exp2.setRepresentation(input[3]);
        engine.addExpression(exp1);
        engine.addExpression(exp2);
    //      System.out.println(exp1.getRepresentation() + " " + exp2.getRepresentation());
        Expression exp = engine.applyRules();
        if (exp == null)
            System.out.println("The input expression cannot be inferred");
        else 
            System.out.println(exp.getRepresentation() + " (" + engine.matchedRule() + ")");    
    }
}