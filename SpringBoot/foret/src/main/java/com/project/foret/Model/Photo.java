package com.project.foret.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Photo {
    private int id;
    private String dir;
    private String filename;
    private String originname;
    private int filesize;
    private String filetype;
    private String reg_date;
}
