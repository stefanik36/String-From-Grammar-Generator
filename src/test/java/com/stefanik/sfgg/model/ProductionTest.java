package com.stefanik.sfgg.model;

import com.stefanik.sfgg.service.PatternBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;

public class ProductionTest {


    @Test
    public void toStringTest01() {
        Transformation t = new Transformation("aA", Arrays.asList("a", "c"));
        assertEquals(t.toString(), "aA -> a | c");

    }
}
