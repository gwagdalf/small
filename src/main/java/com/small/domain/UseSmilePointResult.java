package com.small.domain;

import lombok.Data;

@Data
public class UseSmilePointResult {

    private long smilePointNo; // SmilePoint 번호

    private long refPayNo;      // 참조 결제번호

    private int resultNo;    // 결과 번호    : 0 정상,  0 < 오류

    private String resultComment;  // 결과 사유

}