package com.stefanik.sfgg.service.ParseMethod;

import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.model.Tuple;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class ParseStringMethod {
    private static Random r = new Random();
    /*
     * empty list checked in @see StringGenerator#generate
     */
    public static Function<List<Tuple<Transformation, Integer>>, Tuple<Transformation, Integer>> LEFT =
            (List<Tuple<Transformation, Integer>> keys) -> {
                return keys.stream()
                        .min(Comparator.comparingDouble(Tuple::getR)).get();
            };

    public static Function<List<Tuple<Transformation, Integer>>, Tuple<Transformation, Integer>> RANDOM =
            (List<Tuple<Transformation, Integer>> keys) -> {
                return keys.get(r.nextInt(keys.size()));
            };

}
