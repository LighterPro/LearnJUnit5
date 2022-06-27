package com.realestateapp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class ApartmentRaterTest {

    @Nested
    class rateApartmentTests {

        @ParameterizedTest
            // -- write a parameterized test with different values
        void should_ReturnCorrectRating_When_CorrectApartment() {
            //given

            //when

            //then
        }

        @Test
        void should_ReturnErrorValue_When_IncorrectApartment() {
            //given

            //when

            //then
        }

        @Test
        void should_CalculateAverageRating_When_CorrectApartmentList() {
            //given

            //when

            //then
        }

        @Test
        void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
            //given

            //when

            //then
        }
    }


}