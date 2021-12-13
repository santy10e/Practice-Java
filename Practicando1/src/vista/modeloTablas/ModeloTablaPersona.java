package vista.modeloTablas;

 // @author Santiago Tene
import javax.swing.table.AbstractTableModel;
import lista.controlador.Lista;
import modelo.DatosPersona;
import modelo.Persona;


public class ModeloTablaPersona extends AbstractTableModel{
    

    private Lista<Persona> lista = new Lista();
    private Lista<DatosPersona> listado = new Lista<>();

    public Lista<DatosPersona> getListado() {
        return listado;
    }

    public void setListado(Lista<DatosPersona> listado) {
        this.listado = listado;
    }

    public Lista<Persona> getLista() {
        return lista;
    }

    private void listaPersonas() {
        listado = new Lista<>();
        for (int j = 0; j < lista.tamanio(); j++) {
            Persona c = lista.consultarDatoPosicion(j);
            for (int i = 0; i < c.getPersonaLista().tamanio(); i++) {
                listado.insertarNodo(c.getPersonaLista().consultarDatoPosicion(i));
            }
        }
        System.out.println(listado.tamanio()+"OK");
    }

    public void setLista(Lista lista) {
        this.lista = lista;
        listaPersonas();
    }

    @Override
    public int getRowCount() {
        return listado.tamanio();
    }

    @Override
    public Object getValueAt(int i, int i1) {
        DatosPersona datos = listado.consultarDatoPosicion(i);
        switch (i1) {
            case 0:
                return datos.getNombre();
            case 1:
                return datos.getApellidos();
            case 2:
                return datos.getCedula();
            case 3:
                return datos.getDireccion();
               
            default:
                return null;
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Nombre";
            case 1:
                return "Apellido";
            case 2:
                return "Cedula";
            case 3:
                return "Dirección";
            default:
                return null;
        }
    }
    

}
