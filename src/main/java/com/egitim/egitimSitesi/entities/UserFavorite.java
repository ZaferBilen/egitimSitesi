package com.egitim.egitimSitesi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_favorites")
public class UserFavorite {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @ManyToOne
    @JoinColumn(name = "ouruser_id")
    private OurUser ourUser;

    @ManyToOne
    @JoinColumn(name = "lesson_id" , unique = true)
    private Lesson lesson;

}

