package cl.sodexo.backend.news.model;

import cl.sodexo.backend.news.response.ResponseRest;
import lombok.Data;

@Data
public class NewResponseRest extends ResponseRest{
	
	private NewResponse newResponse = new NewResponse();

}
