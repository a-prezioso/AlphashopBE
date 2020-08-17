package com.xantrix.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xantrix.webapp.entities.Clienti;


public interface ClientiRepository extends JpaRepository<Clienti,String>
{
	Clienti findByCodfidelity(String CodFid);
	
	//Query SQL - Modifica monte bollini tabella Cards
	@Modifying
	@Query(value = "UPDATE cards SET bollini = bollini + (:bollini), ultimaspesa = now() WHERE codfidelity = :codfidelity", nativeQuery = true)
	void UpdMonteBollini(@Param("codfidelity") String CodFid, @Param("bollini") int Bollini);
		
}
