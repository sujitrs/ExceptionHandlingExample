package org.sj.teammember.entities;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
@Table//(uniqueConstraints = { @UniqueConstraint(name = "uk_TeamMember_emailID", columnNames = "emailID") })
public class TeamMember {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	private UUID id;
	
	@NotNull
	@Size(min = 5, max = 14)
	private String name;
	
	@NotNull
	@Size(min = 5, max = 14)
	private String department;
	
	@NotNull
	@Size(min = 5, max = 14)
	private String reporting;
	
	@NotNull
	@Email
	private String emailID;
	
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
	@Version
    private int version;
}
