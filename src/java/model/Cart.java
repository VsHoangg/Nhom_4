/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Cart {
//    OID int identity primary key,
//UserID int,
//FilmID nvarchar(2000),
//[time] date,
//[SID] int,
//Position nvarchar(10),
//[Status] bit,
    private int OID,UserID;
    private Schedule sch;
    private String FilmID;
    private List<String> Positions=new ArrayList<>();
    private float price;
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }


    public Cart() {
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public Schedule getSch() {
        return sch;
    }

    public void setSch(Schedule sch) {
        this.sch = sch;
    }

 

    public String getFilmID() {
        return FilmID;
    }

    public void setFilmID(String FilmID) {
        this.FilmID = FilmID;
    }

    public List<String> getPositions() {
        return Positions;
    }

    public void setPositions(List<String> Positions) {
        this.Positions = Positions;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price*Positions.size();
    }
    public void setPriceCart(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cart{" + "OID=" + OID + ", UserID=" + UserID + ", sch=" + sch + ", FilmID=" + FilmID + ", Positions=" + Positions + ", price=" + price + '}';
    }

   
  


    



    
}
