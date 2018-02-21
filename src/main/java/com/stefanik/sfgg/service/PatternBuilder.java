package com.stefanik.sfgg.service;

import java.util.List;
import java.util.regex.Pattern;

public class PatternBuilder {
    private StringBuilder sb;

    public PatternBuilder(List<String> list) {
        sb = new StringBuilder("^(");
        appendList(list);
    }

    public PatternBuilder withList(List<String> list) {
        sb.append("|");
        appendList(list);
        return this;
    }

    public Pattern build() {
        sb.append(")*$");
        Pattern pattern = Pattern.compile(sb.toString());
        return pattern;
    }

    private void appendList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append("|");
            }
        }
    }


}
