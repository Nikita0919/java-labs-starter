package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


class CourseToolkitTest{
    @Test
    void StartBalance(){
    BankAccount balance1 = new BankAccount(5000);

    balance1.withdraw(1500);
    
    assertEquals(3500,balance1.getBalance());

    }

    @Test
    void StartBalanceThrow(){
    BankAccount balance2 = new BankAccount(1000);

    assertThrows(IllegalArgumentException.class, () -> balance2.withdraw(1500));
    assertEquals(1000,balance2.getBalance());
    }


}