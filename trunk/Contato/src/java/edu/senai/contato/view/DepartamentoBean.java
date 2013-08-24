package edu.senai.contato.view;

import edu.senai.contato.model.Departamento;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

/**
 *
 * @author senai-sc
 */
@ManagedBean
public class DepartamentoBean {
    
    public static List<Departamento> registros = new ArrayList<Departamento>();

    private Departamento bean = new Departamento();

    public Departamento getBean() {
        return bean;
    }

    public List<Departamento> getRegistros() {
        return registros;
    }
    
    public String salvar() {
        if (!registros.contains(bean)) {
            registros.add(bean);
        }
        bean = new Departamento();
        return "listagem";
    }
    
    public void salvarDepartamento(ActionEvent e) {
        salvar();
    }
    
}
