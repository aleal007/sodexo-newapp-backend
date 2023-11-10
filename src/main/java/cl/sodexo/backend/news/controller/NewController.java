package cl.sodexo.backend.news.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.sodexo.backend.news.model.New;
import cl.sodexo.backend.news.model.NewResponseRest;
import cl.sodexo.backend.news.service.INewService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1")
public class NewController {
	
	@Autowired
	private INewService service;
	
	/**
	 * save news
	 * @param New
	 * @return
	 */
	@PostMapping("/news")
	public ResponseEntity<NewResponseRest> save(@RequestBody New news) {
		
		ResponseEntity<NewResponseRest> response = service.save(news);
		return response;
	}
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	@GetMapping("/news/page/{page}")
	public Page<New> getNews(@PathVariable Integer page){
		return service.findAll(PageRequest.of(page, 10));
	}
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	@GetMapping("/news/filter/{title}/page/{page}")
	public Page<New> searchNews(@PathVariable String title, @PathVariable Integer page){
		return service.findByTitle(title, PageRequest.of(page, 10));
	}
	
	/**
	 * delete news
	 * @param id
	 * @return
	 */
	@DeleteMapping("/news/{id}")
	public ResponseEntity<NewResponseRest> delete(@PathVariable Long id) {
		
		ResponseEntity<NewResponseRest> response = service.deleteById(id);
		return response;
	}
	
	

}
