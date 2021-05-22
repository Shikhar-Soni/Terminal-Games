public class OptimalMove {
    public char[] a;
    public int optimalMove;
    public OptimalMove(char[] arr){
        optimalMove = 0;
        a = new char[9];
        for(int i = 0; i<9; i++){
            a[i] = arr[i];
        }
    }
    public boolean gameStop(int turn){
        //slower gameStop function needed here
        //as we need to check all possiblities here
        if(turn <= 5) return false;

        if(a[4] != '_' && a[0] == a[4] && a[0] == a[8]){
            //left diagonal
            return true;
        }
        if(a[4] != '_' && a[2] == a[4] && a[2] == a[6]){
            //right diagonal
            return true;
        }
        char move = ((turn & 1) == 0)?'X':'O';
        for(int row = 0; row < 3; row++){
            //checking all rows
            for(int i = 0; i<3; i++){
                if(a[3*row+i] != move)
                    break;
                if(i == 2) return true;
            }
        }
        for(int column = 0; column < 3; column++){
            //checking all columns
            for(int i = 0; i<3; i++){
                if(a[3*i+column] != move)
                    break;
                if(i == 2) return true;
            }
        }   
        return false;
    }

    public int bestMove(char[] arr, int turn){        
        if(gameStop(turn)){
            //game stops when it's won by one player
            return ((turn & 1) == 0)?-100000+turn:100000-turn;
        }
        if(turn == 10){
            //all 9 turns have been made with no winners
            return 0;
        }
        int ans = 0, min = 100000, max = -100000;
        for(int i = 0; i < 9; i++){
            int score;
            if(arr[i] == '_'){

                arr[i] = ((turn & 1) == 1)?'X':'O';
                score = bestMove(arr, turn+1);
                arr[i] = '_';
                if((turn & 1) == 1){
                    //min for odd turns, i.e., human player
                    if(score < min){
                        ans = i;
                        min = score;
                    }
                }
                else{
                    //max for even player, i.e., CPU
                    if(score > max){
                        ans = i;
                        max = score;
                    }
                }
            }
        }
        this.optimalMove = ans + 1;
        return ((turn&1) == 1)?min:max;
    }
    public int checkOptimalMove(int turn){
        int OptimalPlayResult = bestMove(a, turn);
        //OptimalPlayResult stores the result of the
        //game with optimal play by both sides
        if(OptimalPlayResult < 0){
            //this condition should never be true
            //helps in debugging
            System.out.println("Computer can lose");
        }
        return this.optimalMove;
    }
}
