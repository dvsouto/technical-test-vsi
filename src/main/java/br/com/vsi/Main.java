package br.com.vsi;

import br.com.vsi.presentation.console.AnagramConsolePrint;
import br.com.vsi.presentation.console.OverrideEqualsConsolePrint;
import br.com.vsi.presentation.console.OverrideHashCodeConsolePrint;

public class Main {
    public static void main(String[] args) {
        new AnagramConsolePrint().execute();
        new OverrideEqualsConsolePrint().execute();
        new OverrideHashCodeConsolePrint().execute();
    }
}