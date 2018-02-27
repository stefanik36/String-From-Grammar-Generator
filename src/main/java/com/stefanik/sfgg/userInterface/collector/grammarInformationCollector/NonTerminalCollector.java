package com.stefanik.sfgg.userInterface.collector.grammarInformationCollector;

import com.stefanik.sfgg.Configuration;
import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.util.InvalidGrammarException;

public class NonTerminalCollector extends SymbolCollector {

    private final static String SYMBOLS = "nonterminal symbols";
    private final char CHARACTER = Configuration.NONTERMINAL_CHAR;
    private final boolean HAS_STEPS = true;


    @Override
    protected void doCollect(GrammarBuilder gb) throws InvalidGrammarException {
        while (true) {
            String input = scanner.nextLine();
            if (!TO_NEXT_STEP_STRING.equals(input)) {
                addAndShow(gb, input);
            } else {
                break;
            }
        }
    }

    private void addAndShow(GrammarBuilder gb, String input) throws InvalidGrammarException {
        gb.addNonTerminal(input);
        showList(gb.getNonTerminals(), CHARACTER);
    }

    @Override
    protected char getCharacter() {
        return CHARACTER;
    }

    @Override
    protected String getSymbol() {
        return SYMBOLS;
    }

    @Override
    protected boolean hasSteps() {
        return HAS_STEPS;
    }
}
