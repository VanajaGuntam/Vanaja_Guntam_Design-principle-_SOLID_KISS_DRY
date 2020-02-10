import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        double first = reader.nextDouble();
        double second = reader.nextDouble();
        System.out.print("Enter an operator (+, -, *, /,%):1.Addition\n 2.Subtraction\n 3.Multiplication\n 4.Division\n5.Modulus Division\n ");
        char operator = reader.next().charAt(0);
        double result;
        switch(operator)
        {
            case '+':
                result = first+second;
                System.out.println("Addition of"+first+"+"+second+"is:"+result);
                break;
            case '-':
                result = first-second;
               System.out.println("Subtraction of"+first+"+"+second+"is:"+result);
                break;
            case '*':
                result = first*second;
                System.out.println("Multiplication of"+first+"+"+second+"is:"+result);
                break;
            case '/':
                result = first/second;
                System.out.println("Division of"+first+"+"+second+"is:"+result);
                break;
            case '%':
                result = first%second;
                System.out.println("Modulus Division of"+first+"+"+second+"is:"+result);
                break;
            default:
                System.out.printf("Error! operator is not correct");
                return;
        }
  
    }
}