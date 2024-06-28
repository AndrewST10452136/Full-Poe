package com.mycompany.full.poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

// This class contains unit tests for the taskFeature class
public class taskFeatureTest {
    
    // Instance of taskFeature to be used in the tests
    private taskFeature taskManager;

    // Set up the taskManager object and populate it with test data before each test
    @BeforeEach
    void setUp() {
        taskManager = new taskFeature();
        // Populate the arrays with test data
        taskManager.developers = new String[]{"Mike Smith", "Edward Harrinton", "Samantha Paulson", "Glenda Oberholzer"};
        taskManager.taskNames = new String[]{"Create Login", "Create Reports", "Fix Bugs", "Implement Feature"};
        taskManager.taskDurations = new int[]{5, 11, 3, 8};
        taskManager.taskCount = 4;
    }

    // Test to ensure the developers array is correctly populated
    @Test
    @DisplayName("Developer array correctly populated")
    void testDeveloperArrayPopulated() {
        assertArrayEquals(new String[]{"Mike Smith", "Edward Harrinton", "Samantha Paulson", "Glenda Oberholzer"}, taskManager.developers);
    }

    // Test to display the developer and duration for the task with the longest duration
    @Test
    @DisplayName("Display developer and duration for task with longest duration")
    void testDisplayLongestTask() {
        String longestTaskDeveloper = "";
        int maxDuration = -1;
        // Loop through the tasks to find the one with the longest duration
        for (int i = 0; i < taskManager.taskCount; i++) {
            if (taskManager.taskDurations[i] > maxDuration) {
                maxDuration = taskManager.taskDurations[i];
                longestTaskDeveloper = taskManager.developers[i];
            }
        }
        // Assert that the longest task and its developer are as expected
        assertEquals("Edward Harrinton", longestTaskDeveloper);
        assertEquals(11, maxDuration);
    }

    // Test to search for a specific task by name
    @Test
    @DisplayName("Search for tasks")
    void testSearchForTasks() {
        // Loop through the tasks to find the specified task
        for (int i = 0; i < taskManager.taskCount; i++) {
            if (taskManager.taskNames[i].equalsIgnoreCase("Create Login")) {
                // Assert that the task and its developer are as expected
                assertEquals("Mike Smith", taskManager.developers[i]);
                assertEquals("Create Login", taskManager.taskNames[i]);
                return;
            }
        }
        // Fail the test if the task is not found
        fail("Task not found");
    }

    // Test to search all tasks assigned to a specific developer
    @Test
    @DisplayName("Search all tasks assigned to developer")
    void testSearchTasksAssignedToDeveloper() {
        boolean found = false;
        // Loop through the tasks to find those assigned to the specified developer
        for (int i = 0; i < taskManager.taskCount; i++) {
            if (taskManager.developers[i].equalsIgnoreCase("Edward Harrinton")) {
                // Assert that the task name is as expected
                assertEquals("Create Reports", taskManager.taskNames[i]);
                found = true;
                break;
            }
        }
        // Assert that at least one task was found for the developer
        assertTrue(found);
    }

    // Test to delete a task from the array
    @Test
    @DisplayName("Delete task from array")
    void testDeleteTaskFromArray() {
        int initialCount = taskManager.taskCount;
        // Loop through the tasks to find and delete the specified task
        for (int i = 0; i < taskManager.taskCount; i++) {
            if (taskManager.taskNames[i].equalsIgnoreCase("Create Reports")) {
                // Shift the remaining tasks in the array to fill the gap
                for (int j = i; j < taskManager.taskCount - 1; j++) {
                    taskManager.developers[j] = taskManager.developers[j + 1];
                    taskManager.taskNames[j] = taskManager.taskNames[j + 1];
                    taskManager.taskDurations[j] = taskManager.taskDurations[j + 1];
                }
                // Decrease the task count to reflect the deletion
                taskManager.taskCount--;
                // Assert that the task count has decreased and the task is no longer in the array
                assertEquals(initialCount - 1, taskManager.taskCount);
                for (int j = 0; j < taskManager.taskCount; j++) {
                    assertNotEquals("Create Reports", taskManager.taskNames[j]);
                }
                return;
            }
        }
        // Fail the test if the task is not found
        fail("Task not found");
    }
}
