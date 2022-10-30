package org.zerock.mreview.dto;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UploadResultDTO implements Serializable {
  private String fileName;
  private String uuid;

  private String folderPath;

  private String imageUrl;

  private String thumbnailUrl;

  public UploadResultDTO(String fileName, String uuid, String folderPath) {
    this.fileName = fileName;
    this.uuid = uuid;
    this.folderPath = folderPath;
    this.imageUrl = imageUrlMaker();
    this.thumbnailUrl = thumbnailUrlMaker();
  }

  private String thumbnailUrlMaker() {
    try {
      return URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  // 롬복과 imageURL 충돌로 이름 변경 DTO에 만든 데이터가 생산되지 않음.
  private String imageUrlMaker() {
    try {
      return URLEncoder.encode(this.folderPath + File.separator + this.uuid + "_" + this.fileName, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }
}