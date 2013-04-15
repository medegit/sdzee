package com.sdzee.membres.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.sdzee.breadcrumb.beans.BreadCrumbHelper;
import com.sdzee.breadcrumb.beans.BreadCrumbItem;
import com.sdzee.membres.dao.MembreDao;
import com.sdzee.membres.entities.Membre;

@ManagedBean
@ViewScoped
public class ConnecterBean implements Serializable {
    private static final long   serialVersionUID           = 1L;
    private static final String PSEUDO                     = "pseudo";
    private static final String SESSION_MEMBRE             = "membre";
    private static final String PARAM_URL_ORIGINE          = "urlOrigine";
    private static final String PARAM_QUERY_STRING_ORIGINE = "queryStringOrigine";
    private static final String URL_PARAM_SEPARATEUR       = "?";
    private static final String PAGE_ACCUEIL               = "/accueil.jsf";
    private static final String TITRE_PAGE_CONNEXION       = "Connexion";

    private String              pseudo;
    private String              motDePasse;
    private String              connexionAuto;
    private String              urlOrigine;

    @EJB
    private MembreDao           membreDao;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        urlOrigine = externalContext.getRequestParameterMap().get( PARAM_URL_ORIGINE );
        String queryStringOrigine = externalContext.getRequestParameterMap().get( PARAM_QUERY_STRING_ORIGINE );

        if ( urlOrigine == null || urlOrigine.isEmpty() ) {
            urlOrigine = externalContext.getRequestContextPath() + PAGE_ACCUEIL;
        } else {
            if ( queryStringOrigine != null && !queryStringOrigine.isEmpty() ) {
                urlOrigine += URL_PARAM_SEPARATEUR + queryStringOrigine;
            }
        }
    }

    // Méthode d'action appelée lors du clic sur le bouton du formulaire
    // de connexion
    public void connecter() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        // On récupère le membre depuis la BDD
        Membre membre = membreDao.trouver( PSEUDO, pseudo );

        // On le met en session, puisque si on arrive ici c'est qu'on est
        // déjà passé par le validator associé au formulaire de connexion,
        // qui a vérifié que le pseudo et le mot de passe sont corrects !
        externalContext.getSessionMap().put( SESSION_MEMBRE, membre );

        // On redirige le membre vers la page qu'il voulait visiter
        externalContext.redirect( urlOrigine );
    }

    public String deconnecter() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        return "/accueil";
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse( String motDePasse ) {
        this.motDePasse = motDePasse;
    }

    public String getConnexionAuto() {
        return connexionAuto;
    }

    public void setConnexionAuto( String connexionAuto ) {
        this.connexionAuto = connexionAuto;
    }

    public List<BreadCrumbItem> getBreadCrumb() {
        String chemin = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        List<BreadCrumbItem> breadCrumb = BreadCrumbHelper.initBreadCrumb( chemin );
        BreadCrumbHelper.addItem( breadCrumb, TITRE_PAGE_CONNEXION, null );
        return breadCrumb;
    }
}