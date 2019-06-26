package academico.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import academico.model.entities.Estudiante;

/**
 * Session Bean implementation class ManagerEstudiante
 */
@Stateless
@LocalBean
public class ManagerEstudiante {
   @PersistenceContext
   private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerEstudiante() {
        // TODO Auto-generated constructor stub
  
    }
    public List<Estudiante> findAllEstudiantes(){
    	String consulta="select o from Estudiante o order by o.apellidos";
    	Query q= em.createQuery(consulta,Estudiante.class);
    	return q.getResultList();
    }
    public Estudiante findEstudiantebyCedula(String cedula) {
    	return em.find(Estudiante.class, cedula);
    	
    }
    public void insertarestudiante(Estudiante estudiante) throws Exception {
//    	if (estudiante.getEdad()>50) 
//    		throw new Exception("No se permite mas de 50 años");
    	if (findEstudiantebyCedula(estudiante.getCedula())!= null)
			throw new Exception("Ya existe la cedula ingresada");	    
	     	em.persist(estudiante); 	
    }
    public void eliminarestudiante(String cedula) {
    	Estudiante e = findEstudiantebyCedula(cedula);
    	if (e != null) 	
    	em.remove(e);
    }
    public void actualizarestudiante(Estudiante estudiante) throws Exception {
    	Estudiante e=findEstudiantebyCedula(estudiante.getCedula());
    	if (e==null)
    		throw new Exception("No existe el estudiante");
    	e.setApellidos(estudiante.getApellidos());
    	e.setNombres(estudiante.getNombres());
    	e.setEdad(estudiante.getEdad());
    	em.merge(e);
    	

	}
    
}
