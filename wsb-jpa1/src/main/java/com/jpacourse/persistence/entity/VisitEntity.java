package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.TreatmentType;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VISIT_ID")
	private Long id;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "TIME", nullable = false)
	private LocalDateTime time;

	// Relacja z DoctorEntity
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	private DoctorEntity doctor;

	// Relacja z PatientEntity
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private PatientEntity patient;

	@OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicalTreatmentEntity> treatments;

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

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public List<MedicalTreatmentEntity> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<MedicalTreatmentEntity> treatments) {
		this.treatments = treatments;
	}
}
