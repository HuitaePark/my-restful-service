package kr.co.baki.myrestfulservice.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
@Table(name = "users")
public class User {
    @Schema(title = "사용자 ID", description = "사용자 ID는 자동 생성됩니다")
    @Id
    @GeneratedValue
    private Integer id;

    @Schema(title = "사용자 이름", description = "사용자 이름을 입력합니다.")
    @Size(min=2, message="Name should have at least 2 characters")
    private String name;

    @Schema(title = "사용자 등록일", description = "사용자 등록일을 입력하지 않으면 현재 날짜가 지정됩니다.")
    @Past(message = "등록일은 미래 날짜를 입력하실수 없습니다.")
    private Date joinDate;
    //@JsonIgnore
    @Schema(title = "사용자 패스워드", description = "사용자 패스워드를 입력합니다.")
    private String password;
    //@JsonIgnore
    @Schema(title = "사용자 주민번호", description = "사용자 주민번호를 입력합니다.")
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Integer id, Date joinDate, String name, String password, String ssn) {
        this.id = id;
        this.joinDate = joinDate;
        this.name = name;
        this.password = password;
        this.ssn = ssn;
    }
}
