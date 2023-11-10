package cl.sodexo.backend.news.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.sodexo.backend.news.dao.INewDao;
import cl.sodexo.backend.news.model.New;
import cl.sodexo.backend.news.model.NewResponseRest;

@Service
public class NewServiceImpl implements INewService{

		
	@Autowired
	private INewDao newDao;

	@Override
	@Transactional
	public ResponseEntity<NewResponseRest> save(New news) {
		
		NewResponseRest response = new NewResponseRest();
		List<New> list = new ArrayList<>();
		
		try {
			
			New newSaved = newDao.save(news);
			
			if (newSaved != null) {
				list.add(newSaved);
				response.getNewResponse().setNews(list);
				response.setMetadata("Response ok", "00", "new saved");
			} else {
				response.setMetadata("Response nok", "-1", "new not saved");
				return new ResponseEntity<NewResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		} catch (Exception e) {
			
			response.setMetadata("Response nok", "-1", "Error to save new");
			return new ResponseEntity<NewResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<NewResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional (readOnly = true)
	public Page<New> findAll(Pageable pageable) {
		return newDao.findAll(pageable);
	}

	@Override
	@Transactional (readOnly = true)
	public Page<New> findByTitle(String title, Pageable pageable) {
		return newDao.findByTitleContainingIgnoreCase(title, pageable);
	}

	@Override
	@Transactional
	public ResponseEntity<NewResponseRest> deleteById(Long id) {
		NewResponseRest response = new NewResponseRest();
		try {
			
			newDao.deleteById(id);
			response.setMetadata("response ok", "00", "Register deleted");
			
			
		} catch (Exception e) {
			
			response.setMetadata("Response nok", "-1", "Error to delete it");
			e.getStackTrace();
			return new ResponseEntity<NewResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<NewResponseRest>(response, HttpStatus.OK);
		
	}

}
