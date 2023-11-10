package cl.sodexo.backend.news.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="new")
public class New implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3298837734174984545L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Column(length=1024)
	private String summary;
	@Column(name= "published_at")
	private String publishedAt;
	@Column(name= "new_site")
	private String newsSite;
	@Column(name= "image_url")
	private String imageUrl;
	@Column(name = "saved_at")
	private String savedAt;
	

}
