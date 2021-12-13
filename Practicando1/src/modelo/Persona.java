package modelo;

 // @author Santiago Tene
import java.io.Serializable;
import lista.controlador.Lista;


public class Persona implements Serializable{
    
    private Long id;
    private Lista<DatosPersona> personaLista = new Lista();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lista<DatosPersona> getPersonaLista() {
        return personaLista;
    }

    public void setPersonaLista(Lista<DatosPersona> personaLista) {
        this.personaLista = personaLista;
    }
    
    

}
