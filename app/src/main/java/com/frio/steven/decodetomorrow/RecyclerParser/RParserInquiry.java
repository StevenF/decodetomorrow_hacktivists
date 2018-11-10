package com.frio.steven.decodetomorrow.RecyclerParser;

/**
 * Created by John Steven Frio on 11/10/2018.
 */

public class RParserInquiry {

    private Integer id;
    private String name;
    private String bedroomCount;
    private String floorArea;
    private String lotArea;
    private String imageUrl;

    public RParserInquiry(Integer id, String name, String bedroomCount, String floorArea, String lotArea, String imageUrl) {
        this.id = id;
        this.name = name;
        this.bedroomCount = bedroomCount;
        this.floorArea = floorArea;
        this.lotArea = lotArea;
        this.imageUrl = imageUrl;
    }


    public Integer getId() {
        return id;
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

    public String getImageUrl() {
        return imageUrl;
    }
}
