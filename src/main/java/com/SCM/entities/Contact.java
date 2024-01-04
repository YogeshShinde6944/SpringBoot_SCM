package com.SCM.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CONTACT")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;

	@NotBlank(message = "Name field is required !!")
	@Size(min = 2, max = 20, message = "min 2 max 20 character are required !!")
	private String name;
	private String secondName;
	private String email;
	private String phone;
	private String work;
	private String image;

	@ManyToOne
	private User user;

	@Column(length = 50000)
	private String discription;

	public Contact() {
		super();
	}

	public Contact(int cid, String name, String secondName, String email, String phone, String work, String image,
			String discription) {
		super();
		this.cid = cid;
		this.name = name;
		this.secondName = secondName;
		this.email = email;
		this.phone = phone;
		this.work = work;
		this.image = image;
		this.discription = discription;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", name=" + name + ", secondName=" + secondName + ", email=" + email + ", phone="
				+ phone + ", work=" + work + ", image=" + image + ", discription=" + discription + "]";
	}

}
