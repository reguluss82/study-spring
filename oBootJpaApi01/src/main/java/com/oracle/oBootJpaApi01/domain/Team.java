package com.oracle.oBootJpaApi01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "team5_seq_gen",
					sequenceName = "team5_seq_generator",
					initialValue = 1,
					allocationSize = 1)
@Table(name = "team5")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "team5_seq_gen")
	private Long   teamId;
	@Column(name = "temaname" , length = 50)
	private String name;
}
