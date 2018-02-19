package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.util.InvalidGrammar;

import java.util.ArrayList;
import java.util.List;

public class GrammarBuilder {
    private List<String> terminals;
    private List<String> nonTerminals;
    private String startSymbol;
    private List<Transformation> transformations;

    public GrammarBuilder(List<String> terminals) {
        this.terminals = terminals;
    }

    public GrammarBuilder withNonTerminals(List<String> nonTerminals) throws InvalidGrammar {
        checkCollisions(nonTerminals);
        this.nonTerminals = nonTerminals;
        return this;
    }

    private void checkCollisions(List<String> nonTerminals) throws InvalidGrammar {
        if (terminals.stream().anyMatch(t -> nonTerminals.stream().anyMatch(nt -> t.equals(nt)))) {
            throw new InvalidGrammar("Terminal the same as non terminal.");
        }
    }

    public GrammarBuilder withStartSymbol(String startSymbol) {
        this.startSymbol = startSymbol;
        return this;
    }

    public GrammarBuilder withTransformations(List<Transformation> transformations) {
        this.transformations = transformations;
        return this;
    }

    public Grammar build() throws InvalidGrammar {
        checkGrammar();
        return new Grammar(this);
    }

    private void checkGrammar() throws InvalidGrammar {
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
            transformations = new ArrayList<>();
        }
        if (!(terminals.contains(startSymbol) || nonTerminals.contains(startSymbol))) {
            throw new InvalidGrammar("Wrong start symbol.");
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

    public List<Transformation> getTransformations() {
        return transformations;
    }
}
