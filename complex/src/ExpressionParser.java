import java.lang.reflect.Array;
import java.util.Arrays;

public class ExpressionParser {
    private String[] args;
    Operation operation;
    NumarComplex[] complexNumbers = new NumarComplex[0];
    String semn = null;

    public ExpressionParser(String[] args) {
        this.args = args;
    }

    public Operation getOperation() {
        return operation;
    }

    public NumarComplex[] getComplexNumbers() {
        return complexNumbers;
    }

    public void addToArray(NumarComplex c){
        complexNumbers = Arrays.copyOf(complexNumbers, complexNumbers.length + 1);
        complexNumbers[complexNumbers.length - 1] = c;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private boolean valideaza(String[] args)
    {
        if(args.length == 5)
        {
            if(!isNumeric(args[0]))
                return false;
            if(!args[1].equals("+") && !args[1].equals("-"))
                return false;
            if(!isNumeric(args[2]))
                return false;
            if(!args[3].equals("*"))
                return false;
            if(!args[4].equals("i"))
                return false;
            return true;
        }
        if(args.length == 3)
        {
            if(!isNumeric(args[0]))
                return false;
            if(!args[1].equals("+") && !args[1].equals("-") && !args[1].equals("*"))
                return false;
            if(!args[2].equals("i"))
                return false;
            return true;
        }
        if(args.length == 1)
        {
            if(!args[0].equals("i"))
                return false;
            return true;
        }
        return false;
    }

    public void parse()
    {
        String aux = "";
        for(int i = 0; i < args.length; i++)
        {
            if(!args[i].equals("i")){
                aux += args[i] + " ";
            }
            else{
                aux += args[i];
                String[] splited = aux.split(" ");
                if(!valideaza(splited))
                {
                    System.out.println("Expresie invalida");
                    return;
                }
                if(splited.length == 5){
                    NumarComplex c;

                    if(splited[1].equals("-"))
                    {
                        c = new NumarComplex(Double.parseDouble(splited[0]), Double.parseDouble(splited[2]) * -1);
                    }
                    else
                    {
                        c = new NumarComplex(Double.parseDouble(splited[0]), Double.parseDouble(splited[2]));
                    }
                    addToArray(c);
                }
                if(splited.length == 3){
                    NumarComplex c;
                    if(!splited[1].equals("*")){
                        c = new NumarComplex(Double.parseDouble(splited[0]), 1.0);
                    }
                    else{
                        c = new NumarComplex(0.0, Double.parseDouble(splited[0]));
                    }
                    addToArray(c);
                }
                if(splited.length == 1 && splited[0].equals("i"))
                {
                    NumarComplex c = new NumarComplex(0.0, 1.0);
                    addToArray(c);
                }

                aux = "";
                if(semn == null)
                {
                    if(i + 1 < args.length)
                        semn = args[i+1];
                }
                else{
                    if(i + 1 < args.length)
                        if(!semn.equals(args[i+1]))
                            System.out.println("Operatie invalida!");
                }
                i+=1;

            }
        }
        if(semn.equals("+")){
            operation = Operation.ADDITION;
        }else if(semn.equals("-")){
            operation = Operation.SUBTRACTION;
        }else if(semn.equals("/")){
            operation = Operation.DIVISION;
        }else if(semn.equals("*")){
            operation = Operation.MULTIPLICATION;
        }else{
            System.out.println("Operatie invalida!");
            return;
        }
        for(NumarComplex c: complexNumbers)
        {
            System.out.println(c.getRe());
            System.out.println(c.getIm());
        }
    }
}
