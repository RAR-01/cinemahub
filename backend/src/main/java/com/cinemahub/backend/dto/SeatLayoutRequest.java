package com.cinemahub.backend.dto;

import com.cinemahub.enums.SeatType;

public class SeatLayoutRequest {
    private long screenId;
    private int rows;
    private int columns;
    private SeatType seatType;
    
    public long getScreenId() {
        return screenId;
    }
    
    public void setScreenId(long screenId) {
        this.screenId = screenId;
    }
    
    public int getRows() {
        return rows;
    }
    
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public void setColumns(int columns) {
        this.columns = columns;
    }
    
    public SeatType getSeatType() {
        return seatType;
    }
    
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
 
}
