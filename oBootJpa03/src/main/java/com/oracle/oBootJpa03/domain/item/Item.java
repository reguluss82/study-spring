package com.oracle.oBootJpa03.domain.item;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //SINGLE TABLE Strategy 자신을 상속받은 class들을 하나의 Table로 만든다
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //TABLE_PER_CLASS Strategy 자신을 상속받은 class마다 Table로 만든다
//@Inheritance(strategy = InheritanceType.JOINED) //자신을 상속받은 class마다 Table로 만든다, 각 table에 fk도 생성됨
@DiscriminatorColumn(name = "dtype") // 상속받은 class 들의 구분자가 입력될 column A B M
@Getter
@Setter
public abstract class Item {
	@Id
	@GeneratedValue // form에서 item_id (PK) 를 받지못한다.-> notNull 조건 위배되기 때문에 seq로 만들어준다.
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
}
