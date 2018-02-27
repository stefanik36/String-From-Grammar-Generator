package com.stefanik.sfgg.userInterface.collector;

import com.stefanik.sfgg.service.GrammarBuilder;
import com.stefanik.sfgg.userInterface.collector.grammarInformationCollector.Collector;
import com.stefanik.sfgg.util.InvalidGrammarException;
import com.stefanik.sfgg.util.InvalidInputException;

import java.util.Scanner;

public class NoResultsCollector {


    protected final Scanner scanner = new Scanner(System.in);

    public int getNoResults() throws InvalidInputException {
        showPrompt();
        int input = scanner.nextInt();
        if (input >= 0) {
            return input;
        }
        throw new InvalidInputException("Number of strings must be grater or equal 0.");
    }

    private void showPrompt() {
        System.out.println("Set number of generated strings: ");
    }
}
