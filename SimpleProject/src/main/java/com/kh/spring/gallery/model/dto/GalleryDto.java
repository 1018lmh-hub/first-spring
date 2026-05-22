package com.kh.spring.gallery.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GalleryDto {
	

	  
	  private Long galleryNo;
	  private String galleryTitle;
	  private String galleryWriter;
	  private String galleryContent;
	  private String count;
	  private String createDate;
	  private String status;
	  private List<FileDto> files;

	


}
