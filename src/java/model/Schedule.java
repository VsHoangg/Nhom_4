/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class Schedule {
    private int SID;
    private String StartTime,EndTime;
    private Date day;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public Schedule(int SID, String StartTime, String EndTime, Date day) {
        this.SID = SID;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.day = day;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
    
    public Schedule() {
    }

    public Schedule(int SID, String StartTime, String EndTime) {
        this.SID = SID;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    @Override
    public String toString() {
        return "Schedule{" + "StartTime=" + StartTime + ", EndTime=" + EndTime + ", day=" + day + '}';
    }
    
}
