package com.softserve.edu.data;

interface IFirstname {
	ILastname setFirstname(String firstname);
}

interface ILastname {
	IEmail setLastname(String lastname);
}

interface IEmail {
	ILogin setEmail(String email);
}

interface ILogin {
	IPassword setLogin(String login);
}

interface IPassword {
	ICommunity setPassword(String password);
}

interface ICommunity {
	IRole setCommunity(String community);
}

interface IRole {
	IStatus setRole(String role);
}

interface IStatus {
	IBuildUser setStatus(String status);
}

interface IBuildUser {
	User build();
}

public class User implements IFirstname, ILastname, IEmail,
		ILogin, IPassword, ICommunity, IRole, IStatus, IBuildUser, IUser {

	private String firstname;	// must be
	private String lastname;	// must be
	private String email;		// must be
	private String middlename;
	private String phonenumber;
	private String login;		// must be
	private String password;	// must be
	private String role;		// next
	private String status;		// next
	private String community;	// must be
	private String data;
	private String registerNumber;
	private String registratorNumber;
	private String volumeNumber;
	private String region;
	private String district;
	private String city;
	private String street;
	private String building;
	private String flat;
	private String postcode;
	private String seria;
	private String number;
	private String published;
	
	private User(){
		this.firstname = "";
		this.lastname = "";
		this.email = "";
		this.middlename = "";
		this.phonenumber = "";
		this.login = "";
		this.password = "";
		this.role = "";
		this.status = "";
		this.community = "";
		this.data = "";
		this.registerNumber = "";
		this.registratorNumber = "";
		this.volumeNumber = "";
		this.region = "";
		this.district = "";
		this.city = "";
		this.street = "";
		this.building = "";
		this.flat = "";
		this.postcode = "";
		this.seria = "";
		this.number = "";
		this.published = "";
	}
	
//	public User(String firstname, String lastname, String email,
//			String middlename, String phonenumber, String login,
//			String password, String role, String status, String community,
//			String data, String registerNumber, String registratorNumber,
//			String volumeNumber, String region, String district, String city,
//			String street, String building, String flat, String postcode,
//			String seria, String number, String published) {
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.email = email;
//		this.middlename = middlename;
//		this.phonenumber = phonenumber;
//		this.login = login;
//		this.password = password;
//		this.role = role;
//		this.status = status;
//		this.community = community;
//		this.data = data;
//		this.registerNumber = registerNumber;
//		this.registratorNumber = registratorNumber;
//		this.volumeNumber = volumeNumber;
//		this.region = region;
//		this.district = district;
//		this.city = city;
//		this.street = street;
//		this.building = building;
//		this.flat = flat;
//		this.postcode = postcode;
//		this.seria = seria;
//		this.number = number;
//		this.published = published;
//	}

	public static IFirstname get() {
		return new User();
	}
	
	// setters
	
	public ILastname  setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public IEmail  setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public ILogin  setEmail(String email) {
		this.email = email;
		return this;
	}

	public IPassword  setLogin(String login) {
		this.login = login;
		return this;
	}

	public ICommunity  setPassword(String password) {
		this.password = password;
		return this;
	}

	public IRole  setCommunity(String community) {
		this.community = community;
		return this;
	}

	public IStatus  setRole(String role) {
		this.role = role;
		return this;
	}

	public IBuildUser  setStatus(String status) {
		this.status = status;
		return this;
	}

	public User build() {
		return this;
	}
	
	// --------------------------------------------------
	
	public User setMiddlename(String middlename) {
		this.middlename = middlename;
		return this;
	}

	public User setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
		return this;
	}

	public User setData(String data) {
		this.data = data;
		return this;
	}

	public User setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
		return this;
	}

	public User setRegistratorNumber(String registratorNumber) {
		this.registratorNumber = registratorNumber;
		return this;
	}

	public User setVolumeNumber(String volumeNumber) {
		this.volumeNumber = volumeNumber;
		return this;
	}

	public User setRegion(String region) {
		this.region = region;
		return this;
	}

	public User setDistrict(String district) {
		this.district = district;
		return this;
	}

	public User setCity(String city) {
		this.city = city;
		return this;
	}

	public User setStreet(String street) {
		this.street = street;
		return this;
	}

	public User setBuilding(String building) {
		this.building = building;
		return this;
	}

	public User setFlat(String flat) {
		this.flat = flat;
		return this;
	}

	public User setPostcode(String postcode) {
		this.postcode = postcode;
		return this;
	}

	public User setSeria(String seria) {
		this.seria = seria;
		return this;
	}

	public User setNumber(String number) {
		this.number = number;
		return this;
	}

	public User setPublished(String published) {
		this.published = published;
		return this;
	}

	
	// getters
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getMiddlename() {
		return middlename;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

	public String getCommunity() {
		return community;
	}

	public String getData() {
		return data;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public String getRegistratorNumber() {
		return registratorNumber;
	}

	public String getVolumeNumber() {
		return volumeNumber;
	}

	public String getRegion() {
		return region;
	}

	public String getDistrict() {
		return district;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getBuilding() {
		return building;
	}

	public String getFlat() {
		return flat;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getSeria() {
		return seria;
	}

	public String getNumber() {
		return number;
	}

	public String getPublished() {
		return published;
	}

}
