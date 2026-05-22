package com.kh.spring.gallery.model.dto;

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
public class FileDto {
	

	private Long fileNo;
	private Long galleryNo;
	private String originName;
	private String changeName;


}
