import java.util.Scanner;
public class ExpressionTester {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("Enter a postfix expression or 'end' to stop running");
            String postEx = in.nextLine();
            if (postEx.equals("end")){
                System.exit(0);
            }

            try {
                ExpressionTree tree = new ExpressionTree(postEx);
                System.out.println ("answer : " + tree.eval());
                System.out.println ("prefix : " + tree.prefix());
                System.out.println ("postfix: " + tree.postfix());
                System.out.println ("infix  : " + tree.infix());
            }
            catch (Exception e) {
                System.out.println ("Try again...");
            }
        }
    }
}

