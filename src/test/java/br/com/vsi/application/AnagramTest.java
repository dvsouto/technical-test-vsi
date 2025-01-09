package br.com.vsi.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnagramTest {
    private Anagram anagram;

    @BeforeEach
    void setUp() {
        anagram = new Anagram();
    }

    @Test
    void shouldBeCreateAnagram() {
        List<String> inputOne = List.of("a", "b");
        List<String> resultOne = anagram.execute(inputOne);
        List<String> expectedOne = List.of("ab", "ba");

        assertEquals(expectedOne.size(), resultOne.size());
        assertTrue(resultOne.containsAll(expectedOne));

        List<String> inputTwo = List.of("a", "b", "c");
        List<String> resultTwo = anagram.execute(inputTwo);

        List<String> expectedTwo = List.of(
                "abc",
                "acb",
                "bac",
                "bca",
                "cab",
                "cba"
        );

        assertEquals(expectedTwo.size(), resultTwo.size());
        assertTrue(resultTwo.containsAll(expectedTwo));
    }

    @Test
    void shouldThrowsExceptionWhenEmptyList() {
        List<String> input = List.of();

        assertThrows(IllegalArgumentException.class, () -> anagram.execute(input));
    }

    @Test
    void shouldThrowsExceptionWhenNotIsLetter() {
        List<String> input = List.of("a", "1", "c");

        assertThrows(IllegalArgumentException.class, () -> anagram.execute(input));
    }

    @Test
    void shouldThrowsExceptionWhenHaveManyLetters() {
        List<String> input = List.of("John", "Doe", "a");

        assertThrows(IllegalArgumentException.class, () -> anagram.execute(input));
    }
}
