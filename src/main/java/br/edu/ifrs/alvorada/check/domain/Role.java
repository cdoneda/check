package br.edu.ifrs.alvorada.check.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by rodrigo on 3/18/17.
 */
@Entity
@Data
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String role;
	@ManyToMany
	private Set<User> accounts;
}
