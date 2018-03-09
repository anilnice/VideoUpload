package com.example.anil.videoupload;

/**
 * Created by Anil on 3/9/2018.
 */

public class SingleData {
    int id;
    String video_path,video_name;

    public SingleData(int id, String video_path, String video_name) {
        this.id = id;
        this.video_path = video_path;
        this.video_name = video_name;
    }

    public SingleData() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }
}
