package com.example.fishapp.app.dictionary.view;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class DictionaryView {
    private String name;
    private String locale;
    private Map<String, String> values;
}
