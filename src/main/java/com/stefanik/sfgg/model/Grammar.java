package com.stefanik.sfgg.model;

import com.stefanik.sfgg.model.Transformations;
import com.stefanik.sfgg.service.GrammarBuilder;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Grammar {

    private List<String> terminals;
    private List<String> nonTerminals;
    private String startSymbol;
    private Transformations transformations;

    public Grammar(GrammarBuilder gb) {
        this.terminals = gb.getTerminals();
        this.nonTerminals = gb.getNonTerminals();
        this.startSymbol = gb.getStartSymbol();
        this.transformations = gb.getTransformations();
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public Transformations getTransformations() {
        return transformations;
    }

    public String getStartSymbol() {
        return startSymbol;
    }



}
