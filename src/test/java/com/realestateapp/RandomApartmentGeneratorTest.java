package com.realestateapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomApartmentGeneratorTest {

    @Test
    void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
        //given
        RandomApartmentGenerator randomApartmentGenerator = new RandomApartmentGenerator();
        //when
        Apartment actualApartment = randomApartmentGenerator.generate();
        //then
        assertAll(
                () -> assertEquals(1, actualApartment.getArea())
        );
    }

    @Test
    void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
        //given

        //when

        //then
    }
}