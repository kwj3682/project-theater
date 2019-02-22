package kr.co.movie.domain;

import java.util.Date;

//id,password,m_name, address, birth, phone_number, point,mail,is_ten
public class Member {
	private String id;
	private String password;
	private String mName;
	private String address;
	private String mail;
	private Date birth;
	private String phoneNumber;
	private int point;
	private char isTen;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public char getIsTen() {
		return isTen;
	}
	public void setIsTen(char isTen) {
		this.isTen = isTen;
	}
	
}

