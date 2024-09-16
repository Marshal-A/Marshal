package com.emp.payroll.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    private long empId;

    @Column(name = "Date")
    private String Date;

    @Column(name = "Shift")
    private String Shift;

    @Column(name = "startingTime")
    private String startingTime;

    @Column(name = "endTime")
    private String endTime;

    @Column(name = "Duration")
    private String Duration;

    // Define the time format (24-hour format)
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // Default constructor
    public Schedule() { }

    // Constructor with parameters
    public Schedule(String date, String shift, String startingTime, String endTime) {
        this.Date = date;
        this.Shift = shift;
        this.startingTime = startingTime;
        this.endTime = endTime;
        calculateDuration();  // Automatically calculate duration
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getShift() {
        return Shift;
    }

    public void setShift(String shift) {
        Shift = shift;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
        calculateDuration();  // Recalculate duration when start time is changed
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        calculateDuration();  // Recalculate duration when end time is changed
    }

    public String getDuration() {
        return Duration;
    }

    private void calculateDuration() {
        if (this.startingTime != null && this.endTime != null) {
            // Parse the starting and ending times
            LocalTime start = LocalTime.parse(startingTime, timeFormatter);
            LocalTime end = LocalTime.parse(endTime, timeFormatter);

            // Calculate the duration between the times
            Duration duration = Duration.between(start, end);

            // Handle negative duration (for shifts that pass midnight)
            if (duration.isNegative()) {
                duration = duration.plusDays(1);
            }

            // Convert duration to hours and minutes
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;

            // Set the Duration string in a readable format
            this.Duration = hours + " hours " + minutes + " minutes";
        }
    }
}
