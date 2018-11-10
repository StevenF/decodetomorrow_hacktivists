package com.frio.steven.decodetomorrow.RecyclerParser;

/**
 * Created by JMM on 11/10/2018.
 */

public class RParserShowInquiries {

    private String id;
    private String userID;
    private String name;
    private String bedroomCount;
    private String floorArea;
    private String lotArea;
    private String status;


    public RParserShowInquiries(String id, String userID, String name, String bedroomCount, String floorArea, String lotArea, String status) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.bedroomCount = bedroomCount;
        this.floorArea = floorArea;
        this.lotArea = lotArea;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getBedroomCount() {
        return bedroomCount;
    }

    public String getFloorArea() {
        return floorArea;
    }

    public String getLotArea() {
        return lotArea;
    }

    public String getStatus() {
        return status;
    }
}
