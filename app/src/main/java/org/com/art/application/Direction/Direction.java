package org.com.art.application.direction;

/**
 * Created by Wonka on 15.07.2017.
 */

public class Direction {
    private  String image = "def";
    private  String name = "def";
    private String desc = "def";
    private String imageurl = "def";
    private String concept = "def";
    private String technique = "def";
    private String mUrlImage = "def";

    private String mArtistListImage;

    public Direction() {
    }

    public Direction(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public String getMUrlImage() {
        return mUrlImage;
    }

    public void setMUrlImage(String urlImage) {
        mUrlImage = urlImage;
    }

    public String getMArtistListImage() {
        return mArtistListImage;
    }

    public void setMArtistListImage(String artistListImage) {
        mArtistListImage = artistListImage;
    }

    @Override
    public String toString() {
        return name + "  :  " + mUrlImage;
    }
}
