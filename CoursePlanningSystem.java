package edu.cs2430.assignment3;
import java.util.UUID;

public class CoursePlanningSystem{
    // a constant to represent the maximum size of all three arrays (students, courses, and schedules)
    private static final int ARRAY_MAX = 1000;

    // the current index for the array of courses
    private int courseIndex;

    // an array to store the courses in the system
    private Course[] courses = new Course[ARRAY_MAX];

    // the current index for the array of course
    private int scheduleIndex;

    // an array to store the schedules in the system
    private Schedule[] schedules = new Schedule[ARRAY_MAX];

    // the current index for the array of students
    private int studentIndex;

    // an array to store the students in the system
    private Student[] students = new Student[ARRAY_MAX];


    // default constructor, initializes all array indices to zero and initializes all arrays based on ARRAY_MAX
    public CoursePlanningSystem(){

    }

    //
    // getters and setters
    //

    /**
     * Searches the array of courses for the course with the given course number and program and returns the course
     *
     * @param courseNumber - the course number
     * @param program - the program the course belongs to
     * @return the course or null if the course could not be found
     */
    public Course getCourse(int courseNumber, Program program){
        Course course = null;

        for(int i = 0; i < courseIndex; i++){
            if(courses[i].getNumber() == courseNumber && courses[i].getProgram().equals(program)){
                course = courses[i];
            }
        }

        return course;
    }

    /**
     * @return the array of courses
     */
    public Course[] getCourses(){
        return courses;
    }

    /**
     * Searches the array of schedule and returns the schedule specified by the uuid
     *
     * @param uuid - the uuid of the schedule
     * @return the schedule or null if the schedule could not be found
     */
    public Schedule getScheduleByUuid(UUID uuid){
        Schedule schedule = null;

        for(int i = 0; i < ARRAY_MAX; i++){
            if(schedules[i].getUuid() == uuid){
                schedule = schedules[i];
            }
        }

        return schedule;
    }

    /**
     * Searches the schedule array and returns all schedules for the student specified by the student id.
     *
     * @param studentId - the student id
     * @return an array of all schedules the student has
     */
    public Schedule[] getSchedulesByStudentId(int studentId){
        int size = 0;

        for(int i = 0; i < scheduleIndex; i++){
            if(schedules[i].getStudentId() == studentId){
                size++;
            }
        }

        Schedule[] studentSchedules = new Schedule[size];
        int studentScheduleIndex = -1;

        for (int i = 0; i < scheduleIndex; i++) {
            if (schedules[i].getStudentId() == studentId) {
                studentScheduleIndex++;
                studentSchedules[studentScheduleIndex] = schedules[i];
            }
        }

        return studentSchedules;
    }

    /**
     * @param studentId
     * @return the student or null if the student could not be found
     */
    public Student getStudent(int studentId){
        Student student = null;

        for(int i = 0; i < studentIndex; i++){
            if(students[i].getId() == studentId){
                student = students[i];
            }
        }
        return student;
    }

    /**
     * @return the array of students
     */
    public Student[] getStudents(){
        return students;
    }

    //
    // class methods
    //
    /**
     * Adds a course to the end of the course array.
     * If the array cannot store any more courses, then the course is not added and "false" is returned.
     *
     * @param course - the course to be added
     * @return true if the course was added, false if the course could not be added
     */
    public boolean addCourse(Course course){
        boolean courseAdded = false;

        if(courseIndex < ARRAY_MAX && course != null){
            courses[courseIndex] = course;
            courseIndex++;
            courseAdded = true;
        }

        return courseAdded;
    }

    /**
     * Adds a schedule to the end of the schedule array.
     * If the array cannot store any more schedules then the schedules then the schedule is not added and "false" is returned.
     *
     * @param schedule - the schedule to be added
     * @return true if the schedule could be added, false if the schedule could not be added
     */
    public boolean addSchedule(Schedule schedule){
        boolean scheduleAdded = false;

        if(scheduleIndex < ARRAY_MAX){
            schedules[scheduleIndex] = schedule;
            scheduleIndex ++;
            scheduleAdded = true;
        }

        return scheduleAdded;
    }

    /**
     * Adds a schedule entry to the schedule specified by the schedule uuid.
     * If the array cannot store any more schedule entries then the schedule entry is not added and "false" is returned.
     *
     * @param scheduleUuid - the uuid of the schedule
     * @param scheduleEntry - the schedule entry to add to the schedule
     * @return true if the schedule entry could be added, false if the schedule entry could not be added
     */
    public boolean addScheduleEntryByScheduleUuid(UUID scheduleUuid, ScheduleEntry scheduleEntry){
        boolean added = false;
        for(int i=0; i<scheduleIndex; i++){
            if(schedules[i].getUuid().equals(scheduleUuid)){
                added = schedules[i].addScheduleEntry(scheduleEntry);
            }
        }
        return added;
    }

    /**
     * Adds a student to the end of the student array. If the array cannot store any more students, then the student is not added and "false" is returned.
     * @param student
     * @return true if the student could be added, false if the student could not be found
     */
    public boolean addStudent(Student student){
        boolean studentAdded = false;

        if(studentIndex < ARRAY_MAX){
            students[studentIndex] = student;
            studentIndex ++;
            studentAdded = true;
        }

        return studentAdded;
    }

