package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.util.InvalidGrammarException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarBuilder {
    private List<String> terminals;
    private List<String> nonTerminals;
    private String startSymbol;
    private List<Transformation> transformations;


    public GrammarBuilder() {
        this.terminals = new ArrayList<>();
        this.nonTerminals = new ArrayList<>();
        this.transformations = new ArrayList<>();
    }

    public GrammarBuilder addTerminal(String t) throws InvalidGrammarException {
        this.terminals.add(t);
        checkSymbols();
        return this;
    }

    public GrammarBuilder addNonTerminal(String nt) throws InvalidGrammarException {
        this.nonTerminals.add(nt);
        checkSymbols();
        return this;
    }

    public GrammarBuilder addTransformation(Transformation t) throws InvalidGrammarException {
        this.transformations.add(t);
        checkTransformations();
        return this;
    }


    public GrammarBuilder(List<String> terminals) {
        this();
        this.terminals = terminals;
    }

    public GrammarBuilder withNonTerminals(List<String> nonTerminals) throws InvalidGrammarException {
        this.nonTerminals = nonTerminals;
        checkSymbols();
        return this;
    }

    public GrammarBuilder withStartSymbol(String startSymbol) throws InvalidGrammarException {
        this.startSymbol = startSymbol;
        checkStartSymbol();
        return this;
    }

    public GrammarBuilder withTransformations(List<Transformation> transformations) throws InvalidGrammarException {
        this.transformations = transformations;
        checkTransformations();
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
        checkStartSymbol();
    }

    private void checkStartSymbol() throws InvalidGrammarException {
        if (!(terminals.contains(startSymbol) || nonTerminals.contains(startSymbol))) {
            throw new InvalidGrammarException("Wrong start symbol.");
        }
    }

    private void checkSymbols() throws InvalidGrammarException {
        if (terminals.stream().anyMatch(t -> nonTerminals.stream().anyMatch(nt -> t.equals(nt)))) {
            throw new InvalidGrammarException("Terminal the same as non terminal.");
        }
    }

    private void checkTransformations() throws InvalidGrammarException {
        Pattern p = new PatternBuilder(terminals).withList(nonTerminals).build();
        checkProductionKeys(p);
        checkProductionValues(p);
    }

    private void checkProductionKeys(Pattern p) throws InvalidGrammarException {
        if (!transformations.stream().allMatch(t -> p.matcher(t.getKey()).matches())) {
            throw new InvalidGrammarException("Production key contains unknown symbol.");
        }
    }

    private void checkProductionValues(Pattern p) throws InvalidGrammarException {
        if (!transformations.stream().allMatch(
                t -> t.getValues()
                        .stream()
                        .allMatch(v -> p.matcher(v).matches()))) {
            throw new InvalidGrammarException("Production value contains unknown symbol.");
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
