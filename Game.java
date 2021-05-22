import java.util.Scanner;

public class Game {
    public char[] a;
    public Game(){
        a = new char[9];
        for(int i = 0; i<9; i++){
            a[i] = '_';
        }
    }
    public void insert(int x, int turn){
        this.a[x] = ((turn & 1) == 1)?'X':'O';
    }
    public boolean isFree(int x){
        return (a[x] == '_');
    }
    public void printPositions(){
        System.out.print("\n");
        System.out.println(1 + " | " + 2 + " | " + 3);
        System.out.println(4 + " | " + 5 + " | " + 6);
        System.out.println(7 + " | " + 8 + " | " + 9);
        System.out.print("\n");
    }
    public void display(){
        System.out.print("\n");
        System.out.println(a[0] + " | " + a[1] + " | " + a[2]);
        System.out.println(a[3] + " | " + a[4] + " | " + a[5]);
        System.out.println(a[6] + " | " + a[7] + " | " + a[8]);
        System.out.print("\n");
    }
    public boolean gameStop(int turn, int position){
        //slightly faster version of gameStop function
        if(turn < 5) return false;
        
        //checking the diagonals for odd postions
        if((position & 1) == 1){
            if(a[4] != '_' && a[0] == a[4] && a[0] == a[8]){
                //left diagonal
                return true;
            }
            if(a[4] != '_' && a[2] == a[4] && a[2] == a[6]){
                //right diagonal
                return true;
            }
        }
        //checking the row and column for the current move
        int row = ((position-1) / 3);
        int column = ((position-1) % 3);
        char move = ((turn & 1) == 1)?'X':'O';
        
        //checking the row for the current move
        for(int i = 0; i<3; i++){
            if(a[3*row+i] != move)
                break;
            if(i == 2) return true;
        }

        //checking the column for the current move
        for(int i = 0; i<3; i++){
            if(a[3*i+column] != move)
                break;
            if(i == 2) return true;
        }
        return false;
    }
    public void PlayComputer(Scanner sc){

        System.out.print("You make the first move!!\nSelect a position on the board and make moves:\n");
        System.out.println("The below board despicts the board positions.");
        printPositions();//prints the possible positions and gives a layout to players
        System.out.println("Good Luck!!");
        int turn = 1;
        while(turn < 10){
            int position;
            if(turn%2 == 0){
                OptimalMove ob = new OptimalMove(a);
                position = ob.checkOptimalMove(turn);
                System.out.println("Your computer chose " + position + " position");
            }
            else{
                System.out.println("Enter a position to make a move");
                position = sc.nextInt();
            }
            if(position < 10 && position > 0){
                if(isFree(position-1)){
                    insert(position-1, turn);
                    display();
                }
                else{
                    System.out.println("Occupied position! Select another position!\n");
                    continue;
                }
            }
            else{
                System.out.println("Enter a Valid postion!\n");
                continue;
            }
            if(gameStop(turn, position)){
                break;
            }
            ++turn;
        }
        if(turn == 10){
            System.out.println("Tie !!");
        }
        else{
            if((turn & 1) == 0){
                System.out.println("Computer Won !!");
            }
            else{
                System.out.println("Player Won !!");
            }
        }
    }
    public void PlayHuman(Scanner sc){

        System.out.print("\nPlayer 1 - X     Player 2 - O\n\nSelect a position on the board and make moves:\n");
        System.out.println("The below board despicts the board positions.");
        printPositions();
        System.out.println("Good Luck!!");
        int turn = 1;
        while(turn < 10){
            System.out.println("Enter a position to make a move player " + (((turn & 1) == 1)?"1":"2"));
            int position = sc.nextInt();
            if(position < 10 && position > 0){
                if(isFree(position-1)){
                    insert(position-1, turn);
                    display();
                }
                else{
                    System.out.println("Occupied position! Select another position!\n");
                    continue;
                }
            }
            else{
                System.out.println("Enter a Valid postion!\n");
                continue;
            }
            if(gameStop(turn, position)){
                break;
            }
            ++turn;
        }
        if(turn == 10){
            System.out.println("Tie !!");
        }
        else{
            System.out.println("Player " + (((turn & 1) == 1)?"1":"2") + " Won !!");
        }
    }
}
