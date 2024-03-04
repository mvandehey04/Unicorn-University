package edu.cs2430.assignment3;
import java.util.UUID;

public class Schedule{
    // a constant to represent the maximum sie of the schedule array
    private static final int MAX_SCHEDULE_ENTRY = 50;

    // an array of entries for the schedule
    private ScheduleEntry[] scheduleEntries = new ScheduleEntry[MAX_SCHEDULE_ENTRY];

    // the current index for the array of schedule entries
    private int scheduleEntryIndex = 0;

    // the student id associated with the schedule
    private int studentId;

    // a unique identifier for the given schedule
    private UUID uuid;

    //
    // constructors
    //
    /**
     * creates an empty schedule with the given student id
     * sets the uuid to a random UUID by calling UUID.randomUUID()
     */
    public Schedule(int studentId){
        this.studentId = studentId;
        this.uuid = UUID.randomUUID();
    }

    /**
     * creates a copy of the schedule provided
     * sets the uuid to random UUID by calling UUID.randomUUID()
     * deep copies the array of schedule entries
     */
    public Schedule(Schedule otherSchedule){
        /*this.studentId = otherSchedule.studentId;
        this.uuid = UUID.randomUUID();
        for(int i = 0; i < otherSchedule.scheduleEntryIndex; i++){
            if(scheduleEntries[i] != null){
                this.scheduleEntries[i] = otherSchedule.scheduleEntries[i];
                scheduleEntryIndex ++;
            }
        }*/
        this.scheduleEntries = new ScheduleEntry[MAX_SCHEDULE_ENTRY];
        for(int i=0; i< otherSchedule.scheduleEntryIndex; i++){
            scheduleEntries[i] = otherSchedule.scheduleEntries[i];
        }
        this.scheduleEntryIndex = otherSchedule.scheduleEntryIndex;
        this.studentId = otherSchedule.studentId;
        this.uuid = UUID.randomUUID();
    }

    //
    // getters and setters
    //
    /**
     * @return the student id
     */
    public int getStudentId(){
        return studentId;
    }

    /**
     * @return the uuid
     */
    public UUID getUuid(){
        return uuid;
    }

    /**
     * Returns an array of all courses for a given semester/year.
     *
     * @param semester - the semester
     * @param year - the year
     * @return an array of all courses for a given semester
     */
    public Course[] getCoursesForSemester(Semester semester, int year){
        int index = -1;
        int arrayLength = 0;

        for(int i = 0; i < scheduleEntryIndex; i++){
            if(scheduleEntries[i].getSemester() == semester && scheduleEntries[i].getYear() == year){
                arrayLength ++;
            }
        }

        Course[] semesterCourses = new Course[arrayLength];

        for (int i = 0; i < scheduleEntryIndex; i++) {
            if (scheduleEntries[i].getSemester() == semester && scheduleEntries[i].getYear() == year) {
                index++;
                semesterCourses[index] = scheduleEntries[i].getCourse();
            }
        }

        return semesterCourses;
    }

    /**
     * Returns the schedule entry at the specified index.
     *
     * @param index - the index of the schedule entry
     * @return the schedule entry at the specified index
     */
    public ScheduleEntry getScheduleEntry(int index){
        ScheduleEntry entry = null;

        for(int i = 0; i < MAX_SCHEDULE_ENTRY; i++){
            if(i == index){
                entry = scheduleEntries[i];
            }
        }

        return entry;
    }

    //
    // class methods
    //
    /**
     * Adds the provided schedule entry to the schedule.
     * Does not add the entry if the schedule entry array is full.
     *
     * @param scheduleEntry - the schedule entry to add
     * @return true if the schedule entry was added to the schedule, false if the schedule entry was not added to schedule
     */
    public boolean addScheduleEntry(ScheduleEntry scheduleEntry){
        boolean scheduleAdded = false;
        if(scheduleEntry != null && scheduleEntries != null){
            if (scheduleEntryIndex < MAX_SCHEDULE_ENTRY) {
                scheduleEntries[scheduleEntryIndex] = scheduleEntry;
                scheduleEntryIndex++;
                scheduleAdded = true;
            }
        }


        return scheduleAdded;
    }

    /**
     * Calculates the total number of credits for the schedule
     *
     * @return the total number of credits in the schedule
     */
    public int calculateCredits(){
        int totalCredits = 0;

        for(int i = 0; i < scheduleEntryIndex; i++){
            totalCredits += scheduleEntries[i].getCourse().getNumberOfCredits();
        }

        return totalCredits;
    }

    /**
     * Calculates the total number of credits for the schedule for a given semester/year
     *
     * @param semester - the semester
     * @param year - the year
     * @return the total number of credits in the schedule for a given semester/year
     */
    public int calculateCredits(Semester semester, int year){
        int totalCredits = 0;

        for(int i = 0; i < scheduleEntryIndex; i++){
            if(scheduleEntries[i].getSemester() == semester && scheduleEntries[i].getYear() == year){
                totalCredits += scheduleEntries[i].getCourse().getNumberOfCredits();
            }
        }

        return totalCredits;
    }

    /**
     * Determines if the given course is in the schedule.
     *
     * @param course - the course
     * @return true if the course is in the schedule, false if the course is not in the schedule
     */
    public boolean isCourseInSchedule(Course course){
        boolean courseInSchedule = false;

        for(int i = 0; i < scheduleEntryIndex; i++){
            if(scheduleEntries[i].getCourse() == course){
                courseInSchedule = true;
            }
        }

        return courseInSchedule;
    }

    /**
     * Removed the schedule entry at the specified index.
     *
     * @param index - the index to remove the schedule entry from
     */
    private void removeScheduleEntry(int index){
        for(int i = index; i < scheduleEntryIndex; i++){
            if((i + 1) < scheduleEntries.length){
                scheduleEntries[i] = scheduleEntries[i + 1];
            }
        }
    }

    /**
     * Removes the schedule entry associated with the first course found from the schedule that matches the course provided.
     *
     * @param course - the course to search for
     * @return true if a schedule entry was removed, false if a schedule entry was not removed
     */
    public boolean removeScheduleEntry(Course course){
        boolean scheduleEntryRemoved = false;
        int indexFound = -1;

        for(int i = 0; i < scheduleEntryIndex; i++){
            if(scheduleEntries[i].getCourse() == course){
                indexFound = i;
                scheduleEntryRemoved = true;
            }
        }

        if (scheduleEntryRemoved) {
            for (int i = indexFound; i < scheduleEntryIndex - 1; i++) {
                scheduleEntries[i] = scheduleEntries[i + 1];
            }
            scheduleEntryIndex--;
        }
        return scheduleEntryRemoved;
    }

    /**
     * Removed the schedule entry (specified by the schedule entry uuid) from the schedule
     *
     * @param uuid - the uuid of the schedule entry to remove
     * @return true if the schedule entry was removed, false if the schedule entry was not removed
     */
    public boolean removeScheduleEntry(UUID uuid){
        boolean scheduleEntryRemoved = false;
        int indexFound = -1;

        for(int i = 0; i < scheduleEntryIndex; i++){
            if(scheduleEntries[i].getUuid() == uuid){
                indexFound = i;
                scheduleEntryRemoved = true;
            }
        }

        if (scheduleEntryRemoved) {
            for (int i = indexFound; i < scheduleEntryIndex - 1; i++) {
                scheduleEntries[i] = scheduleEntries[i + 1];
            }
            scheduleEntryIndex--;
        }
        return scheduleEntryRemoved;
    }


}
