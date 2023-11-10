package cl.sodexo.backend.news.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import cl.sodexo.backend.news.dao.INewDao;
import cl.sodexo.backend.news.model.New;
import cl.sodexo.backend.news.model.NewResponseRest;

public class NewServiceImplTest {

	@InjectMocks
	NewServiceImpl service;
	
	@Mock
	INewDao newDao;
	
	@BeforeEach
	public void init() {
		
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	public void saveTest() {
		
		New news = new New(Long.valueOf(1), "News tittle", 
				"news summary", "2023-11-10T12:55:46Z", 
				"2023-11-10T12:55:46Z", 
				"imageurl", 
				"2023-11-10T12:55:46Z");
		
		when(newDao.save(news)).thenReturn(new New());
		
		ResponseEntity<NewResponseRest> response = service.save(news);
		
		assertEquals(1, response.getBody().getNewResponse().getNews().size());
		
		verify(newDao, times(1)).save(news);
		
	}
	
	
	@Test
	public void deleteTest() {
		

		ResponseEntity<NewResponseRest> response = service.deleteById(Long.valueOf(1));
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
		verify(newDao, times(1)).deleteById(Long.valueOf(1));
		
	}
}
