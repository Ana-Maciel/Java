package com.example.jogo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "/jogos")
@RestController
@RequestMapping(path = "/jogos", produces = MediaType.APPLICATION_JSON_VALUE)
public class JogoController {

	@Autowired
	JogoRepository jogoRepository;
	
	@GetMapping()
	public List<Jogo> lista() {
		return this.jogoRepository.findAll();
	}
	
	@PostMapping
	public Jogo guardar(@RequestBody Jogo jogo) {
		return this.jogoRepository.save(jogo);
	}
	
	@GetMapping("/{id}")
	public Jogo getJogo(@PathVariable Integer id){
		return  this.jogoRepository.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Integer id) {
		 this.jogoRepository.deleteById(id);
	}
}