/**
 * Created by Jeremy on 2/3/16.
 */
public class InfixExpressionsEval {

    /**
     * Two-stack algorithm (Dijkstra Algorithm)
     *
     * Value: push onto the value stack
     * Operator: push onto the operator stack
     * Left parenthesis: ignore
     * Right parenthesis:
     *   pop operator and two values;
     *   push the result of applying that operator to those values onto the operand stack
     *
     * **/

    public static void main(String[] args) {

        Stack<String> ops  = new Stack<>();
        Stack<Double> vals = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("("))
                continue;
            else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
                ops.push(s);
            else if (s.equals(")")) {
                double val = 0;
                double val1 = vals.pop();
                double val2 = vals.pop();
                String op = ops.pop();
                if (op.equals("+"))
                    val = val1 + val2;
                if (op.equals("-"))
                    val = val1 - val2;
                if (op.equals("*"))
                    val = val1 * val2;
                if (op.equals("/"))
                    val = val1 / val2;
                vals.push(val);
            }
            else
                vals.push(Double.parseDouble(s));
        }

        System.out.println(vals.pop());
    }

}
