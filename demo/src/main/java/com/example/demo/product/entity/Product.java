package com.example.demo.product.entity;

import java.time.LocalDateTime;


public class Product {

    private Long productId;                         // 상품 코드
    private String productName;                     // 상품명
    private int price;                              // 가격
    private int stockNumber;                        // 재고 수량
    private String productDetail;                   // 상품 상세 설명
    private ProductSellStatus productSellStatus;    // 상품 판매 상태
    private LocalDateTime regTime;                  // 상품 등록 시간
    private LocalDateTime updateTime;               // 상품 수정 시간
}
