package fr.eni.spectacle.bll;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

import java.util.List;

public class SpectacleManager {
    private static SpectacleManager _instance;
    private ArticleDAO daoArticle;
    //****** Singleton  ************//

    public static SpectacleManager getInstance() throws BLLException {
        if (_instance == null) _instance = new SpectacleManager();
        return _instance;
    }
    private SpectacleManager() throws BLLException {
        try {
            daoArticle = DAOFactory.getArticleFactory();
        } catch (DALException e) {
            e.printStackTrace();
        }
    }
    //****************//


    public void addArticle(Article art) throws BLLException {
       // on ne peut pas inserer un article qui exixte deja.
        if (art.getIdArticle() != null){
              try {
                this.validerArticle(art);
                daoArticle.insert(art);
            } catch (DALException e) {
                e.printStackTrace();
            }
          }

    }

    public List<Article> getCatalogue() throws BLLException {
        List<Article> data = null;
        try {
             data = daoArticle.selectAll();
        } catch (DALException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void updateArticle(Article art) throws BLLException {
        this.validerArticle(art);
        try {
            daoArticle.update(art);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void removeArticle(Article art) throws BLLException {
        try {
            daoArticle.delete(art.getIdArticle());
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void validerArticle(Article art) throws BLLException {
        if (art == null) throw new BLLException("Article null");


        if (art.getQteStock()< 0) throw new BLLException("Article invalide : la quantité de stock doit être supérieur à 0");

        if (art instanceof Ramette) {
            if (((Ramette) art).getGrammage() < 0) {
                throw new BLLException("Article invalide : le grammage doit être supérieur à 0");
            }
        }
        if (art instanceof Stylo){
            if (((Stylo) art).getCouleur() == null)  throw new BLLException("Article invalide : la couleur doit être précisée");
        }

    }

    public Article getArticle(int index) throws BLLException {
        if (index < 0) throw new BLLException("index negatif");
        List<Article> cata = this.getCatalogue();
        return cata.get(index);
    }


}
