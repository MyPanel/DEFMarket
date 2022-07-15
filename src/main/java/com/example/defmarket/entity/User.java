package com.example.defmarket.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "users")
@DynamicInsert // Insert 구문에 null 값을 삽입할 경우 해당 칼럼은 제외하고 Insert 구문 생성
@DynamicUpdate // 동일하게 갱신 진행
public class User {

    @Id
    @GeneratedValue(generator = "DEF_SEQ")
    private Integer user_id;

    @Column(nullable = false)
    private String user_email;

    @Column(nullable = false)
    private String user_password;

    @Column(nullable = false)
    private String user_name;

    private String user_nickname;
    private Integer user_age;
    private String user_gender;
    private String user_address;
    private String user_postNumber;
    private String user_grade;
    private Timestamp user_birthday;

    @Column(columnDefinition = "INTEGER(20) default 0")
    private Integer user_point = 0;
    private String user_phoneNumber;
    private Integer user_footBallCheck;
    private Integer user_footSize;

    @Column(columnDefinition = "BOOLEAN default false")
    private Boolean user_flatFeetCheck;
    private Integer user_type;
    private String login_type;
    private Integer user_status;
    private Timestamp delete_date;
    private Timestamp disable_date;

}
