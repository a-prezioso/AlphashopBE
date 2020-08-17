package com.xantrix.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xantrix.webapp.entity.Listini;

public interface ListinoRepository extends JpaRepository<Listini, String>
{
	@Query(value = "SELECT COUNT(*) as Qta FROM Listini a JOIN dettListini b ON a.id = b.idlist WHERE a.id = :idlist", nativeQuery = true)
	Long GetQtaDettList(@Param("idlist") String Listino);
}
