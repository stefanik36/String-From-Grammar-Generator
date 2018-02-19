package com.stefanik.sfgg.service.ParseMethods;

import com.stefanik.sfgg.model.Transformation;

import java.util.Random;
import java.util.function.Function;

public class ChooseProductionMethods {
    /*
     * empty list checked in @see StringGenerator#generate
     */
    public static Function<Transformation, String> LEFT =
            (Transformation t) -> t.getValues().stream().findFirst().get();

    private static Random r = new Random();
    public static Function<Transformation, String> RANDOM =
            (Transformation t) -> t.getValues().get(r.nextInt(t.getValues().size()));
}
