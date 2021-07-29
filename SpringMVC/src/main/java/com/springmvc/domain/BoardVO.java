package com.springmvc.domain;

import lombok.Data;

import java.util.Date;

/**
 * BoardVO
 * 주소
 * Github : http://github.com/azqazq195
 * Created by azqazq195@gmail.com on 2021-07-13
 */

@Data
public class BoardVO {
    private int idx;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private int cnt;
}
