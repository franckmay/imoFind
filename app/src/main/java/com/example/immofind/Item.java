package com.example.immofind;

public class Item {
    String birdListName;
    int birdListImage;

    public Item(String birdName,int birdImage)
    {
        this.birdListImage=birdImage;
        this.birdListName=birdName;
    }
    public String getbirdName()
    {
        return birdListName;
    }
    public int getbirdImage(){
        return birdListImage;
    }
}
