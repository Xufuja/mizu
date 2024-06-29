package dev.xfj.token;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> tokenize(String string) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);

            if (Character.isLetter(character)) {
                buffer.append(character);
                i++;

                while (Character.isLetterOrDigit(string.charAt(i))) {
                    buffer.append(string.charAt(i));
                    i++;
                }
                i--;

                if (buffer.toString().equals("return")) {
                    tokens.add(new Token(TokenType._return, null));
                    buffer.setLength(0);
                } else {
                    System.err.println("Invalid token type!");
                    System.exit(1);
                }
            } else if (Character.isDigit(string.charAt(i))) {
                buffer.append(character);
                i++;

                while (Character.isDigit(string.charAt(i))) {
                    buffer.append(string.charAt(i));
                    i++;
                }
                i--;

                tokens.add(new Token(TokenType.int_lit, buffer.toString()));
                buffer.setLength(0);

            } else if (character == ';') {
                tokens.add(new Token(TokenType.semi, null));
            } else if (Character.isSpaceChar(character)) {
                continue;
            } else {
                System.err.println("Unhandled token!");
                System.exit(1);
            }
        }
        return tokens;
    }
}
