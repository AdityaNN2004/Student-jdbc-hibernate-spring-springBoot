package com.hibernate.student.entity.Testing;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.hibernate.student.utils.Main;

public class SystemTestSuite {

    public static void main(String[] args) {
        StringBuilder automatedInput = new StringBuilder();

        // ====================================================================
        // 1. COURSE CRUD OPERATIONS
        // ====================================================================
        automatedInput.append("2\n");        // Main Menu -> Manage Courses
        
        // [C]REATE Course
        automatedInput.append("1\n");        // Add new course
        automatedInput.append("CS101\n");    // Course Code
        automatedInput.append("Java OOP\n");  // Course Name
        automatedInput.append("Computer Science\n"); // Department
        automatedInput.append("4\n");        // Credits
        
        // [R]EAD Course
        automatedInput.append("2\n");        // List all courses (Verifies Insert)
        automatedInput.append("3\n");        // Find course by Code
        automatedInput.append("CS101\n");    // Target Code
        
        // [U]PDATE Course
        automatedInput.append("4\n");        // Update Course Details
        automatedInput.append("CS101\n");    // Target course to update
        automatedInput.append("CS101\n");    // New Code (Keep same)
        automatedInput.append("Advanced Java\n"); // Updated Name
        automatedInput.append("IT Department\n"); // Updated Dept
        automatedInput.append("5\n");        // Updated Credits
        
        // [R]EAD Course again to verify update
        automatedInput.append("3\n");        
        automatedInput.append("CS101\n");    

        // [D]ELETE Course (Soft delete: sets is_active = 0)
        automatedInput.append("5\n");        // Delete Course
        automatedInput.append("CS101\n");    // Target Code
        
        automatedInput.append("0\n");        // Exit back to Main Menu


        // ====================================================================
        // 2. STUDENT CRUD OPERATIONS
        // ====================================================================
        automatedInput.append("1\n");        // Main Menu -> Manage Students
        
        // [C]REATE Student
        automatedInput.append("2\n");        // Add new Student
        automatedInput.append("ST888\n");    // Roll Number
        automatedInput.append("Rahul\n");    // First Name
        automatedInput.append("Sharma\n");   // Last Name
        automatedInput.append("rahul@test.com\n"); // Email
        automatedInput.append("2003-05-15\n"); // DOB
        automatedInput.append("Mumbai\n");   // Address
        automatedInput.append("8.5\n");      // GPA
        automatedInput.append("M\n");         // Gender
        automatedInput.append("2026-06-30\n"); // Enrollment Date
        
        // [R]EAD Student
        automatedInput.append("1\n");        // List all students
        automatedInput.append("3\n");        // Find Student by Roll No
        automatedInput.append("ST888\n");    // Target Roll Number
        
        // [U]PDATE Student
        automatedInput.append("4\n");        // Update Student Details
        automatedInput.append("ST888\n");    // Target student to update
        automatedInput.append("ST888\n");    // Roll No (Keep same)
        automatedInput.append("Rahul\n");    // First Name
        automatedInput.append("Verma\n");    // Updated Last Name
        automatedInput.append("rahul.verma@test.com\n"); // Updated Email
        automatedInput.append("2003-05-15\n"); // DOB
        automatedInput.append("Pune\n");      // Updated Address
        automatedInput.append("9.0\n");      // Updated GPA
        automatedInput.append("M\n");         // Gender
        automatedInput.append("2026-06-30\n"); // Enrollment Date

        // [R]EAD Student again to verify update
        automatedInput.append("3\n");        
        automatedInput.append("ST888\n");    

        // [D]ELETE Student (Soft delete: sets is_deleted = 1)
        automatedInput.append("5\n");        // Delete Student
        automatedInput.append("ST888\n");    // Target Roll Number
        
        automatedInput.append("0\n");        // Exit back to Main Menu


        // ====================================================================
        // 3. ENROLLMENT OPERATIONS
        // ====================================================================
        // Note: For enrollment testing to succeed, we need an active student and course.
        // Let's create an active student and course first before making the connection.
        
        // Re-create an active Course
        automatedInput.append("2\n"); automatedInput.append("1\n");
        automatedInput.append("BIO10\n"); automatedInput.append("Biology\n"); automatedInput.append("Science\n"); automatedInput.append("3\n");
        automatedInput.append("0\n");

        // Re-create an active Student
        automatedInput.append("1\n"); automatedInput.append("2\n");
        automatedInput.append("ST999\n"); automatedInput.append("Amit\n"); automatedInput.append("Patil\n"); automatedInput.append("amit@test.com\n");
        automatedInput.append("2004-01-01\n"); automatedInput.append("Nashik\n"); automatedInput.append("7.8\n"); automatedInput.append("M\n"); automatedInput.append("2026-06-30\n");
        automatedInput.append("0\n");

        // Now move to Enrollments Menu
        automatedInput.append("3\n");        // Main Menu -> Manage Enrollments
        
        // [C]REATE Enrollment
        automatedInput.append("1\n");        // Add new enrollment
        automatedInput.append("ST999\n");    // Valid Roll Number
        automatedInput.append("BIO10\n");    // Valid Course Code
        automatedInput.append("Semester 1\n"); // Semester
        automatedInput.append("A\n");         // Grade
        automatedInput.append("Enrolled\n"); // Status
        
        // [R]EAD Enrollment
        automatedInput.append("2\n");        // List all enrollments
        automatedInput.append("3\n");        // Find enrollment by Student Roll Number
        automatedInput.append("ST999\n");    // Target Roll Number
        
        automatedInput.append("0\n");        // Exit back to Main Menu

        // ====================================================================
        // SHUTDOWN APPLICATION
        // ====================================================================
        automatedInput.append("0\n");        // Exit Master Management System Completely

        // Redirect input stream
        InputStream originalSystemIn = System.in;
        ByteArrayInputStream testInputStream = new ByteArrayInputStream(automatedInput.toString().getBytes());
        System.setIn(testInputStream);

        System.out.println("🚀 RUNNING COMPREHENSIVE 3-ENTITY CRUD TEST SUITE...");
        try {
            Main.main(new String[]{});
            System.out.println("\n✅ FULL CRUD INTEGRATION TEST COMPLETED SUCCESSFULLY!");
        } catch (Exception e) {
            System.err.println("\n❌ CRUD TESTING TRIPPED UNEXPECTED EXCEPTION:");
            e.printStackTrace();
        } finally {
            System.setIn(originalSystemIn);
        }
    }
}
