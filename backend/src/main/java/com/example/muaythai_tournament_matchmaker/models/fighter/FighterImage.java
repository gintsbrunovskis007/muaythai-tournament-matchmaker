package com.example.muaythai_tournament_matchmaker.models.fighter;

import jakarta.persistence.*;

@Entity
@Table(name = "fighter_images")
public class FighterImage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String fullHeightImage;
    String headToWaistImage;
    String closeUpImage;

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