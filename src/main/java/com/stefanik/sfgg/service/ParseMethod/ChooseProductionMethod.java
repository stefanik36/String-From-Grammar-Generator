package com.stefanik.sfgg.service.ParseMethod;

import com.stefanik.sfgg.model.Transformation;

import java.util.Random;
import java.util.function.Function;

public class ChooseProductionMethod {
    private static Random r = new Random();
    /*
     * empty list checked in @see StringGenerator#generate
     */
    public static Function<Transformation, String> LEFT =
            (Transformation t) -> t.getValues().stream().findFirst().get();

    public static Function<Transformation, String> RANDOM =
            (Transformation t) -> t.getValues().get(r.nextInt(t.getValues().size()));
}
