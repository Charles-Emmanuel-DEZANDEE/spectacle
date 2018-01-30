package fr.eni.spectacle.bo;

import java.util.Date;
import java.util.List;

public class Spectacle {
	
	private int idSpectacle;
	private String titre;
	private String artiste;
	private String lieu;
	private Date date;
	private int placesDisponibles;
	private List<Reservation> listeReservations ;
	
	public Spectacle (int idSpectacle, String titre,String artiste, 
			String lieu , Date date, int placesDisponibles, List<Reservation> listeReservations){
		 this.idSpectacle = idSpectacle;
		 this.titre = titre;
		 this.artiste = artiste;
		 this.lieu = lieu;
		 this.date = date;
		 this.placesDisponibles = placesDisponibles;
		 this.listeReservations = listeReservations;
	}
	
	public Spectacle (String titre,String artiste, 
			String lieu , Date date, int placesDisponibles, List<Reservation> listeReservations){
		 this.titre = titre;
		 this.artiste = artiste;
		 this.lieu = lieu;
		 this.date = date;
		 this.placesDisponibles = placesDisponibles;
		 this.listeReservations = listeReservations;
	}
	
	public List<Reservation> getListeReservations() {
		return listeReservations;
	}

	public void setListeReservations(List<Reservation> listeReservations) {
		this.listeReservations = listeReservations;
	}

	public int getIdSpectacle() {
		return idSpectacle;
	}
	public void setIdSpectacle(int idSpectacle) {
		this.idSpectacle = idSpectacle;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getArtiste() {
		return artiste;
	}
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPlacesDisponibles() {
		return placesDisponibles;
	}
	public void setPlacesDisponibles(int placesDisponibles) {
		this.placesDisponibles = placesDisponibles;
	}
	
	
}
