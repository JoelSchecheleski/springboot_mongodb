package com.uppertools.workshop.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uppertools.workshop.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		User joel = new User(1L, "Joel Schecheleski", "joel.jsp@gmail.com");
		User katia = new User(2L, "Kátia Michele Jurk Avalos Schecheleski", "kmjavalos@gmail.com");
		User miguel = new User(3L, "Miguel Avalos Schecheleski", "miguelp@gmail.com");
		User raquel = new User(3L, "Raquel Avalos Schecheleski", "raquel@gmail.com");

		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(joel, katia, miguel, raquel));
		return ResponseEntity.ok(list);
	}

}
