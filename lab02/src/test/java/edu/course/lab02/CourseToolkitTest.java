package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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





class ProjectTaskTest {


    @Test
    void createValidTask() {
        ProjectTask t = new ProjectTask("T-1", "Отчёт", 8);
        assertEquals("T-1", t.getId());
        assertEquals("Отчёт", t.getTitle());
        assertEquals(8, t.getEstimatedHours());
        assertEquals(TaskStatus.NEW, t.getStatus());
        assertFalse(t.isCompleted());
    }

    @Test
    void blankIdThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("  ", "Отчёт", 8));
    }

    @Test
    void nullIdThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask(null, "Отчёт", 8));
    }

    @Test
    void blankTitleThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", "", 8));
    }

    @Test
    void nonPositiveEstimateThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", "Отчёт", 0));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", "Отчёт", -1));
    }

    
    @Test
    void changeStatusUpdatesState() {
        ProjectTask t = new ProjectTask("T-1", "Отчёт", 8);
        t.changeStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, t.getStatus());
    }

    @Test
    void changeStatusToNullThrows() {
        ProjectTask t = new ProjectTask("T-1", "Отчёт", 8);
        assertThrows(IllegalArgumentException.class, () -> t.changeStatus(null));
    }

    @Test
    void completedWhenStatusDone() {
        ProjectTask t = new ProjectTask("T-1", "Отчёт", 8);
        t.changeStatus(TaskStatus.DONE);
        assertTrue(t.isCompleted());
    }

    @Test
    void increaseEstimateAddsHours() {
        ProjectTask t = new ProjectTask("T-1", "Отчёт", 8);
        t.increaseEstimate(4);
        assertEquals(12, t.getEstimatedHours());
    }

    @Test
    void increaseEstimateNonPositiveThrows() {
        ProjectTask t = new ProjectTask("T-1", "Отчёт", 8);
        assertThrows(IllegalArgumentException.class, () -> t.increaseEstimate(0));
        assertThrows(IllegalArgumentException.class, () -> t.increaseEstimate(-3));
    }

    
}
}