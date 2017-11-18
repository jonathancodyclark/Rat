package com.example.jccla.rat;
import com.example.jccla.rat.Model.Model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jccla on 11/18/2017.
 */

public class pwdTests {
    private final Model model = Model.getInstance();

    @Test
    public void testPassTooLong() {
        Assert.assertEquals("Test password too long", false, model.checkPasswordCharacteristics("aaaaaaaaaaA5%aaaa"));
    }

    @Test
    public void testPassTooShort() {
        Assert.assertEquals("Test password too short", false, model.checkPasswordCharacteristics("A%4"));
    }

    @Test
    public void testPassNoCapitalLetters() {
        Assert.assertEquals("Test password has no uppercase letter", false, model.checkPasswordCharacteristics("@4upper"));
    }

    @Test
    public void testPassCapitalLetters() {
        Assert.assertEquals("Test password has at least one uppercase letter", true, model.checkPasswordCharacteristics("@4Upper"));
    }

    @Test
    public void testPassNoSpecialCharacter() {
        Assert.assertEquals("Test password doesn't have special character", false, model.checkPasswordCharacteristics("aA4aaa"));
    }

    @Test
    public void testPassHasSpecialCharacters() {
        Assert.assertEquals("Test password too short", true, model.checkPasswordCharacteristics("a%A4aaa"));
    }

    @Test
    public void testPassHasNoNumbers() {
        Assert.assertEquals("Test password too short", false, model.checkPasswordCharacteristics("a%Aaaa"));
    }

    @Test
    public void testPassHasNumbers() {
        Assert.assertEquals("Test password too short", true, model.checkPasswordCharacteristics("a%A44444"));
    }
}
