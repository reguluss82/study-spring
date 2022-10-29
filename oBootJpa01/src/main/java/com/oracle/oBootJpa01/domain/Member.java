package com.oracle.oBootJpa01.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data //ToString EqualsAndHashCode Getter Setter(final이 아닌 속성) RqueiredArgsConstructor
@Table(name = "member1")
public class Member {
	@Id // (identifier)PK 지정해준다.
	private Long   id;
	private String name;
	
//	@Override
//	public String toString() {
//		String returnStr = "";
//		returnStr = "[id:" + this.id + ", name:" + this.name + "]";
//		return returnStr;
//	}
}

