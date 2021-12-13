package lista.modelo;

 // @author Santiago Tene
import java.io.Serializable;


public class Nodo <T> implements Serializable{
    private T dato;
    private Nodo nodoSiguiente;

    public Nodo(T dato, Nodo nodoSiguiente) {
        this.dato = dato;
        this.nodoSiguiente = nodoSiguiente;
    }
    
    public Nodo(){
        this.dato=null;
        this.nodoSiguiente=null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(Nodo nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }
    
    

}
