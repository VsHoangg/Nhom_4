/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String FilmID,Information,status,image;
    private int HotLevel;
    private float Price,Duration;
    private Date Publish_date;
    private List<Schedule> schedules=new ArrayList<>();
    private float sumturnover;
    private int male,female,sum;

    public int getMale() {
        return male;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }
    
    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie{" + "FilmID=" + FilmID + ", Information=" + Information + ", status=" + status + ", image=" + image + ", HotLevel=" + HotLevel + ", Price=" + Price + ", Duration=" + Duration + ", Publish_date=" + Publish_date + ", schedules=" + schedules + '}';
    }

    public float getSumturnover() {
        return sumturnover;
    }

    public void setSumturnover(float sumturnover) {
        this.sumturnover = sumturnover;
    }

 

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
    
    public Movie(String FilmID, String Information, String status, String image, int HotLevel, float Price, float Duration, Date Publish_date) {
        this.FilmID = FilmID;
        this.Information = Information;
        this.status = status;
        this.image = image;
        this.HotLevel = HotLevel;
        this.Price = Price;
        this.Duration = Duration;
        this.Publish_date = Publish_date;
    }

    public String getFilmID() {
        return FilmID;
    }

    public void setFilmID(String FilmID) {
        this.FilmID = FilmID;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String Information) {
        this.Information = Information;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHotLevel() {
        return HotLevel;
    }

    public void setHotLevel(int HotLevel) {
        this.HotLevel = HotLevel;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public float getDuration() {
        return Duration;
    }

    public void setDuration(float Duration) {
        this.Duration = Duration;
    }

    public Date getPublish_date() {
        return Publish_date;
    }

    public void setPublish_date(Date Publish_date) {
        this.Publish_date = Publish_date;
    }
    
}
