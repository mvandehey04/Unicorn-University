package edu.cs2430.assignment3;
import java.util.UUID;

public class ScheduleEntry{
    // the course for the schedule entry
    private final Course course;

    // the semester for the schedule entry
    private final Semester semester;

    // a unique identifier for the given schedule
    private final UUID uuid;

    // the year for the schedule entry
    private final int year;

    //
    // constructors
    //
    /**
     * Constructs a schedule entry with the given parameters.
     * Assigns the uuid to a random uuid using UUID.randomUUID()
     *
     * @param semester - the semester
     * @param year - the year
     * @param course - the course
     */
    public ScheduleEntry(Semester semester, int year, Course course){
        this.semester = semester;
        this.year = year;
        this.course = course;
        uuid = UUID.randomUUID();
    }

    /**
     * Constructs a schedule entry with the given parameters.
     *
     * @param uuid - a uuid for the schedule entry
     * @param semester - the semester
     * @param year - the year
     * @param course - the course
     */
    public ScheduleEntry(UUID uuid, Semester semester, int year, Course course){
        this.uuid = uuid;
        this.semester = semester;
        this.year = year;
        this.course = course;
    }

    /**
     * Creates a copy of the provided schedule entry
     * The uuid is not copied because each uuid should be unique, therefore the uuid is assigned to UUID.randomUU
     *
     * @param otherScheduleEntry - the schedule to copy from
     */
    public ScheduleEntry(ScheduleEntry otherScheduleEntry){
        this.semester = otherScheduleEntry.semester;
        this.year = otherScheduleEntry.year;
        this.course = otherScheduleEntry.course;
        uuid = UUID.randomUUID();
    }

    //
    // getters and setters
    //
    public UUID getUuid(){
        return uuid;
    }
    public Semester getSemester(){
        return semester;
    }
    public int getYear(){
        return year;
    }
    public Course getCourse(){
        return course;
    }
}
