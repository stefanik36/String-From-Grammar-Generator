package com.stefanik.sfgg.model;

import com.stefanik.sfgg.util.InvalidGrammar;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Transformations {
//    private List<Transformation> transformations;
//
//    public Transformations() {
//        this.transformations = new ArrayList<>();
//    }
//    public Transformations(List<Transformation> ts) {
//        this();
//        addAll(ts);
//    }
//
//    public void addAll(List<Transformation> ts) {
//        this.transformations.addAll(ts);
//    }
//
//    public void add(Transformation t) {
//        transformations.add(t);
//    }
//
//    public List<String> getKeys() {
//        List<String> keys = transformations
//                .stream()
//                .map(t -> t.getKey())
//                .collect(Collectors.toList());
//        return keys;
//    }
//
//    public Transformation get(String key) throws InvalidGrammar {
//        Optional<Transformation> transformation = transformations
//                .stream()
//                .filter(t -> t.getKey().equals(key)).findFirst();
//        if (!transformation.isPresent()) {
//            throw new InvalidGrammar("Transformation with key " + key + " does not exists.");
//        }
//        return transformation.get();
//    }

}
