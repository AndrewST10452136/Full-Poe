package com.mycompany.full.poe;

import javax.swing.JOptionPane;


public class taskFeature {
    // Static variables to store task information
    public static int taskCount = 0; // Counter for the number of tasks
    public static String[] developers = new String[100]; // Array to store developer names
    public static String[] taskNames = new String[100]; // Array to store task names
    public static String[] taskIDs = new String[100]; // Array to store task IDs
    public static int[] taskDurations = new int[100]; // Array to store task durations
    public static String[] taskStatuses = new String[100]; // Array to store task statuses
    public static StringBuilder taskDetails = new StringBuilder(); // StringBuilder to store all task details

    // Main method to run the task manager
    public static void main(String[] args) {
        // Main loop to display menu and handle user input
        while (true) {
            // Menu options
            String menu = "Choose an option:\n1. Add Task\n2. Task Management\n3. Quit";
            // Show menu and get user choice
            String choice = JOptionPane.showInputDialog(null, menu, "Task Manager", JOptionPane.INFORMATION_MESSAGE);

            // Handle user choice
            switch (choice) {
                case "1":
                    // Add tasks
                    int numTasks = getNumberOfTasks(); // Get number of tasks to add
                    for (int i = 0; i < numTasks; i++) {
                        addTask(); // Add each task
                    }
                    break;
                case "2":
                    // Task management
                    while (true) {
                        // Sub-menu for task management options
                        String subMenu = "Choose an option:\n1. Display Done Tasks\n2. Display Longest Task\n3. Search Task by Name\n4. Delete Task by Name\n5. Back";
                        // Show sub-menu and get user choice
                        String subChoice = JOptionPane.showInputDialog(null, subMenu, "Task Manager", JOptionPane.INFORMATION_MESSAGE);

                        // Handle sub-menu choice
                        switch (subChoice) {
                            case "1":
                                displayDoneTasks(); // Display tasks marked as done
                                break;
                            case "2":
                                displayLongestTask(); // Display the task with the longest duration
                                break;
                            case "3":
                                searchTaskByName(); // Search for a task by name
                                break;
                            case "4":
                                deleteTaskByName(); // Delete a task by name
                                break;
                            case "5":
                                // Exit sub-menu
                                break;
                            default:
                                // Inform user of invalid option
                                JOptionPane.showMessageDialog(null, "Invalid option: Please try again.");
                                break;
                        }

                        // Check if user wants to exit sub-menu
                        if (subChoice.equals("5")) {
                            break;
                        }
                    }
                    break;
                case "3":
                    // Quit application
                    JOptionPane.showMessageDialog(null, "Thank you for visiting EasyKanban");
                    System.exit(0); // Terminate the program
                    break;
                default:
                    // Inform user of invalid main menu option
                    JOptionPane.showMessageDialog(null, "Invalid option: Please try again.");
                    break;
            }
        }
    }

    // Method to get the number of tasks to add from the user
    public static int getNumberOfTasks() {
        // Prompt user for number of tasks
        String input = JOptionPane.showInputDialog("Enter the number of tasks to add:");
        try {
            // Parse input to integer
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Inform user of invalid input and retry
            JOptionPane.showMessageDialog(null, "Invalid option: Please enter a number.");
            return getNumberOfTasks(); // Recursive call to retry
        }
    }

    // Method to add a task with details
    public static void addTask() {
        // Get task name from user
        String taskName = JOptionPane.showInputDialog("Enter the task name:");
        String taskDescription;
        // Loop to validate task description length
        while (!(taskDescription = getTaskDescription()).isEmpty() && !checkTaskDescription(taskDescription)) {
            // Inform user if description is too long
            JOptionPane.showMessageDialog(null, "Task description should not exceed 50 characters.");
        }
        // Get developer details
        String developerDetails = getDeveloperDetails();
        // Get task duration
        int taskDuration = getTaskDuration();
        // Create task ID based on name and developer details
        String taskID = createTaskID(taskName, developerDetails);
        // Get task status
        String taskStatus = getTaskStatus();

        // Compile task detail string
        String taskDetail = "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Duration: " + taskDuration + " hours\n" +
                "Task ID: " + taskID + "\n" +
                "Task Status: " + taskStatus;

        // Append task detail to StringBuilder
        taskDetails.append(taskDetail).append("\n\n");

        // Store task details in arrays
        taskNames[taskCount] = taskName;
        developers[taskCount] = developerDetails;
        taskIDs[taskCount] = taskID;
        taskDurations[taskCount] = taskDuration;
        taskStatuses[taskCount] = taskStatus;

        // Increment task count
        taskCount++;

        // Display added task details to user
        JOptionPane.showMessageDialog(null, "Task Added:\n" + taskDetail);
    }

    // Method to get task description from user
    public static String getTaskDescription() {
        // Prompt for task description with character limit
        return JOptionPane.showInputDialog("Enter the task description (50 characters max):");
    }

    // Method to check if task description is valid
    public static boolean checkTaskDescription(String description) {
        // Return true if description is within character limit
        return description.length() <= 50;
    }

    // Method to get developer details from user
    public static String getDeveloperDetails() {
        // Prompt for developer's first name
        String firstName = JOptionPane.showInputDialog("Enter the developer's first name:");
        // Prompt for developer's last name
        String lastName = JOptionPane.showInputDialog("Enter the developer's last name:");
        // Return full name
        return firstName + " " + lastName;
    }