    /**
     * Attempts to remove the course (specified by the course number) from the array.
     * If the course could not be found, "false" is returned
     * If there are any schedules referencing the course, those schedules are not affected (for assignment simplicity)
     *
     * @param courseNumber - the course number
     * @param program - the program the course is in
     * @return true if the course was removed, false if the course was not removed
     */
    public boolean removeCourse(int courseNumber, Program program){
        boolean courseRemoved = false;
        int indexFound = -1;

        for(int i = 0; i < ARRAY_MAX; i++){
            if(courses[i].getNumber() == courseNumber && courses[i].getProgram() == program){
                indexFound = i;
                courseRemoved = true;
            }
        }

        if(courseRemoved){
            for(int i = indexFound; i < ARRAY_MAX; i++){
                if((i + 1) < courses.length){
                    courses[i] = courses[i + 1];
                }
            }
        }

        return courseRemoved;
    }

    /**
     * Removes a course (specified by the index) from the course array.
     * Shifts all other courses down in the array such that there are no null elements in the middle.
     * If there are any schedules for the course, those schedules are not affected (for assignment simplicity)
     *
     * @param index - the index of the course to remove
     */
    private void removeCourseEntry(int index){
        for(int i = index; i < ARRAY_MAX; i++){
            if((i + 1) < courses.length){
                courses[i] = courses[i + 1];
            }
        }
    }


    /**
     * Removes a schedule (specified by the index) from the schedule array.
     * Shifts all other schedules down in the array such that there are no null elements in the middle.
     *
     * @param index - the index of the schedule to remove
     */
    private void removeSchedule(int index){
        for(int i = index; i < ARRAY_MAX; i++){
            if((i + 1) < schedules.length){
                schedules[i] = schedules[i + 1];
            }
        }
    }

    /**
     * Attempts to remove the schedule (specified by the schedule UUID) from the array.
     * Shifts all other schedules down in the array such that there are no null elements in the middle.
     * If the schedule could not be found, "false" is returned
     *
     * @param uuid - the unique identifier for the schedule
     * @return
     */
    public boolean removeSchedule(UUID uuid){
        boolean scheduleRemoved = false;
        int indexFound = -1;

        for(int i = 0; i < ARRAY_MAX; i++){
            if(schedules[i].getUuid() == uuid){
                indexFound = i;
                scheduleRemoved = true;
            }
        }

        if(scheduleRemoved){
            for(int i = indexFound; i < ARRAY_MAX; i++){
                if((i + 1) < schedules.length){
                    schedules[i] = schedules[i + 1];
                }
            }
        }

        return scheduleRemoved;
    }

    /**
     * Removes the schedule entry from the array.
     * Searches for the schedule using the scheduleUuid and attempts to remove the schedule entry from the schedule.
     *
     * @param scheduleUuid - the uuid of the schedule
     * @param scheduleEntry - the schedule entry to remove from the schedule
     * @return true if the schedule entry was removed from the schedule, false if the schedule entry was not removed from the schedule
     */
    public boolean removeScheduleEntryByScheduleUuid(UUID scheduleUuid, ScheduleEntry scheduleEntry){
        boolean scheduleRemoved = false;
        int indexFound = -1;

        for(int i = 0; i < ARRAY_MAX; i++){
            if(schedules[i].getUuid() == scheduleUuid){
                indexFound = i;
                scheduleRemoved = true;
            }
        }

        if(scheduleRemoved){
            for(int i = indexFound; i < ARRAY_MAX; i++){
                if((i + 1) < courses.length){
                    schedules[i] = schedules[i + 1];
                }
            }
        }


        return scheduleRemoved;
    }

    /**
     * Attempts to remove the student (specified by the studentId) from the array
     * If the student could not be found, "false" is returned
     * If there are any schedules for the student, those schedules are not affected (for assignment simplicity)
     *
     * @param studentId - the student id
     * @return true if the student was removed, false the student was not removed
     */
    public boolean removeStudent(int studentId){
        boolean studentRemoved = false;
        int indexFound = -1;

        for(int i = 0; i < studentIndex; i++){
            if(students[i].getId() == studentId){
                indexFound = i;
                studentRemoved = true;
            }
        }

        if(studentRemoved){
            for(int i = indexFound; i < ARRAY_MAX; i++){
                if((i + 1) < courses.length){
                    students[i] = students[i + 1];
                }
            }

            studentIndex --;
        }


        return studentRemoved;
    }

    /**
     * Removes a student (specified by the index) from the array
     * Shifts all other students down in the array such that there are no null elements in the middle.
     * If there are any schedules for te student, those schedules are not affected (for assignment simplicity)
     *
     * @param index - the index of student to remove
     */
    private void removeStudentEntry(int index){
        for(int i = index; i < ARRAY_MAX; i++){
            if((i + 1) < students.length){
                students[i] = students[i + 1];
            }
        }
    }


}
