package kr.co.baki.myrestfulservice.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
public class User {
    private Integer id;

    @Size(min=2, message="Name should have at least 2 characters")
    private String name;

    @Past(message = "등록일은 미래 날짜를 입력하실수 없습니다.")
    private Date joinDate;

    private String password;
    private String ssn;
}
