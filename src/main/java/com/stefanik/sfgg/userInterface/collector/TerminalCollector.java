package com.stefanik.sfgg.userInterface.collector;

import com.stefanik.sfgg.Configuration;
import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.util.InvalidGrammarException;


public class TerminalCollector extends SymbolCollector {

    private final static String SYMBOLS = "terminal symbols";
    private final char CHARACTER = Configuration.TERMINAL_CHAR;
    private final boolean HAS_STEPS= true;


    @Override
    protected void doCollect(GrammarBuilder gb) throws InvalidGrammarException {
        while (true) {
            String input = scanner.nextLine();
            if (!"".equals(input)) {
                addAndShow(gb, input);
            } else {
                break;
            }
        }
    }

    private void addAndShow(GrammarBuilder gb, String input) throws InvalidGrammarException {
        gb.addTerminal(input);
        showList(gb.getTerminals(), CHARACTER);
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
