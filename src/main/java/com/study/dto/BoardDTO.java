package com.study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private String id;
    private String categoryName;
    private String title;
    private String content;
    private String regName;
    private String regDt;
    private String modName;
    private String modDt;
    private int views;
}
