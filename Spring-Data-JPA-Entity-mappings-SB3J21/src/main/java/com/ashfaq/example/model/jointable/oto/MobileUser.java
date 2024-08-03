package com.ashfaq.example.model.jointable.oto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//*** The @JoinTable annotation is not typically used for one-to-one relationships. 
//Instead, @JoinColumn is the more appropriate choice. 

@EqualsAndHashCode(of = { "id" })
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "jpa_MobileUser" )
public class MobileUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;

	@OneToOne
	@JoinColumn(name = "profile_id")
	private MobileProfile profile;

}
