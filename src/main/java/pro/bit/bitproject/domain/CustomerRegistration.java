package pro.bit.bitproject.domain;

import java.time.LocalDateTime;

public class CustomerRegistration {
	
	private int cusId;
	private int branchId;
	private String cusfullname;
	private String initials;
	private String initialsextraction;
	private String cusshopAddresslineone;
	private String cusshopAddresslinetwo;
	private String cusshopAddresslinethree;
	private String cushomeAddresslineone;
	private String cushomeAddresslinetwo;
	private String cushomeAddresslinethree;
	private String shoptel;
	private String cushometel;
	private String cusmobile;
	private String cusnic;
	private String cusemail;
	
	private String cusspfullname;
	private String spinitials;
	private String spinitialsextraction;
	private String spcusAddresslineone;
	private String spcusAddresslinetwo;
	private String spcusAddresslinethree;
	private String spcushometel;
	private String spcusmobile;
	private String cusspnic;
	private String cusspemail;
	private LocalDateTime createdtime;
	private Character status;
	private int customerType;
	
	
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public String getCusfullname() {
		return cusfullname;
	}
	public void setCusfullname(String cusfullname) {
		this.cusfullname = cusfullname;
	}
	
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	public String getInitialsextraction() {
		return initialsextraction;
	}
	public void setInitialsextraction(String initialsextraction) {
		this.initialsextraction = initialsextraction;
	}
	
	public String getCusshopAddresslineone() {
		return cusshopAddresslineone;
	}
	public void setCusshopAddresslineone(String cusshopAddresslineone) {
		this.cusshopAddresslineone = cusshopAddresslineone;
	}
	
	public String getCusshopAddresslinetwo() {
		return cusshopAddresslinetwo;
	}
	public void setCusshopAddresslinetwo(String cusshopAddresslinetwo) {
		this.cusshopAddresslinetwo = cusshopAddresslinetwo;
	}
	
	public String getCusshopAddresslinethree() {
		return cusshopAddresslinethree;
	}
	public void setCusshopAddresslinethree(String cusshopAddresslinethree) {
		this.cusshopAddresslinethree = cusshopAddresslinethree;
	}
	
	public String getCushomeAddresslineone() {
		return cushomeAddresslineone;
	}
	public void setCushomeAddresslineone(String cushomeAddresslineone) {
		this.cushomeAddresslineone = cushomeAddresslineone;
	}
	
	public String getCushomeAddresslinetwo() {
		return cushomeAddresslinetwo;
	}
	public void setCushomeAddresslinetwo(String cushomeAddresslinetwo) {
		this.cushomeAddresslinetwo = cushomeAddresslinetwo;
	}
	
	public String getCushomeAddresslinethree() {
		return cushomeAddresslinethree;
	}
	public void setCushomeAddresslinethree(String cushomeAddresslinethree) {
		this.cushomeAddresslinethree = cushomeAddresslinethree;
	}
	
	public String getShoptel() {
		return shoptel;
	}
	public void setShoptel(String shoptel) {
		this.shoptel = shoptel;
	}
	
	public String getCushometel() {
		return cushometel;
	}
	public void setCushometel(String cushometel) {
		this.cushometel = cushometel;
	}
	
	public String getCusmobile() {
		return cusmobile;
	}
	public void setCusmobile(String cusmobile) {
		this.cusmobile = cusmobile;
	}
	
	public String getCusnic() {
		return cusnic;
	}
	public void setCusnic(String cusnic) {
		this.cusnic = cusnic;
	}
	
	public String getCusemail() {
		return cusemail;
	}
	public void setCusemail(String cusemail) {
		this.cusemail = cusemail;
	}
	
	public String getCusspfullname() {
		return cusspfullname;
	}
	public void setCusspfullname(String cusspfullname) {
		this.cusspfullname = cusspfullname;
	}
	
	public String getSpinitials() {
		return spinitials;
	}
	public void setSpinitials(String spinitials) {
		this.spinitials = spinitials;
	}
	
	public String getSpinitialsextraction() {
		return spinitialsextraction;
	}
	public void setSpinitialsextraction(String spinitialsextraction) {
		this.spinitialsextraction = spinitialsextraction;
	}
	
	public String getSpcusAddresslineone() {
		return spcusAddresslineone;
	}
	public void setSpcusAddresslineone(String spcusAddresslineone) {
		this.spcusAddresslineone = spcusAddresslineone;
	}
	
	public String getSpcusAddresslinetwo() {
		return spcusAddresslinetwo;
	}
	public void setSpcusAddresslinetwo(String spcusAddresslinetwo) {
		this.spcusAddresslinetwo = spcusAddresslinetwo;
	}
	
	public String getSpcusAddresslinethree() {
		return spcusAddresslinethree;
	}
	public void setSpcusAddresslinethree(String spcusAddresslinethree) {
		this.spcusAddresslinethree = spcusAddresslinethree;
	}
	
	public String getSpcushometel() {
		return spcushometel;
	}
	public void setSpcushometel(String spcushometel) {
		this.spcushometel = spcushometel;
	}
	
	public String getSpcusmobile() {
		return spcusmobile;
	}
	public void setSpcusmobile(String spcusmobile) {
		this.spcusmobile = spcusmobile;
	}
	
	public String getCusspnic() {
		return cusspnic;
	}
	public void setCusspnic(String cusspnic) {
		this.cusspnic = cusspnic;
	}
	
	public String getCusspemail() {
		return cusspemail;
	}
	public void setCusspemail(String cusspemail) {
		this.cusspemail = cusspemail;
	}
	
	public LocalDateTime getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalDateTime createdtime) {
		this.createdtime = createdtime;
	}
	public int getCustomerType() {
		return customerType;
	}
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	
	
}
