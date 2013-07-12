package com.nooblee.cantina.view;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.nooblee.cantina.business.CantinaBC;
import com.nooblee.cantina.business.ClienteBC;
import com.nooblee.cantina.business.EmpresaBC;
import com.nooblee.cantina.domain.Cantina;
import com.nooblee.cantina.domain.Empresa;
import com.nooblee.cantina.util.ImportacaoClientesFacade;

@ViewController
@PreviousView("./cliente_list.jsf")
public class ClienteImportMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteBC clienteBC;
	
	@Inject
	private CantinaBC cantinaBC;
	
	@Inject
	private EmpresaBC empresaBC;
	
	private String nomeArquivo;
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String executarImportacao() {
		ImportacaoClientesFacade imp = new ImportacaoClientesFacade(nomeArquivo, clienteBC);
		Cantina cantina = cantinaBC.load(1L);
		Empresa empresa = empresaBC.load(1L);
		try {
			imp.process(cantina, empresa);

			FacesMessage msg = new FacesMessage("Sucesso", nomeArquivo + " importado com sucesso!!!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro", "Erro na importação do arquivo [" + nomeArquivo + "]!!!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		
		return null;
	}
	
	@Transactional
	public void handleFileUpload(FileUploadEvent event) {  
		
		ImportacaoClientesFacade imp = new ImportacaoClientesFacade(event.getFile().getFileName(), this.clienteBC);
		Cantina cantina = cantinaBC.load(1L);
		Empresa empresa = empresaBC.load(1L);
		try {
			imp.process(cantina, empresa);

			FacesMessage msg = new FacesMessage("Sucesso", event.getFile().getFileName() + " importado com sucesso!!!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro", "Erro na importação do arquivo [" + event.getFile().getFileName() + "]!!!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    } 

}