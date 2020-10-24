package com.zeta.photos;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping(path = "/images")
public class PhotoController {

	private Logger LOGGER = LoggerFactory.getLogger(PhotoController.class);
	
	@Autowired
	private ImageRepository imagesRepo;
	
	@GetMapping(value="/{id}")      
	public ResponseEntity<?> generateRandomPics(@PathVariable("id") String id,
			HttpServletResponse response) throws IOException{
		String url = "https://loremflickr.com/320/240";
		HttpHeaders headers = new HttpHeaders();
		List<Image> imgRecords = new ArrayList<>();
		imagesRepo.findAll().forEach(imgRecords::add);
		LOGGER.info("imgRecords=> "+imgRecords);
//		for(Image img: imgRecords) {
//			if(img.getImageId()==Long.valueOf(id)) {
//				headers.setLocation(URI.create(img.getImageurl()));
//				return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
//			}
//		}
		
		headers.setLocation(URI.create(url));
		Image image = new Image();
		image.setImageId(Long.valueOf(id));
		 RestTemplate restTemplate = new RestTemplate();
		 String result = restTemplate.getForObject(url, String.class);
////restTemplate.exchange(requestEntity, responseType)
////
//RequestEntity  request =  RequestEntity.get(URI.create(url)).accept(MediaType.APPLICATION_JSON).build();
//		 ResponseEntity<String> 
		 
		 ResponseEntity<String> response1
		  = restTemplate.getForEntity(url, String.class) ;
		 System.out.println(response1.getHeaders());
		 
		 image.setImageurl("");
		// LOGGER.info("Imageurl=> "+result);
		imagesRepo.save(image);
		ResponseEntity<?> resp =  new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
		 LOGGER.info("resp=> "+resp.toString());
		 response.sendRedirect(url);
		 response.getHeaderNames();
		 LOGGER.info("resp=> "+response.getHeaderNames());
		
		return resp;
	}
}
