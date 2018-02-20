package com.stefanik.sfgg.service.parseMethods;

import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.model.Tuple;
import com.stefanik.sfgg.service.ParseMethods.ParseStringMethods;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class ParseStringMethodsTest {

    @Test
    public void leftTest01() {
        Tuple<Transformation, Integer> result = ParseStringMethods.LEFT.apply(Arrays.asList(
                new Tuple<>(new Transformation("a", Arrays.asList()), 2),
                new Tuple<>(new Transformation("a", Arrays.asList()), 5)
        ));
        assertEquals("a", result.getL().getKey());
        assertEquals(2, (int) result.getR());
    }

    @Test(expected = NoSuchElementException.class)
    public void leftTest02() {
        ParseStringMethods.LEFT.apply(Arrays.asList());
    }
}
