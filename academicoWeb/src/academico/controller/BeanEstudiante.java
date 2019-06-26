package academico.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import academico.model.entities.Estudiante;
import academico.model.manager.ManagerEstudiante;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanEstudiante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerEstudiante managerEstudiante;
	private List<Estudiante> listaEstudiantes;
	private Estudiante estudiante;
	private Estudiante estudianteSelecionado;
	private boolean panelColapsado;
	@PostConstruct
	public void inicializar() {
		listaEstudiantes= managerEstudiante.findAllEstudiantes();
		estudiante = new Estudiante();
	    panelColapsado=true;
	}
	public void actionListenerColapsarPanel() {
		panelColapsado=! panelColapsado;
	}
	public void actionListenerInsertEstudiante() {
		try {
			managerEstudiante.insertarestudiante(estudiante);
			listaEstudiantes=managerEstudiante.findAllEstudiantes();
			estudiante = new Estudiante();
			JSFUtil.crearMensajeInfo("Datos ingresados correctamente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarEstudiante(String cedula) {
		managerEstudiante.eliminarestudiante(cedula);
		listaEstudiantes= managerEstudiante.findAllEstudiantes();
		JSFUtil.crearMensajeInfo("Eliminado Correctamente");
	}
	public void actionListenerSeleccionarEstudiante(Estudiante estudiante) {
		estudianteSelecionado = estudiante ;
	}
	public void actionListenerActualizarEstudiante() {
		try {
			managerEstudiante.actualizarestudiante(estudianteSelecionado);
			listaEstudiantes = managerEstudiante.findAllEstudiantes();
			JSFUtil.crearMensajeInfo("Datos Modificados");
			
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}
	public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	public Estudiante getEstudianteSelecionado() {
		return estudianteSelecionado;
	}
	public void setEstudianteSelecionado(Estudiante estudianteSelecionado) {
		this.estudianteSelecionado = estudianteSelecionado;
	}
	

}
