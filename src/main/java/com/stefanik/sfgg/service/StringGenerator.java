package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.model.Transformations;
import com.stefanik.sfgg.util.InvalidGrammar;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StringGenerator {
    private Grammar grammar;

    public StringGenerator(Grammar grammar) {
        this.grammar = grammar;
    }




    public String generate() throws InvalidGrammar {
        String ss = grammar.getStartSymbol();
        List<String> termianls = grammar.getTerminals();
        List<String> nonTerminals = grammar.getNonTerminals();
        Transformations transformations = grammar.getTransformations();

        StringBuilder sb = new StringBuilder(ss);
        List<String> keys = transformations.getKeys();
//        Random rand = new Random();
        while (nonTerminals.stream().anyMatch(nt -> sb.toString().contains(nt))) {
            List<String> filtered = keys
                    .stream()
                    .filter(k -> sb.indexOf(k) >= 0)
                    .collect(Collectors.toList());

            if (!filtered.isEmpty()) {
                String key = filtered.get(rand.nextInt() % filtered.size());
                int i = sb.indexOf(key);
                sb.delete(i, i + key.length());
                Transformation trans = transformations.get(key);
                if (!trans.isEmpty()) {
                    int index = rand.nextInt(trans.size());
                    String tran = trans.get(index);
                    sb.insert(i, tran);
                }
            } else {
                System.out.println("empty!!!");
            }

        }
        return sb.toString();
    }

}
