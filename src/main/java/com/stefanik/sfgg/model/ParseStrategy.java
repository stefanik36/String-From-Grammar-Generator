package com.stefanik.sfgg.model;

import com.stefanik.sfgg.model.Tuple;

import java.util.List;
import java.util.function.Function;

public class ParseStrategy {
    private Function<List<Tuple<Transformation, Integer>>, Tuple<Transformation, Integer>> parseStringMethod;
    private Function<Transformation, String> chooseProductionMethod;

    public ParseStrategy(
            Function<List<Tuple<Transformation, Integer>>, Tuple<Transformation, Integer>> parseStringMethod,
            Function<Transformation, String> chooseProductionMethod
    ) {
        this.parseStringMethod = parseStringMethod;
        this.chooseProductionMethod = chooseProductionMethod;
    }

    /**
     * returns a function that selects the transformation
     * that will be used in the next iteration of changing string
     *
     * @return selected tuple
     */
    public Function<List<Tuple<Transformation, Integer>>, Tuple<Transformation, Integer>> getParseString() {
        return parseStringMethod;
    }

    /**
     * returns a function that selects production from transformation
     *
     * @return selected tuple
     */
    public  Function<Transformation, String> getProductionMethod() {
        return chooseProductionMethod;
    }

}
