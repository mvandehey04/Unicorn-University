package edu.cs2430.assignment3;

public class Course{
    // the current index for the array of prerequisite courses
    private int currentPrerequisiteIndex = 0;

    //a constant to represent the max (5) of prerequisite course array
    private static final int MAX_PREREQUISITES = 5;

    // course name, ex: Object-Oriented Programming
    private String name;

    // course number, ex: 2430
    private int number;

    // number of credits for course, ex: 3
    private int numberOfCredits;

    // an array of prerequisite courses
    private Course[] prerequisiteCourses = new Course[MAX_PREREQUISITES];

    // the program the course belongs to, ex: COMPUTER_SCIENCE
    private Program program;

    //
    // constructors
    //
    public Course(String name, int number, Program program, int numberOfCredits){
        this.name = name;
        this.number = number;
        this.program = program;
        this.numberOfCredits = numberOfCredits;

    }
    public Course(String name, int number, Program program, int numberOfCredits, Course[] prerequisiteCourses){
        this.name = name;
        this.number = number;
        this.program = program;
        this.numberOfCredits = numberOfCredits;
        for(int i = 0; i < prerequisiteCourses.length; i++){
            if(prerequisiteCourses[i] != null){
                this.prerequisiteCourses[currentPrerequisiteIndex] = prerequisiteCourses[i];
                currentPrerequisiteIndex ++;
            }
        }
    }

    //
    // setters and getters
    //
    public String getName(){
        return name;
    }
    public int getNumber(){
        return number;
    }
    public int getNumberOfCredits(){
        return numberOfCredits;
    }
    public Course[] getPrerequisiteCourses(){
        return prerequisiteCourses;
    }
    public Program getProgram(){
        return program;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public void setNumberOfCredits(int numberOfCredits){
        this.numberOfCredits = numberOfCredits;
    }
    public void setProgram(Program program){
       this.program = program;
    }

    //
    // class methods
    //
    /**
     * Bonus Challenge
     * Checks to see if the prerequisite course creates a cycle with any of the other prerequisite courses or the current course.
     * @param prerequisiteCourse
     * @return true if the prerequisite course is valid to add, false if the prerequisite course is not valid to add
     */
    public boolean containsPrerequisiteCycle(Course prerequisiteCourse){
        return false;
    }


    /**
     * Checks to see if the prerequisite course already exists in the prerequisite course array
     *
     * @param prerequisiteCourse - the prerequisite course
     * @return true if the prerequisite course already exists in the array, false if the prerequisite course does not already exist in the array
     */
    public boolean containsPrerequisite(Course prerequisiteCourse){
        boolean containsPrerequisite = false;

        for(int i = 0; i < currentPrerequisiteIndex; i++){
            if(prerequisiteCourses[i].getName().equals(prerequisiteCourse.getName()) && prerequisiteCourses[i].getProgram() == prerequisiteCourse.getProgram()){
                containsPrerequisite = true;
            }
        }

        return containsPrerequisite;
    }

    /**
     * Adds the provided course to the prerequisite array.
     * The prerequisite course must be a valid prerequisite course in order to be added.
     * The function validateAddPrerequisiteCourse(edu.cs2430.assignment3.Course) determines if the prerequisite course is valid.
     *
     * @param prerequisiteCourse
     * @return returns true if the prerequisite course was added, false if the prerequisite course was not added
     */
    public boolean addPrerequisiteCourse(Course prerequisiteCourse){
        boolean successfullyAdded = false;

        if(validateAddPrerequisiteCourse(prerequisiteCourse)){
            prerequisiteCourses[currentPrerequisiteIndex] = prerequisiteCourse;
            currentPrerequisiteIndex ++;
            successfullyAdded = true;
        }

        return successfullyAdded;
    }

    /**
     * Validates if the prerequisite course can be added to the current course.
     * The prerequisite course cannot be null
     * The prerequisite course cannot have the same course name and program as the current course
     * The current course has fewer than five current prerequisite courses
     * The current course does not already have the course (same name and program) as a prerequisite
     *
     * @param prerequisiteCourse
     * @return true if the prerequisite course is valid to add, false if the prerequisite course is not valid to add
     */
    private boolean validateAddPrerequisiteCourse(Course prerequisiteCourse){
        boolean isValid = false;

        // to check if prerequisite is a valid course:
        // there is room for another prerequisite course, the prerequisite course is not null,
        // the name is not the same as the course, and the program is also not the same
        if(prerequisiteCourse != null && prerequisiteCourse.getName() != null && prerequisiteCourse.getProgram() != null) {
            if((!(prerequisiteCourse.getName().equals(name))
                    && (prerequisiteCourse.getProgram() != program)) && (currentPrerequisiteIndex < MAX_PREREQUISITES)
                    && !containsPrerequisite(prerequisiteCourse)){
                isValid = true;

            }

        }

        return isValid;

    }

}
