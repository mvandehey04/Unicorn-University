package edu.cs2430.assignment3;

public class Student{
    // the student id
    private int id;
    // a constant to represent the max size of the schedule array (5)
    private static final int MAX_SCHEDULES = 5;
    // the student name
    private String name;
    // variable to hold the next possible student id to assign
    private static int nextStudentId = 1;
    // the current index for the array of schedules
    private int scheduleIndex;
    // the array of possible schedules for the student
    private Schedule[] schedules = new Schedule[MAX_SCHEDULES];

    /**
     * Constructs a student with the given name.
     * Assigns the id of the student to the next available id from nextStudentId
     * Initializes the array of schedules to an empty array of size MAX_SCHEDULES
     * @param name
     */
    public Student(String name){
        this.name = name;
        id = nextStudentId;
        nextStudentId ++;
    }

    //
    // getters and setters
    //

    /**
     * @return student id
     */
    public int getId(){
        return id;
    }

    /**
     * @return name of the student
     */
    public String getName(){
        return name;
    }

    /**
     * @return schedule array of the student
     */
    public Schedule[] getSchedules(){
        return schedules;
    }

    /**
     * @param id - new student id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * @param name - new student name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @param schedules - new schedule array
     */
    public void setSchedules(Schedule[] schedules){
        for(int i = 0; i < scheduleIndex; i++){
            this.schedules[i] = schedules[i];
        }

    }

}
