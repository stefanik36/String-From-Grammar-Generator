package com.stefanik.sfgg.userInterface;

import com.stefanik.sfgg.Configuration;
import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.ParseStrategy;
import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.service.ParseMethod.ChooseProductionMethod;
import com.stefanik.sfgg.service.ParseMethod.ParseStringMethod;
import com.stefanik.sfgg.service.StringGenerator;
import com.stefanik.sfgg.userInterface.collector.NonTerminalCollector;
import com.stefanik.sfgg.userInterface.collector.ProductionCollector;
import com.stefanik.sfgg.userInterface.collector.StartSymbolCollector;
import com.stefanik.sfgg.userInterface.collector.TerminalCollector;
import com.stefanik.sfgg.util.InvalidGrammarException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.stefanik.sfgg.Configuration.TERMINAL_CHAR;
import static com.stefanik.sfgg.Configuration.NONTERMINAL_CHAR;
import static com.stefanik.sfgg.Configuration.PRODUCTION_RULES_CHAR;
import static com.stefanik.sfgg.Configuration.START_SYMBOL_CHAR;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private static final int NO_RESULTS = 100;

    public void start() {
        showHeader();
        GrammarBuilder gb = new GrammarBuilder();
        try {
            Grammar grammar = getGrammar(gb);
            ParseStrategy parseStrategy = getParseStrategy();
            StringGenerator sg = new StringGenerator(grammar, parseStrategy);
            List<String> result = getResult(sg);
            showResult(result);
        } catch (InvalidGrammarException e) {
            e.printStackTrace();
        }
    }

    private List<String> getResult(StringGenerator sg) throws InvalidGrammarException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < NO_RESULTS; i++) {
            result.add(sg.generate());
        }
        return result;
    }

    private void showResult(List<String> result) {
        System.out.println("------------------");
        result.forEach(r -> System.out.println(" [" + r.length() + "] " + r));
        System.out.println("------------------");
    }

    private ParseStrategy getParseStrategy() {
        return new ParseStrategy(ParseStringMethod.RANDOM, ChooseProductionMethod.RANDOM);
    }

    private Grammar getGrammar(GrammarBuilder gb) throws InvalidGrammarException {
        new TerminalCollector().collect(gb);
        new NonTerminalCollector().collect(gb);
        new StartSymbolCollector().collect(gb);
        new ProductionCollector().collect(gb);
        return gb.build();
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
