package com.stefanik.sfgg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final static char NONTERMINAL_CHAR = 'N';
    private final static char TERMINAL_CHAR = '\u01A9';
    private final static char PRODUCTION_RULES_CHAR = 'P';
    private final static char START_SYMBOL_CHAR = 'S';
    private Scanner scanner = new Scanner(System.in);


    public void start() {
        showHeader();


        List<String> terminals = getAndShowTorNt(TERMINAL_CHAR);
        List<String> nonTerminals = getAndShowTorNt(NONTERMINAL_CHAR);
    }

    private List<String> getAndShowTorNt(char character) {
        List<String> terminals = new ArrayList<>();
        String symbols;
        if(character == TERMINAL_CHAR){
            symbols = "terminal";
        } else{
            symbols = "nonterminal";
        }
        System.out.println("Set "+symbols+" symbols (empty string and enter to next step):");
        while (true) {
            String input = scanner.nextLine();
            if ("".equals(input)) {
                break;
            } else {
                terminals.add(input);
            }

            showTorNt(terminals, character);
        }
        return terminals;
    }

    private void showTorNt(List<String> list, char character) {
        System.out.print(character + " = {");
        for (int i = 0 ; i< list.size();i++) {
            System.out.print(list.get(i));
            if(i<list.size()-1){
                System.out.print(",");
            }
        }
        System.out.println("}");
    }

    private void showHeader() {
        System.out.println("+---------------------------------+");
        System.out.println("|  STRING FROM GRAMMAR GENERATOR  |");
        System.out.println("|          G = (" + NONTERMINAL_CHAR + "," + TERMINAL_CHAR + "," + PRODUCTION_RULES_CHAR + "," + START_SYMBOL_CHAR + ")          |");
        System.out.println("|                                 |");
        System.out.println("| " + NONTERMINAL_CHAR + " - nonterminal symbols         |");
        System.out.println("| " + TERMINAL_CHAR + " - terminal symbols            |");
        System.out.println("| " + PRODUCTION_RULES_CHAR + " - production rules            |");
        System.out.println("| " + START_SYMBOL_CHAR + " - start symbol                |");
        System.out.println("+---------------------------------+");
    }


}
