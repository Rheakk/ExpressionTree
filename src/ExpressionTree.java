///////////////////////////////////////////////////////////////////////////
// Author : Rhea K
// Created : 24/March/2019
///////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.lang.*;

// https://www.tutorialspoint.com/java/lang/enum_name.htm
enum Operator {
    ADD("+"), SUB ("-"), MULT ("*"), DIV ("/");

    String name;
    Operator (String n) {
        name = n;
    }
    String showName () {
        return name;
    }
}

public class ExpressionTree implements ExpressionTreeInterface {

    LinkedList treeStack = new LinkedList();

    public ExpressionTree(String expression) {
        StringTokenizer tokExp = new StringTokenizer(expression);
        while (tokExp.hasMoreTokens()) {
            ExpressionNode n = new ExpressionNode(tokExp.nextToken());
            if (n.isOperator) {
                try {
                    n.right = (ExpressionNode) treeStack.pop();
                    n.left = (ExpressionNode) treeStack.pop();
                } catch (Exception e) {
                    System.err.println("Need two operands before an operator");
                    throw e;
                }

                treeStack.push(n);
            } else {
                treeStack.push(n);
            }
        }
    }

    public class ExpressionNode {

        public ExpressionNode left = null;
        public ExpressionNode right = null;
        public ExpressionNode parent;
        public ExpressionNode sibling;
        public boolean isOperator;
        // if isOperator is true, then
        // op is set and left and right are null;
        // else value is set.
        //
        Operator op;
        int value;

        public ExpressionNode(String token) {
            if (token.equals("+")) {
                isOperator = true;
                op = Operator.ADD;
            } else if (token.equals("-")) {
                isOperator = true;
                op = Operator.SUB;
            } else if (token.equals("*")) {
                isOperator = true;
                op = Operator.MULT;
            } else if (token.equals("/")) {
                isOperator = true;
                op = Operator.DIV;
            } else {
                try {
                    value = Integer.parseInt(token);
                } catch (NumberFormatException ne) {
                    System.err.println("character other than "
                            + "digit or operator in expression");
                    throw ne;
                }
            }
        }

        public int eval() {
            if (!isOperator) {
                return value;
            }
            int result;
            int leftVal = left.eval();
            int rightVal = right.eval();

            switch (op) {
                case ADD:
                    result = leftVal + rightVal;
                    break;
                case SUB:
                    result = leftVal - rightVal;
                    break;
                case MULT:
                    result = leftVal * rightVal;
                    break;
                case DIV:
                    result = leftVal / rightVal;
                    break;
                default:
                    result = 0;
                    System.err.println("Bad operator: " + op);
            }
            return result;
        }

        public void prefix(ArrayList<String> list) {
            if (!isOperator) {
                list.add(Integer.toString(value));
            } else {
                list.add(op.showName());
                left.prefix(list);
                right.prefix(list);
            }

        }

        public void postfix(ArrayList<String> list) {
            if (!isOperator) {
                list.add(Integer.toString(value));
            } else {
                left.postfix(list);
                right.postfix(list);
                list.add(op.showName());
            }

        }

        public void infix(boolean isRoot, ArrayList<String> list) {
            if (!isOperator) {
                list.add(Integer.toString(value));
            } else {
                if (!isRoot) {
                    list.add("(");
                }
                left.infix(false, list);
                list.add(op.showName());
                right.infix(false, list);
                if (!isRoot) {
                    list.add(")");
                }
            }
        }
    }

    public int eval() {
        if (treeStack.size() > 1) {
            System.err.println("error: more than one node found in treeStack when calling eval()");
            return 0;
        }
        ExpressionNode node = (ExpressionNode) treeStack.peekFirst();
        if (node == null) {
            System.err.println("error: Tree stack empty when calling eval()");
            return 0;
        }
        return node.eval();
    }

    public String prefix() {
        ExpressionNode n = (ExpressionNode) treeStack.peekFirst();
        ArrayList<String> list = new ArrayList<String> ();
        n.prefix(list);
        return listToString (list);
    }

    public String postfix() {
        ExpressionNode n = (ExpressionNode) treeStack.peekFirst();
        ArrayList<String> list = new ArrayList<String> ();
        n.postfix(list);
        return listToString (list);
    }
    public String infix() {
        ExpressionNode n = (ExpressionNode) treeStack.peekFirst();
        ArrayList<String> list = new ArrayList<String> ();
        n.infix(true, list);
        return listToString (list);
    }
    public String listToString (ArrayList<String> list){
      /*  String exp= list.get (0);
        for (int i = 1; i < list.size(); i++) {
            exp = exp.concat (" ");
            exp = exp.concat (list.get(i));
        }*/

        // https://stackoverflow.com/questions/599161/best-way-to-convert-an-arraylist-to-a-string
        return String.join(" ", list);
    }



}
