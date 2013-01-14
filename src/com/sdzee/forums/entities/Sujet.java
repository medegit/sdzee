package com.sdzee.forums.entities;

import java.sql.Timestamp;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sdzee.membres.entities.Membre;

@Entity
@Table( name = "forum_sujet" )
public class Sujet {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long                id;
    @NotNull( message = "{sujet.titre.notnull}" )
    private String              titre;
    @NotNull( message = "{sujet.auteur.notnull}" )
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "auteur" )
    private Membre              auteur;
    @NotNull( message = "{sujet.forum.notnull}" )
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "forum" )
    private Forum               forum;
    @NotNull( message = "{sujet.dateCreation.notnull}" )
    private Timestamp           dateCreation;
    private Boolean             ferme;
    private Boolean             sticky;
    private Integer             vues;
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "sujet" )
    private LinkedList<Reponse> reponses;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre( String titre ) {
        this.titre = titre;
    }

    public Membre getAuteur() {
        return auteur;
    }

    public void setAuteur( Membre auteur ) {
        this.auteur = auteur;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum( Forum forum ) {
        this.forum = forum;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation( Timestamp dateCreation ) {
        this.dateCreation = dateCreation;
    }

    public Boolean getFerme() {
        return ferme;
    }

    public void setFerme( Boolean ferme ) {
        this.ferme = ferme;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky( Boolean sticky ) {
        this.sticky = sticky;
    }

    public Integer getVues() {
        return vues;
    }

    public void setVues( Integer vues ) {
        this.vues = vues;
    }

    public LinkedList<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses( LinkedList<Reponse> reponses ) {
        this.reponses = reponses;
    }
}
