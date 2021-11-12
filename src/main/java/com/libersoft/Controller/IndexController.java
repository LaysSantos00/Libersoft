package com.libersoft.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	
	
	
	//pagina para home do aluno


	
}
