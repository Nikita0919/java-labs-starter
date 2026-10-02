package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
    public static boolean isPrime(int number){
        if(number<2){
            return false;
        }
        int res =0;
        for(int i =2;i<=number/2;i++){
            if(number%i==0){
                res++;
            }
        }
        if(res!=0){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static boolean isPalindrome(String text){
        if (text == null)
            throw new IllegalArgumentException("");
        int left = 0;
        int right = text.length() - 1;
        int res = 0;
        
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                res+=1;
            }
            left++;
            right--;
        }
        if(res==0){
            return true;
        }
        else{
            return false;
        }
    }
    public static double average(int[] values){
        if (values == null || values.length==0)
            throw new IllegalArgumentException("");
        double res = 0;
        for(int i =0; i<values.length;i++){
            res += values[i];
        }
        res/=values.length;
        return res;
    }
}
