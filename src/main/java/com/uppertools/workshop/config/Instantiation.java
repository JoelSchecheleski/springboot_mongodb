package com.uppertools.workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import com.uppertools.workshop.domain.Post;
import com.uppertools.workshop.domain.User;
import com.uppertools.workshop.dto.AuthorDTO;
import com.uppertools.workshop.dto.CommentsDTO;
import com.uppertools.workshop.repository.PostRepository;
import com.uppertools.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		this.userRepository.deleteAll();
		User u1 = new User(null, "Joel Schecheleski", "joel.jsp@gmail.com");
		User u2 = new User(null, "Kátia Michele Jurk Avalos Schecheleski", "kmjavalos@gmail.com");
		User u3 = new User(null, "Miguel Schecheleski", "miguel@gmail.com");
		User u4 = new User(null, "Raquel Schecheleski", "raquel@gmail.com");
		this.userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));

		this.postRepository.deleteAll();
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo, abraços!", new AuthorDTO(u1));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(u2));

		CommentsDTO c1 = new CommentsDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(u1));
		CommentsDTO c2 = new CommentsDTO("Aproveite!", sdf.parse("21/03/2018"), new AuthorDTO(u2));
		CommentsDTO c3 = new CommentsDTO("Tenha um ótimo dia!", sdf.parse("21/03/2018"), new AuthorDTO(u3));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));

		this.postRepository.saveAll(Arrays.asList(post1, post2));

		u1.getPosts().addAll(Arrays.asList(post1, post2));
		this.userRepository.save(u1); // Atribuição dos pósts ao usuário Joel

	}

}
