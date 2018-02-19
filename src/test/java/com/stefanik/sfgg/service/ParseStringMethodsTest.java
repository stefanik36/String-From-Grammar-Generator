package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.model.Tuple;
import com.stefanik.sfgg.service.ParseMethods.ParseStringMethods;
import com.stefanik.sfgg.util.InvalidGrammar;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ParseStringMethodsTest {

    @Test
    public void leftTest() throws InvalidGrammar {
        Tuple<Transformation, Integer> result = ParseStringMethods.LEFT.apply(Arrays.asList(
                new Tuple<>(new Transformation("a", Arrays.asList()), 2),
                new Tuple<>(new Transformation("a", Arrays.asList()), 5)
        ));
        assertEquals("a", result.getL());
        assertEquals(2, (int) result.getR());
    }
}
