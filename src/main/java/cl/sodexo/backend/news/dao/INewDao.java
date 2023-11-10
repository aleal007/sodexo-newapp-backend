package cl.sodexo.backend.news.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import cl.sodexo.backend.news.model.New;

public interface INewDao extends JpaRepository<New, Long>{
	
	@Query("select n from New n where n.title like %?1%")
	Page<New> findByTitleLike(String title, Pageable pageable);
	
	Page<New> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}
