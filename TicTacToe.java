import java.util.Scanner;
class TicTacToe{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Tic Tac Toe game !!");
        int ch = 0;
        do{
            System.out.println("Enter your choice:");
            System.out.println("Enter negative number: Human Player\nEnter non-negative number: Computer Player");
            int choice = sc.nextInt();
            Game obj = new Game();
            if(choice < 0)
                obj.PlayHuman(sc);
            else
                obj.PlayComputer(sc);
            System.out.println("Enter any number other than 0 to continue\nOr Enter 0 to exit the program");
            ch = sc.nextInt();
        }while(ch != 0);    
        sc.close();
    }
}