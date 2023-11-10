package cl.sodexo.backend.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import cl.sodexo.backend.news.model.New;
import cl.sodexo.backend.news.model.NewResponseRest;

public interface INewService {
	
	public ResponseEntity<NewResponseRest> save(New news);
	public Page<New> findByTitle(String title, Pageable pageable);
	public Page<New> findAll(Pageable pageable);
	public ResponseEntity<NewResponseRest> deleteById(Long id);

}
