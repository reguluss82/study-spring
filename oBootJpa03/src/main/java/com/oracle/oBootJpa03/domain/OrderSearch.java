package com.oracle.oBootJpa03.domain;

import lombok.Getter;
import lombok.Setter;
// entity 없음. view를 위해 만든 class
@Getter
@Setter
public class OrderSearch {
	private String      memberName;		//회원이름
	private OrderStatus orderStatus;	//주문상태[ORDER , CANCLE]

}
