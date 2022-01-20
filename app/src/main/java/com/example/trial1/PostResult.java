package com.example.trial1;

import com.google.gson.annotations.SerializedName;
//REST API로 받아올 데이터를 변환하여 매핑할 DTO 클래스 선언
//REST API의 응답 데이터 구조에 맞게 모델 클래스 선언 - 클래스명은 상관x
// DTO 모델 - PostResult Class 선언
public class PostResult {

    @SerializedName("cd_id")
    private int cd_id;

    @SerializedName("c")
    private int c;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    private String cd_name;
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑시켜줌

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResult{" +
                "userId=" + cd_id +
                ", id=" + c +
                ", title='" + cd_name + '\'' +
                '}';
    }
}