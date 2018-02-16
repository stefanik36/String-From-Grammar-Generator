package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.Transformations;
import com.stefanik.sfgg.util.InvalidGrammar;

import java.util.ArrayList;
import java.util.List;

public class GrammarBuilder {
    private List<String> terminals;
    private List<String> nonTerminals;
    private String startSymbol;
    private Transformations transformations;

    public GrammarBuilder(List<String> terminals) {
        this.terminals = terminals;
    }

    public GrammarBuilder withNonTerminals(List<String> nonTerminals) throws InvalidGrammar {
        checkCollisons(nonTerminals);
        this.nonTerminals = nonTerminals;
        return this;
    }

    private void checkCollisons(List<String> nonTerminals) throws InvalidGrammar {
        if (terminals.stream().anyMatch(t -> nonTerminals.stream().anyMatch(nt -> t.equals(nt)))) {
            throw new InvalidGrammar("Terminal the same as non terminal.");
        }
    }

    public GrammarBuilder withStartSymbol(String startSymbol) {
        this.startSymbol = startSymbol;
        return this;
    }

    public GrammarBuilder withTransformations(Transformations transformations) {
        this.transformations = transformations;
        return this;
    }

    public Grammar build() throws InvalidGrammar {
        resolveNulls();
        return new Grammar(this);
    }

    private void resolveNulls() throws InvalidGrammar {
        if (terminals == null) {
            throw new InvalidGrammar("Terminals unknown.");
        }
        if (startSymbol == null) {
            throw new InvalidGrammar("Start symbol unknown.");
        }
        if (nonTerminals == null) {
            nonTerminals = new ArrayList<>();
        }
        if (transformations == null) {
            transformations = new Transformations();
        }
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public String getStartSymbol() {
        return startSymbol;
    }

    public Transformations getTransformations() {
        return transformations;
    }
}
