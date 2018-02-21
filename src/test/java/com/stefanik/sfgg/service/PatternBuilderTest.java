package com.stefanik.sfgg.service;

import com.stefanik.sfgg.util.InvalidGrammarException;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;

public class PatternBuilderTest {


    @Test
    public void buildTest01(){
        Pattern p = new PatternBuilder(Arrays.asList("a","b")).withList(Arrays.asList("A","B")).build();
//        System.out.print(p.toString());
        assertEquals(p.toString(),"^(a|b|A|B)*$");
    }
}
