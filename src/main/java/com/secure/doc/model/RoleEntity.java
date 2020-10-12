package com.secure.doc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.secure.doc.enums.EnumRole;



@Entity
@Table(name = "roles")
public class RoleEntity {

	
	@Id
	@SequenceGenerator(name="seq_roles",
    sequenceName="seq_roles",
    allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_roles")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EnumRole name;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnumRole getName() {
		return name;
	}

	public void setName(EnumRole name) {
		this.name = name;
	}

	

	
}
