package dev.xfj;

import dev.xfj.token.Token;
import dev.xfj.token.TokenType;
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

            Files.writeString(Path.of("out.asm"), tokensToAssembly(tokens));

            //TODO Get it working on Linux
            //system("nasm -felf64 out.asm)
            //system("ld -o out out.o)
            //Executing ./out should return specified exit code using echo $?
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String tokensToAssembly(List<Token> tokens) {
        StringBuilder output = new StringBuilder();
        output.append("global _start\n_start:\n");

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            if (token.type() == TokenType._return) {
                if (i + 1 < tokens.size() && tokens.get(i + 1).type() == TokenType.int_lit) {
                    if (i + 2 < tokens.size() && tokens.get(i + 2).type() == TokenType.semi) {
                        output.append("    mov rax, 60\n");

                        output.append("    mov rdi, ");
                        output.append(tokens.get(i+1).value());
                        output.append("\n");

                        output.append("    syscall");
                    }
                }
            }
        }
        return output.toString();
    }
}
