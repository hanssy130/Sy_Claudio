package ca.bcit.sy_claudio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

//    @SerializedName("id")
//    @Expose
//    private int id;
//    public int getId() {
//        return id;
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @SerializedName("name")
//    @Expose
//    private String srcName;
//    public String getSrcName() {
//        return srcName;
//    }
//    public void setSrcName(String srcName) {
//        this.srcName = srcName;
//    }
//
//    @SerializedName("author")
//    @Expose
//    private String author;
//    public String getAuthor() {
//        return author;
//    }
//    public void setAuthor(String author) {
//        this.author = author;
//    }

    @SerializedName("title")
    @Expose
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
