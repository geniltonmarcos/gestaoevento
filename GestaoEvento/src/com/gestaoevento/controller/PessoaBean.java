package com.gestaoevento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.gestaoevento.dao.PessoaDao;
import com.gestaoevento.dao.imp.PessoaDaoImp;
import com.gestaoevento.model.Localidade;
import com.gestaoevento.model.Pessoa;
import com.gestaoevento.util.FacesUtils;

@ManagedBean
@RequestScoped
public class PessoaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private PessoaDao pessoaDao;
	private List<Pessoa> pessoas;
	private FacesUtils facesUtils;
	private ArrayList<SelectItem> pessoasItens;
	
	private boolean alterando; 
		
	public PessoaBean() {
		// TODO Auto-generated constructor stub
		this.pessoa = new Pessoa();
		this.pessoaDao = null;
		this.pessoas = null;
		this.facesUtils = new FacesUtils(FacesContext.getCurrentInstance());
		this.alterando = false;
		this.pessoasItens = null;
	}
	
	public List<SelectItem> getPessoaItens() {
		try {
			list();
			//this.categoriaTitulosItens.add(si);
			pessoasItens = new ArrayList<SelectItem>();
			
			SelectItem si;// = new SelectItem(""," ");
			//categoriaTitulosItens.add(si);
			
			for(Pessoa pessoa : pessoas){
				if(pessoa.getId() != null && pessoa.getAtivo()){
					si = new SelectItem(pessoa, pessoa.getCargo() + " - " + pessoa.getNome());
					pessoasItens.add(si); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return pessoasItens;
	}
	
	public void save() {
		
		try {
			pessoaDao = new PessoaDaoImp();
			if(pessoa.getId() == null || (pessoa.getId() != null && pessoa.getId() == 0)){
				
				pessoaDao.save(pessoa);
			}
			else if(pessoa.getId() > 0)
				pessoaDao.update(pessoa);
			facesUtils.adicionaMensagemDeInformacao("Dados salvos com Sucesso");
			
			//this.pessoa = new Pessoa();
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "new";
		
	}
	
	public void delete() {
		try {
			pessoaDao = new PessoaDaoImp();
			pessoaDao.delete(pessoa);
			list();
			facesUtils.adicionaMensagemDeInformacao("Sucesso no Processamento do Formulário");
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "list";
	}
	
	public String list() {
		try {
			pessoa.setNome(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nome")
			);		
			
			pessoaDao = new PessoaDaoImp();
			this.pessoas = pessoaDao.list(pessoa);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return "list";
	}
	
	public  List<Pessoa> listaDireto(){
		try {
			pessoaDao = new PessoaDaoImp();
			return pessoaDao.list(pessoa);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return null;
	}
	
	public String preparaAlteracao() {
		this.alterando = true;
		try {
			String sid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
			//System.out.println("pessoa id: "+sid);
			Long id = Long.valueOf(sid);
			pessoaDao = new PessoaDaoImp();
			this.pessoa = pessoaDao.getPessoa(id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		
		return "form";
	}
	
	public String preparaAdicao(){
		pessoa = new Pessoa();
		return "form";
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public boolean isAlterando() {
		return alterando;
	}

	public void setAlterando(boolean alterando) {
		this.alterando = alterando;
	}
}
