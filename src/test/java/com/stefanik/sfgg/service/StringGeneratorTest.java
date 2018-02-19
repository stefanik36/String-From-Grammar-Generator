package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.ParseStrategy;
import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.service.ParseMethods.ChooseProductionMethods;
import com.stefanik.sfgg.service.ParseMethods.ParseStringMethods;
import com.stefanik.sfgg.util.InvalidGrammar;
import org.junit.Test;

import java.util.Arrays;

public class StringGeneratorTest {
    private static final String EPSILON = "";

    @Test
    public void generateTest() throws InvalidGrammar {
        Grammar g = new GrammarBuilder(Arrays.asList("a"))
                .withNonTerminals(Arrays.asList("S", "X", "Y"))
                .withStartSymbol("S")
                .withTransformations(
                        (Arrays.asList(
                                new Transformation("S", Arrays.asList("aXa")),
                                new Transformation("X", Arrays.asList("Yaaa")),
                                new Transformation("Y", Arrays.asList(EPSILON))
                        ))
                ).build();
        ParseStrategy ps = new ParseStrategy(ParseStringMethods.LEFT, ChooseProductionMethods.LEFT);

        StringGenerator sg = new StringGenerator(g, ps);
        String s = sg.generate();
        System.out.println(s);
    }
}
