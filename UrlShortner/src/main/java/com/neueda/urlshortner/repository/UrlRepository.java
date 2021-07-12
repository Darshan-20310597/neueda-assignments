package com.neueda.urlshortner.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neueda.urlshortner.model.*;


@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	
	public Url findByShortLink(String shortlink);


}
