package cl.sodexo.backend.news.model;

import java.util.List;

import lombok.Data;

@Data
public class NewResponse {
	
	private List<New> news;

}
