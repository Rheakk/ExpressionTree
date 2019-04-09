import java.util.Scanner;

public class ExpressionTester {

    public static void main(String[] args) {
        boolean isTesting = true;
        if (isTesting) {

           /* System.out.println("Checking bad inputs for trees");
            checkTreeConstruct("+");
            checkTreeConstruct("4 +");
            checkTreeConstruct("10 3 + +");
            checkTreeConstruct("ab");
            checkTreeConstruct("4  2 + c9");
            checkTreeConstruct("( 3 4 +");
            checkTreeConstruct(" 4 3 3 +/");
            System.out.println();*/

            System.out.println("Testing eval()");
            checkEval("2", 2);
            checkEval("2 5 *", 10);
            checkEval("2 5 +", 7);
            checkEval("2 5 -", -3);
            checkEval("4 2 /", 2);
            checkEval("10 5 /", 2);
            checkEval("3 4 - 10 1 + *", -11);
            checkEval("10 3 4 6 8 - / * +", 4);
            checkEval("10 3 - 4 + 11 / 8 *", 8);
            System.out.println();

            System.out.println("Testing postfix()");
            checkPostfix("2");
            checkPostfix("2 5 *");
            checkPostfix("2 5 +");
            checkPostfix("2 5 -");
            checkPostfix("4 2 /");
            checkPostfix("10 5 /");
            checkPostfix("3 4 - 10 1 + *");
            checkPostfix("10 3 4 6 8 - / * +");
            checkPostfix("10 3 - 4 + 11 / 8 *");
            System.out.println();

            System.out.println("Testing prefix()");
            checkPreOrIn("2", "2", true);
            checkPreOrIn("2 5 *", "* 2 5", true);
            checkPreOrIn("10 3 4 6 8 - / * +", "+ 10 * 3 / 4 - 6 8", true);
            checkPreOrIn("10 3 - 4 + 11 / 8 *", "* / + - 10 3 4 11 8", true);
            checkPreOrIn("3 4 - 10 1 + *", "* - 3 4 + 10 1", true);
            System.out.println();

            System.out.println("Testing infix()");
            checkPreOrIn("2", "2", false);
            checkPreOrIn("2 5 *", "2 * 5", false);
            checkPreOrIn("10 3 4 6 8 - / * +", "10 + ( 3 * ( 4 / ( 6 - 8 ) ) )", false);
            checkPreOrIn("10 3 - 4 + 11 / 8 *", "( ( ( 10 - 3 ) + 4 ) / 11 ) * 8", false);
            checkPreOrIn("3 4 - 10 1 + *", "( 3 - 4 ) * ( 10 + 1 )", false);
            System.out.println();
        }

        else {
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Enter a postfix expression or 'end' to stop running");
                String postEx = in.nextLine();
                if (postEx.equals("end")) {
                    System.exit(0);
                }

                try {
                    ExpressionTree tree = new ExpressionTree(postEx);
                    System.out.println("eval : " + tree.eval());
                    System.out.println("prefix : " + tree.prefix());
                    System.out.println("postfix: " + tree.postfix());
                    System.out.println("infix  : " + tree.infix());

                } catch (Exception e) {
                    System.out.println("Try again...");
                }
            }
        }
    }

    public static void checkTreeConstruct(String postEx) {
        ExpressionTree tree = new ExpressionTree(postEx);
    }

    public static void checkEval(String postEx, Integer check) {
        ExpressionTree tree = new ExpressionTree(postEx);
        Integer out = tree.eval();
        if (check != out) {
            System.err.println("error: " + check + " != " + out + " stopping !");
            System.exit(1);
        } else {
            System.out.println(check + " == " + out);
        }
    }

    public static void checkPostfix(String postEx) {
        ExpressionTree tree = new ExpressionTree(postEx);
        String out = tree.postfix();
        if (!postEx.equals(out)) {
            System.err.println("error: " + postEx + " does not equal " + out + " stopping !");
            System.exit(1);
        } else {
            System.out.println(postEx + " equals " + out);
        }
    }

    public static void checkPreOrIn(String postEx, String check, boolean isPre) {
        ExpressionTree tree = new ExpressionTree(postEx);
        String out;
        if (isPre) {
            out = tree.prefix();
        } else {
            out = tree.infix();
        }
        if (!check.equals(out)) {
            System.err.println("error: " + check + " does not equal " + out + " stopping !");
            System.exit(1);
        } else {
            System.out.println(check + " equals " + out);
        }
    }
}

