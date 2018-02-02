package fr.eni.spectacle.ihm;

import java.util.List;

import fr.eni.spectacle.bll.BLLException;
import fr.eni.spectacle.bll.ClientManager;
import fr.eni.spectacle.bll.ReservationManager;
import fr.eni.spectacle.bll.SpectacleManager;
import fr.eni.spectacle.bo.Client;
import fr.eni.spectacle.bo.Reservation;
import fr.eni.spectacle.bo.Spectacle;
import fr.eni.spectacle.dal.DALException;
import fr.eni.spectacle.ihm.*;

public class Controller {
	
	private FenetreAccueilSpectacle fenetreSpectacle;
	private static Controller instance;
	private boolean nouveauClient;

	
	private Controller() throws DALException, BLLException{
		fenetreSpectacle = new FenetreAccueilSpectacle();
		this.nouveauClient = true;
	}

	public static synchronized Controller getInstance() throws DALException, BLLException{
		if (instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public void startApp() throws BLLException, DALException {
		fenetreSpectacle.initListeSpectacle();
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}
	
	public void listeReservations() throws BLLException, DALException{
		fenetreSpectacle.initListeReservations();
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}
	
   public void listeClients() throws BLLException, DALException{
		fenetreSpectacle.initListeClients();
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
    }
   
   public void afficherReservation(int idClient) throws BLLException, DALException{
	  List<Reservation> listeReservations = ReservationManager.getInstance().getReservationByIdClient(idClient);
	  
   }

   public void supprimerClient(Client client) throws BLLException, DALException{
	   	int idClient = client.getIdClient();
	   	ClientManager.getInstance().removeClient(idClient);
		fenetreSpectacle.initListeClients();
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
    }
   
	public void nouvelleReservation(Spectacle spectacle){
		fenetreSpectacle.initReservation(spectacle);
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}
	
	public void rechercherArtiste() throws BLLException, DALException{
		fenetreSpectacle.ListeSpectacleByArtiste(fenetreSpectacle.getFieldRechercherArtiste().getText());
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}

	public void enregistrerReservation(Spectacle spectacle) throws BLLException, DALException {
	    //traitement
        int idClient;
        int idSpectacle = spectacle.getIdSpectacle();

        if (this.nouveauClient){
            //création d'un client
			Client newClient = new Client(fenetreSpectacle.getFieldNom().getText(),fenetreSpectacle.getFielPrenom().getText(), fenetreSpectacle.getFieldEmail().getText(),fenetreSpectacle.getFieldAdresse().getText(),fenetreSpectacle.getFieldCP().getText(),fenetreSpectacle.getFieldVille().getText());
			ClientManager.getInstance().addClient(newClient);
            idClient = newClient.getIdClient();
        }
        else{

             Client clientChoisi = (Client) fenetreSpectacle.getCboClients().getSelectedItem();
            idClient = clientChoisi.getIdClient();
        }

        // recupérer les champs avec get
        //on créé une reservation
        Reservation newReservation = new Reservation(idSpectacle,idClient,
        (Integer) fenetreSpectacle.getCboPlaces().getSelectedItem());


        this.nouveauClient= true;

    }

    public void utiliserClientExistant(Spectacle spectacle, Client client){
	    this.nouveauClient= false;
	    //remplissage des champs de la reservation

        fenetreSpectacle.getFieldNom().setText(client.getNom());
        fenetreSpectacle.getFielPrenom().setText(client.getPrenom());
        fenetreSpectacle.getFieldAdresse().setText(client.getAdresse());
        fenetreSpectacle.getFieldEmail().setText(client.getEmail());
        fenetreSpectacle.getFieldCP().setText(client.getCodePostal());
        fenetreSpectacle.getFieldVille().setText(client.getVille());

        //on affiche la même page remplie

        fenetreSpectacle.initReservation(spectacle);
        fenetreSpectacle.revalidate();
        fenetreSpectacle.repaint();

    }
    
    public void removeReservation(Reservation reservation) throws BLLException, DALException{
    	try {
    		ReservationManager.getInstance().removeReservation(reservation.getCodeReservation());
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
}
