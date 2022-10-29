package com.oracle.oBootJpa02.domain;

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
@SequenceGenerator(name = "team_seq_gen",
					sequenceName = "team_seq_generator",
					initialValue = 1,
					allocationSize = 1
					)
@Table(name = "Team")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "team_seq_gen"
					)
	@Column(name = "team_id")
	private Long team_id;
	@Column(name = "teamname")
	private String name;
	
}
