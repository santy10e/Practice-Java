package controlador.usolista;

// @author Santiago Tene
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lista.controlador.Lista;
import modelo.DatosPersona;
import modelo.Persona;

public class PersonaController {

    private final String CARPETA = "datos" + File.separatorChar + Persona.class.getSimpleName() + ".obj";
    private Lista<Persona> listClientes = new Lista();
    private Persona persona;
    private Lista<DatosPersona> datosPersona = new Lista();
    private DatosPersona sitDatosPersona;

    public Lista<Persona> getListClientes() {
        return listClientes;
    }

    public void setListClientes(Lista<Persona> listClientes) {
        this.listClientes = listClientes;
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Lista<DatosPersona> getDatosPersona() {
        return datosPersona;
    }

    public void setDatosPersona(Lista<DatosPersona> datosPersona) {
        this.datosPersona = datosPersona;
    }

    public DatosPersona getSitDatosPersona() {
        if (sitDatosPersona == null) {
            sitDatosPersona = new DatosPersona();
        }
        return sitDatosPersona;
    }

    public void setSitDatosPersona(DatosPersona sitDatosPersona) {
        this.sitDatosPersona = sitDatosPersona;
    }

    public boolean guardar() {
        Lista<Persona> aux = listar();
        try {
            persona.setId(Long.valueOf((aux.tamanio() + 1)));
            aux.insertarNodo(persona);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA));
            oos.writeObject(aux);
            oos.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo guardar");
            return false;
        }
    }

    public boolean modificar() {
        Lista<Persona> aux = listar();
        try {
            for (int i = 0; i < aux.tamanio(); i++) {
                if (aux.consultarDatoPosicion(i).getId().intValue() == persona.getId().intValue()) {
                    aux.modificarPorPos(persona, i);
                    guardarArchivo(aux);
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo guardar");
            return false;
        }
    }

    private void guardarArchivo(Lista<Persona> aux) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA));
        oos.writeObject(aux);
        oos.close();
    }

    public boolean guardarSitio() {
        if (getPersona().getId() != null) {
            sitDatosPersona.setId(persona.getId());
            persona.getPersonaLista().insertarNodo(sitDatosPersona);
            Lista<Persona> aux = listar();
            int pos = -1;
            for (int i = 0; i < aux.tamanio(); i++) {
                if (aux.consultarDatoPosicion(i).getId().intValue() == persona.getId().intValue()) {
//                    aux.consultarDatoPosicion(i).setSitiosLista(cliente.getSitiosLista());
                    pos = i;
                    break;
                }
            }
            System.out.println("Posicion" + pos);
            if (pos >= 0) {
                try {
                    aux.modificarPorPos(getPersona(), pos);
                    guardarArchivo(aux);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return true;
        } else {
            return false;
        }
    }

    public Lista<Persona> listar() {
        Lista<Persona> lista = new Lista<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            lista = (Lista<Persona>) ois.readObject();
            ois.close();

        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
        return lista;

    }

}
