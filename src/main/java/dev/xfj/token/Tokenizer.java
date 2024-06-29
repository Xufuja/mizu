package dev.xfj.token;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizer {
    public List<Token> tokenize(String string) {
        List<Token> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(string, " ;", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (token.equals("return")) {
                tokens.add(new Token(TokenType._return, null));
            } else if (token.chars().allMatch(Character::isDigit)) {
                tokens.add(new Token(TokenType.int_lit, token));
            } else if (token.equals(";")) {
                tokens.add(new Token(TokenType.semi, null));
            } else if (token.equals(" ")) {
                continue;
            } else {
                System.err.println("Unhandled token!");
                System.exit(1);
            }
        }

        return tokens;
    }
}
