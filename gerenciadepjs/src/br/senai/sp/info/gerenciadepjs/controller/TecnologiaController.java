package br.senai.sp.info.gerenciadepjs.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.gerenciadepjs.dao.ProjetoDAO;
import br.senai.sp.info.gerenciadepjs.dao.TecnologiaDAO;
import br.senai.sp.info.gerenciadepjs.model.Tecnologia;

@Controller
@RequestMapping("/app")
public class TecnologiaController {
	
	@Autowired
	private TecnologiaDAO dao;
	
	@Autowired
	private ProjetoDAO pjsDao;
	
	@GetMapping("/tecnologia")
	public String AbrirMenuTecnologias (Model model,
			@RequestParam (name = "pesquisa", required = false)String nome,
			@RequestParam (name = "sucesso", required = false)String sucesso,
			@RequestParam (name = "erro", required = false)String erro){ 

		if (nome != null) {
			model.addAttribute("tecnologias", dao.pesquisarPorNome(nome));
		}else {
			model.addAttribute("tecnologias", dao.buscarTodos());	
		}
		
		if (sucesso != null) {
			model.addAttribute("sucesso", "true");
		}
		
		if(erro != null) {
			model.addAttribute("erro", "true");
		}				
		return "tecnologia/menu";		
	}
	
	@GetMapping("/tecnologia/novo")
	public String abrirFormTecnologia(@RequestParam(name = "id", required = false)Long id, Model model) {
		
		if (id == null) {
			model.addAttribute("tecnologia", new Tecnologia());
		}else {
			model.addAttribute("tecnologia", dao.buscar(id));
		}	
		return "tecnologia/form";
	}
	
	
	@GetMapping("/tecnologia/deletar")
	public String deletar(@RequestParam(name = "id", required = true)Long id, Model model) {
		
		if(!pjsDao.buscarPorTecnologia(id).isEmpty()) {
			model.addAttribute("erro","true");
			return "redirect:/app/tecnologia";		
		}

		dao.deletar(dao.buscar(id));				
		return "redirect:/app/tecnologia";
	}

	@PostMapping("/tecnologia/salvar")
	public String salvar(@Valid Tecnologia tecnologia, BindingResult br, Model model) {
		
		if(dao.buscarPorNome(tecnologia.getNome()) != null && tecnologia.getId() == null) {
			br.addError(new FieldError("tecnologia", "nome", "O nome já existe"));
		}
		
		if(tecnologia.getNome().length() > 26){
			br.addError(new FieldError("tecnologia", "nome", "O nome ultrapassou o limite de caractéres"));
		}
		
		if(tecnologia.getNome() == null) {
			br.addError(new FieldError("tecnologia", "nome", "O campo 'Nome' está vazio"));
		}
		
		if(br.hasErrors()) {
			model.addAttribute("tecnologia", tecnologia);
			System.out.println("SALVAR TECNOLOGIA: "+br.getAllErrors());
			return "tecnologia/form";		
		}
		
		if(tecnologia.getId() == null){
			dao.persistir(tecnologia);
		}else {
			Tecnologia tecnologiaBanco = dao.buscar(tecnologia.getId());
			BeanUtils.copyProperties(tecnologia, tecnologiaBanco, "id");
			dao.alterar(tecnologiaBanco);
		}	
		
		model.addAttribute("sucesso", "true");
		return "redirect:/app/tecnologia";	
	}
}
