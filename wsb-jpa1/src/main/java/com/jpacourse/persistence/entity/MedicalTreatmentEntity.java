package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.TreatmentType;

import javax.persistence.*;

@Entity
@Table(name = "MEDICAL_TREATMENT")
public class MedicalTreatmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TREATMENT_ID")
	private Long id;

	@Column(name = "DESCRIPTION",nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE", nullable = false)
	private TreatmentType type;

	// Relacja z VisitEntity
	@ManyToOne
	@JoinColumn(name = "VISIT_ID", nullable = false)
	private VisitEntity visit;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TreatmentType getType() {
		return type;
	}

	public void setType(TreatmentType type) {
		this.type = type;
	}

}
