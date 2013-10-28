package com.sdzee.tutos.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "tuto_partie" )
public class Partie {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long           id;

    @NotNull( message = "{tuto.partie.titre.notnull}" )
    private String         titre;

    @NotNull( message = "{tuto.partie.bigtuto.notnull}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "bigtuto" )
    private BTMTChap       bigTuto;                 // côté "customer" du mapping d'une partie qui appartient à un big-tuto

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "partie" )
    private List<BTMTChap> chapitres;               // côté "owner" du mapping d'une partie qui contient des chapitres

    @NotNull( message = "{tuto.partie.position.notnull}" )
    private Integer        position;

    private Integer        difficulte;

    private String         introduction;

    private String         conclusion;

    @NotNull( message = "{tuto.partie.dateCreation.notnull}" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date           dateCreation;

    @NotNull( message = "{tuto.partie.dateDerniereModification.notnull}" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date           dateDerniereModification;

    @NotNull( message = "{tuto.partie.adresseIP.notnull}" )
    private String         adresseIP;

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

    public BTMTChap getBigTuto() {
        return bigTuto;
    }

    public void setBigTuto( BTMTChap bigTuto ) {
        this.bigTuto = bigTuto;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition( Integer position ) {
        this.position = position;
    }

    public Integer getDifficulte() {
        return difficulte;
    }

    public void setDifficulte( Integer difficulte ) {
        this.difficulte = difficulte;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction( String introduction ) {
        this.introduction = introduction;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion( String conclusion ) {
        this.conclusion = conclusion;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation( Date dateCreation ) {
        this.dateCreation = dateCreation;
    }

    public Date getDateDerniereModification() {
        return dateDerniereModification;
    }

    public void setDateDerniereModification( Date dateDerniereModification ) {
        this.dateDerniereModification = dateDerniereModification;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP( String adresseIP ) {
        this.adresseIP = adresseIP;
    }

    public List<BTMTChap> getChapitres() {
        return chapitres;
    }

    public void setChapitres( List<BTMTChap> chapitres ) {
        this.chapitres = chapitres;
    }
}
