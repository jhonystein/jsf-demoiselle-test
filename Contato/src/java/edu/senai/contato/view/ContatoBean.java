package edu.senai.contato.view;

import edu.senai.contato.model.Departamento;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author senai-sc
 */
@ManagedBean
public class ContatoBean {
    
    private String nome;
    private String email;
    private String telefone;
    private String mensagem;
    private Departamento departamento;
    private String tmpEmail;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String enviar() {
        //System.out.println("Valores: " + nome + "|" + departamento.getNome());
        email = tmpEmail;
        return null;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void validarEmail(FacesContext context, UIComponent componente, Object value) throws ValidatorException {
        String email = (String) value;
        if (!email.endsWith("@sc.senai.br")) {
            throw new ValidatorException(new FacesMessage("Erro no email!!!"));
        }
    }
    
    public void deduzirEmail(ValueChangeEvent e) {
        String nome = (String) e.getNewValue();
        tmpEmail = nome.toLowerCase().replace(' ', '.').concat("@sc.senai.br");
    }
}
