package com.stefanik.sfgg.userInterface;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.ParseStrategy;
import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.service.ParseMethod.ChooseProductionMethod;
import com.stefanik.sfgg.service.ParseMethod.ParseStringMethod;
import com.stefanik.sfgg.service.StringGenerator;
import com.stefanik.sfgg.userInterface.collector.NoResultsCollector;
import com.stefanik.sfgg.userInterface.collector.grammarInformationCollector.NonTerminalCollector;
import com.stefanik.sfgg.userInterface.collector.grammarInformationCollector.ProductionCollector;
import com.stefanik.sfgg.userInterface.collector.grammarInformationCollector.StartSymbolCollector;
import com.stefanik.sfgg.userInterface.collector.grammarInformationCollector.TerminalCollector;
import com.stefanik.sfgg.util.InvalidGrammarException;
import com.stefanik.sfgg.util.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

import static com.stefanik.sfgg.Configuration.TERMINAL_CHAR;
import static com.stefanik.sfgg.Configuration.NONTERMINAL_CHAR;
import static com.stefanik.sfgg.Configuration.PRODUCTION_RULES_CHAR;
import static com.stefanik.sfgg.Configuration.START_SYMBOL_CHAR;

public class UserInterface {

    public void start() {
        showHeader();
        GrammarBuilder gb = new GrammarBuilder();
        try {
            Grammar grammar = getGrammar(gb);
            ParseStrategy parseStrategy = getDefaultParseStrategy();
            StringGenerator sg = new StringGenerator(grammar, parseStrategy);
            int noResults = getNoResults();
            List<String> result = getResult(sg, noResults);
            showResult(result);
        } catch (InvalidGrammarException e) {
            e.printStackTrace();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public int getNoResults() throws InvalidInputException {
        return new NoResultsCollector().getNoResults();
    }

    private List<String> getResult(StringGenerator sg, int noResults) throws InvalidGrammarException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < noResults; i++) {
            result.add(sg.generate());
        }
        return result;
    }

    private void showResult(List<String> result) {
        System.out.println("------------------");
        result.forEach(r -> System.out.println(" [" + r.length() + "] " + r));
        System.out.println("------------------");
    }

    private ParseStrategy getDefaultParseStrategy() {
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
