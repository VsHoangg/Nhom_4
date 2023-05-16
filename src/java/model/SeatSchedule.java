/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;


public class SeatSchedule {
//    [SID] int,
//Position nvarchar(10),
//foreign key (Position) references [seat](Position),
//foreign key ([SID]) references [Schedule]([SID]),
//[Status] bit,
    private int SID;
    private String Position;
    private Boolean Status;

    public SeatSchedule() {
    }

    public SeatSchedule(int SID, String Position, Boolean Status) {
        this.SID = SID;
        this.Position = Position;
        this.Status = Status;
    }

    

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

  
    
}
