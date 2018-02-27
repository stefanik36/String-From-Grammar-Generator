package com.stefanik.sfgg.service.parseMethods;

import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.model.Tuple;
import com.stefanik.sfgg.service.ParseMethod.ParseStringMethod;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class ParseStringMethodTest {

    @Test
    public void leftTest01() {
        Tuple<Transformation, Integer> result = ParseStringMethod.LEFT.apply(Arrays.asList(
                new Tuple<>(new Transformation("a", Arrays.asList()), 2),
                new Tuple<>(new Transformation("a", Arrays.asList()), 5)
        ));
        assertEquals("a", result.getL().getKey());
        assertEquals(2, (int) result.getR());
    }

    @Test(expected = NoSuchElementException.class)
    public void leftTest02() {
        ParseStringMethod.LEFT.apply(Arrays.asList());
    }
}
