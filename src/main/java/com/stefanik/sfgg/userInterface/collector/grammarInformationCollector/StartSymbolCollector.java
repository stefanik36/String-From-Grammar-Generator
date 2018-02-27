package com.stefanik.sfgg.userInterface.collector.grammarInformationCollector;

import com.stefanik.sfgg.Configuration;
import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.util.InvalidGrammarException;

public class StartSymbolCollector extends SymbolCollector {

    private final static String SYMBOL = "start symbol";
    private final char CHARACTER = Configuration.START_SYMBOL_CHAR;
    private final boolean HAS_STEPS= false;


    @Override
    protected void doCollect(GrammarBuilder gb) throws InvalidGrammarException {
        gb.withStartSymbol(scanner.nextLine());
    }

    @Override
    protected char getCharacter() {
        return CHARACTER;
    }

    @Override
    protected String getSymbol() {
        return SYMBOL;
    }

    @Override
    protected boolean hasSteps() {
        return HAS_STEPS;
    }
}
