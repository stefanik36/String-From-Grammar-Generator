package com.stefanik.sfgg.service;

import com.stefanik.sfgg.model.Grammar;
import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.util.InvalidGrammar;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class GrammarBuilderTest {


    @Test
    public void buildTest01() throws InvalidGrammar {
        Grammar g = new GrammarBuilder(Arrays.asList("a", "b"))
                .withNonTerminals(Arrays.asList("A"))
                .withStartSymbol("A")
                .withTransformations(
                        Arrays.asList(
                                new Transformation("A", Arrays.asList("a"))
                        )
                )
                .build();
        assertThat(g.getTerminals(), contains("a", "b"));
        assertThat(g.getNonTerminals(), contains("A"));
        assertEquals("A", g.getStartSymbol());

        assertThat(g.getTransformations(), contains(
                hasProperty("key", is("A"))
        ));
    }

    @Test(expected = InvalidGrammar.class)
    public void buildTest02() throws InvalidGrammar {
        new GrammarBuilder(Arrays.asList("a", "b"))
                .withNonTerminals(Arrays.asList("A"))
                .withStartSymbol("B")
                .withTransformations(
                        Arrays.asList(
                                new Transformation("A", Arrays.asList("a"))
                        )
                )
                .build();
    }
}