    // Method to get task duration from user
    public static int getTaskDuration() {
        while (true) {
            try {
                // Prompt for task duration in hours
                String input = JOptionPane.showInputDialog("Enter the task duration in hours:");
                // Parse input to integer
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Inform user of error if input is not an integer
                JOptionPane.showMessageDialog(null, "Invalid option: Please enter an integer.");
            } catch (NullPointerException e) {
                // Inform user of error if input field is left empty
                JOptionPane.showMessageDialog(null, "Invalid option: Please do not leave this field empty.");
            }
        }
    }

    // Method to create a unique task ID
    public static String createTaskID(String taskName, String developerDetails) {
        // Initialize StringBuilder for ID
        StringBuilder id = new StringBuilder();
        // Append first two characters of task name in uppercase
        id.append(taskName.substring(0, Math.min(2, taskName.length())).toUpperCase());
        id.append(":");
        // Append full task name in uppercase
        id.append(taskName.toUpperCase());
        id.append(":");
        // Append last three characters of developer details in uppercase
        id.append(developerDetails.substring(Math.max(0, developerDetails.length() - 3)).toUpperCase());
        // Return the constructed ID
        return id.toString();
    }

     // Method to get task status from user
    public static String getTaskStatus() {
    while (true) {
        //Prompting the user to enter a task number 
        String statusChoice = JOptionPane.showInputDialog(null, "Enter the task status number:\n1. To Do\n2. Done\n3. Doing", "Task Status",
                JOptionPane.INFORMATION_MESSAGE);
        if (statusChoice == null) {
            return null; // Return null if user cancels the input
        }
        switch (statusChoice) {
            case "1":
                return "To Do"; // Return "To Do" if user selects option 1
            case "2":
                return "Done"; // Return "Done" if user selects option 2
            case "3":
                return "Doing"; // Return "Doing" if user selects option 3
            default:
                JOptionPane.showMessageDialog(null, "Invalid option: Please try again."); // Show error message for invalid input
        }
      }
    }


    // Display tasks that are marked as 'Done'
    public static void displayDoneTasks() {
        // Initialize a StringBuilder to concatenate strings efficiently
        StringBuilder doneTasks = new StringBuilder();
        // Iterate over all tasks
        for (int i = 0; i < taskCount; i++) {
            // Check if the current task status is 'Done'
            if (taskStatuses[i].equals("Done")) {
                // Append the developer, task name, and duration to the StringBuilder
                doneTasks.append("Developer: ").append(developers[i]).append("\n");
                doneTasks.append("Task Name: ").append(taskNames[i]).append("\n");
                doneTasks.append("Duration: ").append(taskDurations[i]).append(" hours\n\n");
            }
        }
        // Display the concatenated string of done tasks in a message dialog
        JOptionPane.showMessageDialog(null, doneTasks.toString());
    }

    // Display the task with the longest duration
    public static void displayLongestTask() {
        // Variable to store the maximum duration found
        int maxDuration = -1;
        // Variable to store the developer of the longest task
        String longestTaskDeveloper = "";
        // Iterate over all tasks
        for (int i = 0; i < taskCount; i++) {
            // Check if the current task duration is greater than the maxDuration
            if (taskDurations[i] > maxDuration) {
                // Update maxDuration and the developer of the longest task
                maxDuration = taskDurations[i];
                longestTaskDeveloper = developers[i];
            }
        }
        // Display the developer and duration of the longest task in a message dialog
        JOptionPane.showMessageDialog(null, "Developer with Longest Task: " + longestTaskDeveloper + "\nLongest Task Duration: " + maxDuration + " hours");
    }

    // Search for a task by its name
    public static void searchTaskByName() {
        // Prompt the user to enter a task name for searching
        String taskName = JOptionPane.showInputDialog("Enter the task name to search:");
        // Iterate over all tasks
        for (int i = 0; i < taskCount; i++) {
            // Check if the current task name matches the user input (case-insensitive)
            if (taskNames[i].equalsIgnoreCase(taskName)) {
                // Display the task details in a message dialog and exit the method
                JOptionPane.showMessageDialog(null, "Task Name: " + taskNames[i] + "\nDeveloper: " + developers[i] + "\nTask Status: " + taskStatuses[i]);
                return;
            }
        }
        // If the task is not found, inform the user
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Delete a task by its name
    public static void deleteTaskByName() {
        // Prompt the user to enter a task name for deletion
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:");
        // Iterate over all tasks
        for (int i = 0; i < taskCount; i++) {
            // Check if the current task name matches the user input (case-insensitive)
            if (taskNames[i].equalsIgnoreCase(taskName)) {
                // Shift all subsequent tasks up in the arrays to overwrite the deleted task
                for (int j = i; j < taskCount - 1; j++) {
                    developers[j] = developers[j + 1];
                    taskNames[j] = taskNames[j + 1];
                    taskIDs[j] = taskIDs[j + 1];
                    taskDurations[j] = taskDurations[j + 1];
                    taskStatuses[j] = taskStatuses[j + 1];
                }
                // Decrement the task count to reflect the deletion
                taskCount--;
                // Inform the user of successful deletion
                JOptionPane.showMessageDialog(null, "Task deleted successfully.");
                return;
            }
        }
        // If the task is not found, inform the user
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Display a report of all tasks
    public static void displayTaskReport() {
        // Display the task details in a message dialog
        JOptionPane.showMessageDialog(null, taskDetails.toString());
    }
}
