package com.example.muaythai_tournament_matchmaker.models.fighter;

import jakarta.persistence.*;

@Embeddable
public class FighterImage{

    @Column(name = "full_height_image", length = 500)
    private String fullHeightImage;

    @Column(name = "head_to_waist_image", length = 500)
    private String headToWaistImage;

    @Column(name = "close_up_image", length = 500)
    private String closeUpImage;

    public FighterImage(){};

    public FighterImage(String fullHeightImage, String headToWaistImage, String closeUpImage) {
        this.fullHeightImage = fullHeightImage;
        this.headToWaistImage = headToWaistImage;
        this.closeUpImage = closeUpImage;
    }

    public String getFullHeightImage() {
        return fullHeightImage;
    }

    public void setFullHeightImage(String fullHeightImage) {
        this.fullHeightImage = fullHeightImage;
    }

    public String getHeadToWaistImage() {
        return headToWaistImage;
    }

    public void setHeadToWaistImage(String headToWaistImage) {
        this.headToWaistImage = headToWaistImage;
    }

    public String getCloseUpImage() {
        return closeUpImage;
    }

    public void setCloseUpImage(String closeUpImage) {
        this.closeUpImage = closeUpImage;
    }
}