package com.stefanik.sfgg.service.ParseMethods;

import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.model.Tuple;
import com.stefanik.sfgg.util.InvalidGrammar;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ParseStringMethods {
    /*
     * empty list checked in @see StringGenerator#generate
     */
    public static Function<List<Tuple<Transformation, Integer>>, Tuple<Transformation, Integer>> LEFT =
            (List<Tuple<Transformation, Integer>> keys) -> {
                return keys.stream()
                        .min(Comparator.comparingDouble(Tuple::getR)).get();
            };

}
