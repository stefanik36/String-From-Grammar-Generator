package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.*;
import com.stefanik.sfgg.util.InvalidGrammarException;

import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringGenerator {
    private Grammar grammar;
    private ParseStrategy parseStrategy;

    public StringGenerator(Grammar grammar, ParseStrategy parseStrategy) {
        this.grammar = grammar;
        this.parseStrategy = parseStrategy;
    }

    public String generate() throws InvalidGrammarException {
        String ss = grammar.getStartSymbol();
        List<String> terminals = grammar.getTerminals();
        List<String> nonTerminals = grammar.getNonTerminals();
        List<Transformation> transformations = grammar.getTransformations();

        StringBuilder sb = new StringBuilder(ss);
        while (nonTerminals.stream().anyMatch(nt -> sb.toString().contains(nt))) {
            List<Tuple<Transformation, Integer>> filtered = getPotentialTransformations(sb, transformations);

            if (!filtered.isEmpty()) {
                Tuple<Transformation, Integer> keyTuple = parseStrategy.getParseString().apply(filtered);
                Transformation t = keyTuple.getL();
                if (!keyTuple.getL().getValues().isEmpty()) {
                    String production = parseStrategy.getProductionMethod().apply(t);
                    modify(sb, keyTuple.getR(), t.getKey().length(), production);
                }
            } else {
                throw new InvalidGrammarException("Cannot replace non terminal word. " +
                        "Needed transformation do not exists.");
            }
        }
        String result = sb.toString();
        checkResult(result, terminals);
        return result;
    }

    private void modify(StringBuilder sb, int index, int length, String production) {
        sb.delete(index, index + length);
        sb.insert(index, production);
    }

    private BiFunction<Transformation, String, Integer> index =
            (Transformation t, String s) -> s.indexOf(t.getKey());

    private List<Tuple<Transformation, Integer>> getPotentialTransformations(StringBuilder sb, List<Transformation> trans) {
        return trans.stream()
                .filter(k -> index.apply(k, sb.toString()) >= 0)
                .map(k -> new Tuple<>(k, index.apply(k, sb.toString())))
                .collect(Collectors.toList());
    }

    private void checkResult(String result, List<String> terminals) throws InvalidGrammarException {
        Pattern pattern = new PatternBuilder(terminals).build();
        Matcher matcher = pattern.matcher(result);
        if (!matcher.matches()) {
            throw new InvalidGrammarException("Unknown symbol. Check terminal symbols.");
        }

    }

}
