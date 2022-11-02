package org.zerock.mreview.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@NoArgsConstructor
public class MovieImageDTO {

    private String uuid;

    private String imgName;

    private String path;

    private String imageURL;

    private String thumbnailURL;

    public MovieImageDTO(String uuid, String imgName, String path) {
        this.uuid = uuid;
        this.imgName = imgName;
        this.path = path;
        this.imageURL = makeImageURL();
        this.thumbnailURL = makeThumbnailURL();
    }

    public String makeImageURL() {
        try {
            return URLEncoder.encode(path + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String makeThumbnailURL() {
        try {
            return URLEncoder.encode(path + "/s_" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}

