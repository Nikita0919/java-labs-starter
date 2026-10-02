package edu.course.lab01;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }


     @Test
    void returns_1_ForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(1);

        assertFalse(result);
    }
    void returns_2_ForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(2);

        assertTrue(result);
    }
    void returns_17_ForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(17);

        assertTrue(result);
    }
    void returns_49_ForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(49);

        assertFalse(result);
    }


    void returns_level_ForPalindrome() {
        boolean result = CourseToolkit.isPalindrome("level");

        assertTrue(result);
    }
    void returns_bobr_ForPalindrome() {
        boolean result = CourseToolkit.isPalindrome("bobr");

        assertFalse(result);
    }
    @Test void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.isPalindrome(null));
    }


    void returns_double_Foraverage() {
        double result = CourseToolkit.average(new int[] {2,3,4});

        boolean r = false;
        if(result==3.0){
            r=true;
        }
        assertTrue(r);
        
    }
    void returns_double__Foraverage() {
        double result = CourseToolkit.average(new int[] {-2,-3,-4});

        boolean r = false;
        if(result==-3.0){
            r=true;
        }
        assertTrue(r);
        
    }
    @Test void averageThrowsForNullOrEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.average(null));
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.average(new int[]{}));
    }
}
