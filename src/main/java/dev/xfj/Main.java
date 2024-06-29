package dev.xfj;

import dev.xfj.token.Token;
import dev.xfj.token.Tokenizer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Incorrect usage!");
            System.err.println("Pass a .mz file!");
            System.exit(1);
        }
        try {
            String input = Files.readString(Path.of(args[0]));
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.tokenize(input);
            tokens.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
