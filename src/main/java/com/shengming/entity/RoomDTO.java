package com.shengming.entity;

import java.io.Serializable;

public class RoomDTO implements Serializable {
    private Integer id;

    private String playernum;

    private Integer gametype;

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayernum() {
        return playernum;
    }

    public void setPlayernum(String playernum) {
        this.playernum = playernum;
    }

    public Integer getGametype() {
        return gametype;
    }

    public void setGametype(Integer gametype) {
        this.gametype = gametype;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", playernum='" + playernum + '\'' +
                ", gametype=" + gametype +
                '}';
    }
}