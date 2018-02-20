package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.util.InvalidGrammarException;

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

    public GrammarBuilder withNonTerminals(List<String> nonTerminals) throws InvalidGrammarException {
        checkCollisions(nonTerminals);
        this.nonTerminals = nonTerminals;
        return this;
    }

    private void checkCollisions(List<String> nonTerminals) throws InvalidGrammarException {
        if (terminals.stream().anyMatch(t -> nonTerminals.stream().anyMatch(nt -> t.equals(nt)))) {
            throw new InvalidGrammarException("Terminal the same as non terminal.");
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

    public Grammar build() throws InvalidGrammarException {
        checkGrammar();
        return new Grammar(this);
    }

    private void checkGrammar() throws InvalidGrammarException {
        if (terminals == null) {
            throw new InvalidGrammarException("Terminals unknown.");
        }
        if (startSymbol == null) {
            throw new InvalidGrammarException("Start symbol unknown.");
        }
        if (nonTerminals == null) {
            nonTerminals = new ArrayList<>();
        }
        if (transformations == null) {
            transformations = new ArrayList<>();
        }
        if (!(terminals.contains(startSymbol) || nonTerminals.contains(startSymbol))) {
            throw new InvalidGrammarException("Wrong start symbol.");
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
