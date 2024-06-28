package dev.xfj;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Incorrect usage!");
            System.err.println("Pass a .mz file!");
            System.exit(1);
        }
        try {
            String input = Files.readString(Path.of(args[0]));
            System.out.println(input);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
