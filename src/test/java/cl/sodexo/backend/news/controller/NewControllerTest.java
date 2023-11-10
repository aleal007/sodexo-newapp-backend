package cl.sodexo.backend.news.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cl.sodexo.backend.news.model.New;
import cl.sodexo.backend.news.model.NewResponseRest;
import cl.sodexo.backend.news.service.INewService;

public class NewControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	NewController newController;
	
	@Mock
	private INewService service;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(newController).build();
	}
	
	@Test
	public void saveTest() {
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		New news = new New(Long.valueOf(1), "News tittle", 
				"news summary", "2023-11-10T12:55:46Z", 
				"2023-11-10T12:55:46Z", 
				"imageurl", 
				"2023-11-10T12:55:46Z");
		
		when(service.save(news)).thenReturn(new ResponseEntity<NewResponseRest>(HttpStatus.OK));
		
		ResponseEntity<NewResponseRest> respuesta = newController.save(news);
		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	@Test
	public void deleteTest() throws Exception {
		
		NewResponseRest newResponse = new NewResponseRest();
		
		newResponse.setMetadata("Response ok", "00", "Response successful");
		
		when(service.deleteById(Long.valueOf("1"))).thenReturn(new ResponseEntity<NewResponseRest>(newResponse, HttpStatus.OK));
		
		this.mockMvc.perform(delete("/api/v1/news/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());	
	}

}
