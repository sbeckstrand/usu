use v6;
unit module MyCode;

# Function to generate an array of token from a provided string. 
sub getTokens ($data) is export {
    
    # Helper method that is used to remove the first character from a string. 
    sub popFirst($text) {
        my $updatedText = substr($text, 1..$text.chars);

        return $updatedText;
    }
    
    # Primary method which is used to create each token. 
    sub createTokens($input, @tokenList) {
        my $remainingInput = "";

        # Check if start of line is comment. If so, check if the input contains a new line. If so, 
        if substr($input, 0..0) ~~ m/ ";" / {
            if $input ~~ m/ "\n" / {
                $remainingInput = $/.postmatch;
            } 
            else {
                $remainingInput = "";
            }
        }

        # Check if next character is a space. If so, skip past it.
        elsif substr($input, 0..0) ~~ / " " / {
            $remainingInput = popFirst($input);
        }

        # check if next 'character' is a new line. If so, skip past it.
        elsif substr($input, 0..0) ~~ / "\n" / {
            $remainingInput = popFirst($input);
        }

        # check if next 'character' is a tab. If so, skip past it.
        elsif substr($input, 0..0) ~~ / "\t" / {
            $remainingInput = popFirst($input);
        }

        # Check if the next character is a left parentheses.
        elsif substr($input, 0..0) ~~ / "(" / {
            @tokenList.push("LPAREN: " ~ "(");
            $remainingInput = popFirst($input);
        }

        # Check if the next character is a right parentheses.
        elsif substr($input, 0..0) ~~ / ")" / {
            @tokenList.push("RPAREN: " ~ ")");
            $remainingInput = popFirst($input);
        }

        # Check if the next character is a multiplication operator.
        elsif substr($input, 0..0) ~~ / "*" / {
            @tokenList.push("MULTIPLICATION: " ~ "*");
            $remainingInput = popFirst($input);
        }

        # Check if the next character is an addition operator.
        elsif substr($input, 0..0) ~~ / "+" / {
            @tokenList.push("ADDITION: " ~ "+");
            $remainingInput = popFirst($input);
        }

        # Check if next series of characters are an integer value.
        elsif substr($input, 0..0) ~~ /(<:N>) / {
            if $input ~~ / (<:!N>) / {
                @tokenList.push("INTEGER: " ~ $/.prematch);
                $remainingInput = $/ ~ $/.postmatch;
            }
        } 

        # Check if next series of characters are wrapped in double quotes. 
        elsif substr($input, 0..0) ~~ / "\"" / {
            if substr($input, 1..$input.chars) ~~ m/ "\"" / {
                @tokenList.push("STRING: " ~ $/ ~ $/.prematch ~ $/);
                $remainingInput = $/.postmatch;
            }
        }

        # Check if next series of characters are 
        elsif substr($input, 0..0) ~~ /(<:L>) / {
            if $input ~~ / (<:!L+:!N>) / {
                @tokenList.push("IDENTIFIER: " ~ $/.prematch);
                $remainingInput = $/ ~ $/.postmatch;
            }
        } 

        # Check if the there are any unprocessed characters left. If so, recursively call the function again to process the rest of the input. 
        if $remainingInput.chars > 0 {
            return createTokens($remainingInput, @tokenList);
        } 
        else {
            # If no more input, return the token list. 
            return @tokenList;
        }  

        
    }

    my $tokens = ();
    my $tokenList = createTokens($data, $tokens);
    
    say $tokens;
    return $tokens;
}

# This function checks that the number of parentheses are equal and that the first is a left parentheses and the last is a right parentheses.
sub balance (@tokens) is export {
    my $lCount = 0;
    my $rCount = 0;
    my $first = "";
    my $last = "";
    
    if @tokens[0] ~~ "LPAREN: (" {
        $first = "l";
    }

    for @tokens -> $token {
        if $token ~~ "LPAREN: (" {
            $lCount = $lCount + 1;
            $last = "l";
        }
        elsif $token ~~ "RPAREN: )" {
            $rCount = $rCount +1;
            $last = "r"
        }
    }

    if $lCount ~~ $rCount {
        if $first ~~ "l" {
            if $last ~~ "r" {
                return True;
            }
            else {
                return False;
            }
        } 
        else {
            return False;
        }
    } 
    else {
        return False;
    }
}


# Function to format the string with one token per line and indented based on its current expression. 
sub format (@tokens) is export {

    my $currentIndent = 0;
    my $formattedString = "";

    # Helper function to add the appropriate number of tabs (indentation) to each line. 
    sub addTabs($tabCount, $formattedString) {

        my $updatedString = $formattedString;

        loop (my $i = 0; $i < $tabCount; $i++ ) {
            $updatedString = $updatedString ~ "\t"
        }

        return $updatedString;
    }

    # Loop through each token and indent or remove indentation if it is a parentheses. In any case, add the token value at the end of the line and start a new line. 
    for @tokens -> $token {
        
        if $token ~~ "LPAREN: (" {
            $formattedString = addTabs($currentIndent, $formattedString);
            $formattedString = $formattedString ~ $token.split(" ")[1] ~ "\n";
            $currentIndent = $currentIndent + 1;
        }
        elsif $token ~~ "RPAREN: )" {
            $currentIndent = $currentIndent - 1;
            $formattedString = addTabs($currentIndent, $formattedString);
            $formattedString = $formattedString ~ $token.split(" ")[1] ~ "\n";
        }
        else {
            $formattedString = addTabs($currentIndent, $formattedString);
            $formattedString = $formattedString ~ $token.split(" ")[1] ~ "\n";
        }

        
        
    }

    return $formattedString.trim;
}

say getTokens("(* 3 x)");


