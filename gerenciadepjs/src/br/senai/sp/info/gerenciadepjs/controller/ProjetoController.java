package br.senai.sp.info.gerenciadepjs.controller;

import java.util.ArrayList;
import java.util.List;

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
import br.senai.sp.info.gerenciadepjs.dao.StatusDAO;
import br.senai.sp.info.gerenciadepjs.dao.TecnologiaDAO;
import br.senai.sp.info.gerenciadepjs.model.Projeto;
import br.senai.sp.info.gerenciadepjs.model.Tecnologia;

@Controller
@RequestMapping("/app")
public class ProjetoController {
	
	@Autowired
	private ProjetoDAO dao;
	
	@Autowired
	private TecnologiaDAO daoTec;
	
	@Autowired
	private StatusDAO daoStatus;
	
	@GetMapping("/projeto")
	public String AbrirTelaProjetos (@RequestParam(name = "idStatus", required = false)Integer idStatus, 
									 @RequestParam(name = "idTec", required = false)Long id, 
									 @RequestParam(name = "pesquisa", required = false)String nome,
									 @RequestParam(name = "sucesso", required = false)String sucesso,
									 @RequestParam(name = "pagina", required = false)Integer pagina,
									 Model model) {
		
		if(id == null && nome == null) {
			model.addAttribute("projetos", dao.buscarTodos());	
		}
		
		if (sucesso != null) {
			model.addAttribute("sucesso", "true");
		}
		
		if (pagina != null) {
			model.addAttribute("pagina", pagina);
		}
		
		if(nome != null) {
			model.addAttribute("projetos", dao.pesquisarPorNome(nome));
		}
		
		if(id != null) {
			model.addAttribute("projetos", dao.buscarPorTecnologia(id));
		}
		
		if(idStatus != null) {
			model.addAttribute("projetos", dao.buscarPorStatus(idStatus));
		}
				
		return "projeto/menu";
	}
	
	@GetMapping("/projeto/novo")
	public String AbrirTelaNovoProjeto(Model model) {
				
		model.addAttribute("status", daoStatus.buscarTodos());
		model.addAttribute("tecnologias", daoTec.buscarTodos());
		model.addAttribute("projeto", new Projeto());
				
		return "projeto/form";
		
	}
	
	@GetMapping("/projeto/editar")
	public String AbrirEditarProjeto(@RequestParam(required = true)Long id, Model model) {
		
		model.addAttribute("status", daoStatus.buscarTodos());
		model.addAttribute("tecnologias", daoTec.buscarTodos());
		model.addAttribute("projeto", dao.buscar(id));
			
		return "projeto/form";
	}
	
	@GetMapping("/projeto/visualizar")
	public String AbrirVerProjeto(@RequestParam(required = true)Long id, Model model) {
		
		model.addAttribute("projeto", dao.buscar(id));
		
		return "projeto/view";
	}
	
	@PostMapping("/projeto/salvar")
	public String salvar(@Valid Projeto projeto, BindingResult brprojeto,
			@RequestParam(name = "tecnologias", required = true) Long[] tecnologiaId,
			Model model) {
		
		if (dao.buscarPorNome(projeto.getNome()) != null && dao.buscar(projeto.getId()) == null) {
			brprojeto.addError(new FieldError("projeto", "nome", "O nome já existe"));
		}
		
		if (brprojeto.hasErrors()) {
			System.out.print("ERROS CADASTRAR PROJETO: ");
			System.out.println(brprojeto.getAllErrors());
			model.addAttribute("status", daoStatus.buscarTodos());
			model.addAttribute("tecnologias", daoTec.buscarTodos());
			return "projeto/form";
		}
		
		List<Tecnologia> tecnologias = new ArrayList<>();
		for (int i = 0; i < tecnologiaId.length; i++) {		
			tecnologias.add(daoTec.buscar(tecnologiaId[i]));
		}
	
		projeto.setTecnologia(tecnologias);
				
		if (dao.buscar(projeto.getId()) == null) {
			dao.persistir(projeto);
		}else {
			Projeto projetoBanco = dao.buscar(projeto.getId());	
			BeanUtils.copyProperties(projeto, projetoBanco, "id");
			dao.alterar(projetoBanco);
		}	
		
		model.addAttribute("sucesso", "true");
		return "redirect:/app/projeto";
	}
	
	@GetMapping("/projeto/deletar")
	public String deletar(@RequestParam(name = "id", required = true)Long id) {
		dao.deletar(dao.buscar(id));
		return "redirect:/app/projeto";
	}	
}
