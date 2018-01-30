package fr.eni.spectacle.bo;

import java.util.Date;

public class Reservation {
	private int codeReservation;
	private int idSpectacle;
	private int clientId;
	private int nombrePlaces;
	private Date dateReservation ;
	
	public Reservation(int codeReservation,  int idSpectacle,int clientId, 
			int nombrePlaces ) {
		this.codeReservation= codeReservation;
		this.idSpectacle = idSpectacle;
		this.clientId = clientId;
		this.nombrePlaces = nombrePlaces;
	}
	
	public Reservation(int idSpectacle,int clientId, 
			int nombrePlaces ) {
		this.idSpectacle = idSpectacle;
		this.clientId = clientId;
		this.nombrePlaces = nombrePlaces;
	}

	public int getCodeReservation() {
		return codeReservation;
	}


	public void setCodeReservation(int codeReservation) {
		this.codeReservation = codeReservation;
	}


	public int getIdSpectacle() {
		return idSpectacle;
	}


	public void setIdSpectacle(int idSpectacle) {
		this.idSpectacle = idSpectacle;
	}


	public int getClientId() {
		return clientId;
	}


	public void setClientId(int clientId) {
		this.clientId = clientId;
	}


	public int getNombrePlaces() {
		return nombrePlaces;
	}


	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}


	public Date getDateReservation() {
		return dateReservation;
	}


	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

}
