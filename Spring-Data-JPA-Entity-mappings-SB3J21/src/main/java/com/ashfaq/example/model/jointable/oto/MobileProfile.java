package com.ashfaq.example.model.jointable.oto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class MobileProfile {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String address;
	    private String phoneNumber;

	    @OneToOne(mappedBy = "profile")
	    private MobileUser user;

}
