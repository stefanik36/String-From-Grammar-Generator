package com.stefanik.sfgg.userInterface.collector.grammarInformationCollector;

import com.stefanik.sfgg.Configuration;
import com.stefanik.sfgg.model.Transformation;
import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.util.InvalidGrammarException;

import java.util.ArrayList;
import java.util.List;

public class ProductionCollector extends SymbolCollector {

    private final static String SYMBOLS = "productions rules";
    private final char CHARACTER = Configuration.PRODUCTION_RULES_CHAR;
    private final boolean HAS_STEPS = true;


    @Override
    protected void doCollect(GrammarBuilder gb) throws InvalidGrammarException {
        while (true) {
            System.out.print("production key: ");
            String input = scanner.nextLine();
            if (!TO_NEXT_STEP_STRING.equals(input)) {
                addProduction(gb, input);
            } else {
                break;
            }
        }
    }

    private void addProduction(GrammarBuilder gb, String key) throws InvalidGrammarException {
        System.out.println();
        showProductions(gb.getTransformations());
        List<String> values = new ArrayList<>();
        Transformation p = new Transformation(key, values);
        while (true) {
            System.out.println("production values (type \""+TO_NEXT_STEP_STRING+"\" to next production): ");
            System.out.println(p);
            String input = scanner.nextLine();
            if (!TO_NEXT_STEP_STRING.equals(input)) {
                values.add(input);
            } else {
                break;
            }
        }
        gb.addTransformation(p);
    }

    private void showProductions(List<Transformation> transformations) {
        transformations.forEach(System.out::println);
    }

    @Override
    protected char getCharacter() {
        return CHARACTER;
    }

    @Override
    protected String getSymbol() {
        return SYMBOLS;
    }

    @Override
    protected boolean hasSteps() {
        return HAS_STEPS;
    }
}
