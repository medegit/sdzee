package com.sdzee.forums.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.JoinFetch;

import com.sdzee.membres.entities.Member;

/**
 * Post est l'entité JPA décrivant la table des réponses aux sujets. La validation des champs s'appuie sur la JSR-303.
 * 
 * @author Médéric Munier
 * @version %I%, %G%
 */
@Entity
@Table( name = "forum_post" )
public class Post implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long    id;

    @NotNull( message = "{forums.post.author.notnull}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "author" )
    @JoinFetch
    private Member  author;

    @NotNull( message = "{forums.post.topic.notnull}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "topic" )
    @JoinFetch
    private Topic   topic;

    @NotNull( message = "{forums.post.text.notnull}" )
    private String  text;

    @Temporal( TemporalType.TIMESTAMP )
    private Date    lastEditDate;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "lastEditBy" )
    @JoinFetch
    private Member  lastEditBy;

    @NotNull( message = "{forums.post.creationDate.notnull}" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date    creationDate;

    private Integer upVotes   = 0;

    private Integer downVotes = 0;

    @NotNull( message = "{forums.post.ipAddress.notnull}" )
    private String  ipAddress;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Member getAuthor() {
        return author;
    }

    public void setAuthor( Member author ) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic( Topic topic ) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate( Date lastEditDate ) {
        this.lastEditDate = lastEditDate;
    }

    public Member getLastEditBy() {
        return lastEditBy;
    }

    public void setLastEditBy( Member lastEditBy ) {
        this.lastEditBy = lastEditBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate ) {
        this.creationDate = creationDate;
    }

    public Integer getUpVotes() {
        return upVotes;
    }

    public void setUpVotes( Integer upVotes ) {
        this.upVotes = upVotes;
    }

    public void addUpVote() {
        this.upVotes++;
    }

    public void removeUpVote() {
        this.upVotes--;
    }

    public Integer getDownVotes() {
        return downVotes;
    }

    public void setDownVotes( Integer downVotes ) {
        this.downVotes = downVotes;
    }

    public void addDownVote() {
        this.downVotes++;
    }

    public void removeDownVote() {
        this.downVotes--;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress( String ipAddress ) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return String.format( "Post[%d]", id );
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Post other = (Post) obj;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        return true;
    }
}
