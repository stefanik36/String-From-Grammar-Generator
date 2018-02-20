package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.ParseStrategy;
import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.service.ParseMethods.ChooseProductionMethods;
import com.stefanik.sfgg.service.ParseMethods.ParseStringMethods;
import com.stefanik.sfgg.util.InvalidGrammarException;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class StringGeneratorTest {
    private static final String EPSILON = "";

    @Test
    public void generateTest01() throws InvalidGrammarException {
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
        assertEquals("aaaaa", s);
    }


    @Test
    public void generateTest02() throws InvalidGrammarException {
        Grammar g = new GrammarBuilder(Arrays.asList("a","b","c"))
                .withNonTerminals(Arrays.asList("S", "X", "Y"))
                .withStartSymbol("S")
                .withTransformations(
                        (Arrays.asList(
                                new Transformation("S", Arrays.asList("XYXaXbcY")),
                                new Transformation("X", Arrays.asList("YaaYaY")),
                                new Transformation("Y", Arrays.asList("abc"))
                        ))
                ).build();
        ParseStrategy ps = new ParseStrategy(ParseStringMethods.LEFT, ChooseProductionMethods.LEFT);

        StringGenerator sg = new StringGenerator(g, ps);
        String s = sg.generate();
        assertEquals("abcaaabcaabcabcabcaaabcaabcaabcaaabcaabcbcabc", s);
    }

    @Test(expected = InvalidGrammarException.class)
    public void generateTest03() throws InvalidGrammarException {
        Grammar g = new GrammarBuilder(Arrays.asList("a","b"))
                .withNonTerminals(Arrays.asList("S"))
                .withStartSymbol("S")
                .withTransformations(
                        (Arrays.asList(
                                new Transformation("S", Arrays.asList("abc"))
                        ))
                ).build();
        ParseStrategy ps = new ParseStrategy(ParseStringMethods.LEFT, ChooseProductionMethods.LEFT);

        StringGenerator sg = new StringGenerator(g, ps);
        sg.generate();
    }
}
