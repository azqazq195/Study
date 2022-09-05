package com.example.converter.entity;

import com.example.converter.converter.AES256Converter;
import com.example.converter.converter.BooleanToTinyintConverter;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Convert(converter = AES256Converter.class)
    private String regNo;
    @Convert(converter = BooleanToTinyintConverter.class)
    private boolean deleted;
}
