package com.stefanik.sfgg.service.parseMethods;

import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.service.ParseMethod.ChooseProductionMethod;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ChooseProductionMethodTest {

    @Test
    public void leftTest01() {
        String result = ChooseProductionMethod.LEFT.apply(
                new Transformation("A", Arrays.asList("a", "b", "c")));
        assertEquals("a", result);
    }

    @Test
    public void randomTest01() {
        String result = ChooseProductionMethod.RANDOM.apply(
                new Transformation("A", Arrays.asList("a", "b", "c")));
        assertTrue("a".equals(result) || "b".equals(result) || "c".equals(result));
    }
}
