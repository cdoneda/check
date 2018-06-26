package br.edu.ifrs.alvorada.check.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private boolean active;
	private String password;
	@NotBlank
	private String name;
	private String email;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

}