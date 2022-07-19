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
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(name = "constraintName",
        columnNames = {
                "userEmail",
                "userNickName",
                "userPhoneNumber"})
})
@DynamicInsert // Insert 구문에 null 값을 삽입할 경우 해당 칼럼은 제외하고 Insert 구문 생성
@DynamicUpdate // 동일하게 갱신 진행
public class User {

    @Id
    @GeneratedValue(generator = "DEF_SEQ")
    private int userId;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userName;

    private String userNickName;
    private Integer userAge;
    private String userGender;
    private String userAddress;
    private String userPostNumber;
    private String userGrade;
    private Timestamp userBirthday;

    @Column(columnDefinition = "number(10,0) default 0")
    private Integer userPoint;
    private String userPhoneNumber;
    private Integer userFootBallCheck;
    private Integer userFootSize;

    @Column(columnDefinition = "varchar2(255 char) default '0'")
    private String userFlatFeetCheck;
    private Integer userType;
    private String loginType;
    private Integer userStatus;
    private Timestamp deleteDate;
    private Timestamp disableDate;

    public User() {}
    public User(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
