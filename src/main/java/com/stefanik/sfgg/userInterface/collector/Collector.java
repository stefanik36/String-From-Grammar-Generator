package com.stefanik.sfgg.userInterface.collector;

import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.util.InvalidGrammarException;

public interface Collector {
    void collect(GrammarBuilder gb) throws InvalidGrammarException;
}
