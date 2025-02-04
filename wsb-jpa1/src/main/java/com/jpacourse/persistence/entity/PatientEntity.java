package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.Pronoun;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PATIENT_ID")
	private Long id;

	@Column(name="FIRST_NAME",nullable = false)
	private String firstName;

	@Column(name="LAST_NAME",nullable = false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(name="PRONOUN", nullable = false)
	private Pronoun pronoun;

	@Column(name="TELEPHONE_NUMBER",nullable = false)
	private String telephoneNumber;

	@Column(name="EMAIL",unique=true)
	private String email;

	@Column(name = "PATIENT_NUMBER", nullable = false, unique = true)
	private String patientNumber;

	@Column(name = "DATE_OF_BIRTH", nullable = false)
	private LocalDate dateOfBirth;

	// Relacja z encją AddressEntity
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	private AddressEntity address;

	// Relacja z encją VisitEntity, mapowana przez Patient
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitEntity> visits;

	@Version
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Pronoun getPronoun() {
		return pronoun;
	}

	public void setPronoun(Pronoun pronoun) {
		this.pronoun = pronoun;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public List<VisitEntity> getVisits() {
		return (visits != null) ? visits : new ArrayList<>();
	}

	public void setVisits(List<VisitEntity> visits) {
		this.visits = visits;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
