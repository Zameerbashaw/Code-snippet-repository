import java.util.Scanner;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int random_number = random.nextInt(1, 100);
        int chance = 5;
        int guessnumber = 0;
        int Points = 0;
        System.out.println("welcome you have  "+ chance +"  chances to win this game");

        for(int i=0; i<chance; i++) {
            System.out.println("this is your" + (i + 1) + "chance, enter your guess");
            guessnumber = scanner.nextInt();

            if (guessnumber == random_number) {
                Points = (chance-i)* 100;
                System.out.println("you won the game your points is "+ Points + "out of 300" );
            }
            else if (guessnumber > random_number) {
                System.out.println("too big");
            } else if (guessnumber < random_number)
                    System.out.println("too small");
        }
        System.out.println("game over the number was" + random_number);
    }

}