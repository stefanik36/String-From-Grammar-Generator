package com.stefanik.sfgg.userInterface.collector.grammarInformationCollector;

import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.util.InvalidGrammarException;

import java.util.List;
import java.util.Scanner;

public abstract class SymbolCollector implements Collector {

    protected final Scanner scanner = new Scanner(System.in);
    protected final String TO_NEXT_STEP_STRING = "\\next";

    @Override
    public void collect(GrammarBuilder gb) throws InvalidGrammarException {
        showPrompt();
        doCollect(gb);
    }

    private void showPrompt() {
        StringBuilder prompt = new StringBuilder("Set ");
        prompt.append(getSymbol());
        prompt.append(" [" + getCharacter() + "]");
        if (hasSteps()) {
            prompt.append(" (type \""+TO_NEXT_STEP_STRING+"\" to next step)");
        }
        prompt.append(":");
        System.out.println(prompt.toString());
    }

    protected void showList(List<String> list, char character) {
        System.out.print(character + " = {");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("}");
    }

    protected abstract void doCollect(GrammarBuilder gb) throws InvalidGrammarException;

    protected abstract char getCharacter();

    protected abstract String getSymbol();

    protected abstract boolean hasSteps();


}
