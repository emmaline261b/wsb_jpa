package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.Specialization;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOCTOR_ID")
	private Long id;

	@Column(name="FIRST_NAME",nullable = false)
	private String firstName;

	@Column(name="LAST_NAME",nullable = false)
	private String lastName;

	@Column(name="TELEPHONE_NUMBER",nullable = false, length=12)
	private String telephoneNumber;

	@Column(name="EMAIL",nullable = false, unique=true)
	private String email;

	@Column(name="DOCTOR_NUMBER",nullable = false,unique=true)
	private String doctorNumber;

	@Column(name="SPECIALIZATION",nullable = false)
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	// Relacja z encją AddressEntity
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	private AddressEntity address;

	// Relacja z encją Visit, mapowana przez encję Doctor
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitEntity> visits;



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

	public String getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

}
