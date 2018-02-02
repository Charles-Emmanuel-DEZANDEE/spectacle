package fr.eni.spectacle.bo;

import java.sql.Date;

public class Reservation {
	private String codeReservation;
	private int idSpectacle;
	private int clientId;
	private int nombrePlaces;
	private Date dateReservation ;
	
	public Reservation(int idSpectacle,int clientId,
			int nombrePlaces ) {
		this.idSpectacle = idSpectacle;
		this.clientId = clientId;
		this.nombrePlaces = nombrePlaces;
	}
	
	public Reservation(String code_reservation, int idSpectacle, int clientId,
					   int nombrePlaces, Date date_reservation) {
		this.codeReservation = code_reservation;
		this.idSpectacle = idSpectacle;
		this.clientId = clientId;
		this.nombrePlaces = nombrePlaces;
	}

	public String getCodeReservation() {
		return codeReservation;
	}


	public void setCodeReservation(String codeReservation) {
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

	@Override
	public String toString() {
		return "Reservation [codeReservation=" + codeReservation + ", idSpectacle=" + idSpectacle + ", clientId="
				+ clientId + ", nombrePlaces=" + nombrePlaces + ", dateReservation=" + dateReservation + "]";
	}

	
	
}
