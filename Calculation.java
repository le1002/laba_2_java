public class Calculation {
    private String[] tokens;
    private int pos;

    public Calculation(String a){
    this.tokens = a.split(" ");
    this.pos = 0;

}

    public static void main(String[] args) {
        //String expression ="1 + 2 - 4 + 0.2";
        //String expression ="-2 + 3 - -2";
        //String expression ="1 + 2 * 4 - 6 / 3";
        //String expression ="( 1 + 2 ) * 4 - 6 / 3";
        String expression ="( 1 + ( 6 - 3 ) * 2 ) * 4 - 6 / 3";

        Calculation calculation =new Calculation(expression);
        System.out.println(calculation.calculate());

        //System.out.println("expression = " + (-2 + 3 - -2));
        //System.out.println("expression = " + (1 + 2 - 4 + 0.2));
        //System.out.println("expression = " + (1 + 2 * 4 - 6 / 3));
        //System.out.println("expression = " + (( 1 + 2 ) * 4 - 6 / 3));
        System.out.println("expression = " + (( 1 + ( 6 - 3 ) * 2 ) * 4 - 6 / 3));
    }
    private double calculate(){
        double first = multiply();
        while(pos<tokens.length){
            String operator = tokens[pos];
            if (!operator.equals("+")&& !operator.equals("-")){
                break;
            }
            else{
                pos++;
            }
            double second = multiply();
            if (operator.equals("+")){
                first+=second;
            }
            else{
                first-=second;
            }
        }
        return  first;
    }
    public double multiply(){
        double first = bracket();
        while(pos<tokens.length){
            String operator = tokens[pos];
            if (!operator.equals("*")&& !operator.equals("/")){
                break;
            }
            else{
                pos++;
            }
            double second = bracket();
            if (operator.equals("*")){
                first *=second;
            }
            else{
                first /=second;
            }
        }
        return  first;
    }
    public double bracket(){
            String next = tokens[pos];
            double result;
            if (next.equals("(")){
                pos++;
                result = calculate();
                String closeBracket;
                if (pos< tokens.length){
                    closeBracket = tokens[pos];
                }
                else{
                    throw new IllegalArgumentException("Error there is no closing parenthesis");
                }
                if (closeBracket.equals(")")){
                    pos++;
                    return result;
                }
                throw new IllegalArgumentException("')' not contained" );
            }
            pos++;
            return Double.parseDouble(next);

}
}
