package com.ashfaq.example.auditing;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractAuditingEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract T getId();

	@CreatedDate
	@Column(updatable = false, name = "created_date")
	private Instant createdDate;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Instant lastModifiedDate;

}
