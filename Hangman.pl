@words=('computer', 'radio', 'calculator', 'teacher', 'bureau', 'police', 'geometry', 'president', 'subject', 'country', 'enviroment', 'classroom', 'animals', 'province', 'month', 'politics', 'puzzle', 'instrument', 'kitchen', 'language', 'vampire', 'ghost', 'solution', 'service', 'software', 'virus', 'security', 'phonenumber', 'expert', 'website', 'agreement', 'support', 'compatibility', 'advanced', 'search', 'triathlon', 'immediately', 'encyclopedia', 'endurance', 'distance', 'nature', 'history', 'organization', 'international', 'championship', 'government', 'popularity', 'thousand', 'feature', 'wetsuit', 'fitness', 'legendary', 'variation', 'equal', 'approximately', 'segment', 'priority', 'physics', 'branche', 'science', 'mathematics', 'lightning', 'dispersion', 'accelerator', 'detector', 'terminology', 'design', 'operation', 'foundation', 'application', 'prediction', 'reference', 'measurement', 'concept', 'perspective', 'overview', 'position', 'airplane', 'symmetry', 'dimension', 'toxic', 'algebra', 'illustration', 'classic', 'verification', 'citation', 'unusual', 'resource', 'analysis', 'license', 'comedy', 'screenplay', 'production', 'release', 'emphasis', 'director', 'trademark', 'vehicle', 'aircraft', 'experiment');

sub print_hangman{
    ($n) = @_;
    print "_______________\n";
    print "|             |\n";
    print "|             O\n";
    if($n == 2){
        print "|             |\n";
    }
    elsif($n == 3){
        print "|           \\ |\n";
    }
    elsif($n >= 4){
        print "|           \\ | /\n";
    }
    if($n < 5){
        print "|\n";
        print "|\n";
    }
    elsif($n == 5){
        print "|             |\n";
        print "|           /\n";
    }
    else{
        print "|             |\n";
        print "|           /   \\ \n";
    }
    print "|\n";
    print "|-----------------|\n";
}

sub printguess{
    ($n) = @_;
    if($n == 1){
        print $n;
        print "st wrong guess, continue playing.\n";
    }
    elsif($n == 2){
        print $n;
        print "nd wrong guess, continue playing.\n";
    }
    elsif($n == 3){
        print $n;
        print "rd wrong guess, continue playing.\n";
    }
    elsif($n < 6){
        print $n;
        print "th wrong guess, continue playing.\n";
    }
    else{
        print "last wrong guess made.\n";
    }
}

sub game{
    $word_index = int(rand(100));
    #random number from 0 to 99
    $word = @words[$word_index];
    $len = length($word);
    @matchedsofar;
    for($u = 0; $u < $len; $u++){
        $matchedsofar[$u] = '_';
    }
    $incorrect_guesses = 0;
    $countmatched = 0;
    @guessed_yet;
    while(1){
        print "Current status of the letters of the word guessed correctly ->\n";
        print "@matchedsofar\n";
        print "Enter a single letter :";
        $enteredword1 = <STDIN>;
        chomp $enteredword1;
        $enteredword = lc($enteredword1);
        if(length($enteredword) != 1){
            print "Wrong input, enter a SINGLE letter at a time ->\n";
            next;
        }
        print "\n";
        $found = index($word, $enteredword);
        if($found < 0){
            $letters_guessed_yet = @guessed_yet;
            $flag = 0;
            for($i = 0; $i < $letters_guessed_yet; $i++){
                if($guessed_yet[$i] eq $enteredword){
                    $flag = 1;
                }
            }
            if($flag == 0){
                $guessed_yet[$letters_guessed_yet] = $enteredword;
            }
            else{
                print "You have already made this incorrect guess!\n";
                next;
            }
            $incorrect_guesses++;
            printguess($incorrect_guesses);
            print_hangman($incorrect_guesses);
            if($incorrect_guesses == 6){
                print "Game Over\n";
                last;
            }
        }
        elsif($matchedsofar[$found] eq $enteredword){
            print "You have already guessed this letter, enter a new choice\n";
            next;
        }
        else{
            print "Correct choice\n";
            push(@guessed_yet, ($enteredword));
            for($i = 0; $i < $len; $i++){
                $ch = substr($word, $i, 1);
                if($ch eq $enteredword){
                    $matchedsofar[$i] = $enteredword;
                    $countmatched++;
                }
            }
        }
        print "Total guesses made uptil now ->\n";
        print "@guessed_yet\n"; 
        if($countmatched == $len){
            print "Won\n";
            last;
        }
    }
    splice(@guessed_yet);
    splice(@matchedsofar);
}

$tocontinue = 'Y';
print "Welcome to the hangman game! Follow the instructions to play !!\n";
do{
    game();
    print "Enter ['Y'] to play again or any other character to end the game\n";
    $tocontinue = <STDIN>;
    chomp($tocontinue);
}
while($tocontinue eq 'Y');
