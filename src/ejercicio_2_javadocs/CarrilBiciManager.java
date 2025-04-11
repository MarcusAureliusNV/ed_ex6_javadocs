package ejercicio_2_javadocs;

/**
 * aaaa
 */
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CarrilBiciManager {
    /**
     * Este código permite la búsqueda de rutas de carriles bici que existen. Si no se añade un nombre, o es un nombre erróneo, o no se añade los Km, salta error.
     * @author Empresa desarrolladora CádizTech
     * @version 2.4.0
     *
     * Para más información visitar la página indicada @link https://institucional.cadiz.es/area/Plan-de-Movilidad-Urbana-Sostenible/2021
     */

    /*
        @param tramos indica el nombre y la longitud de la ruta.
        @param estadoTramos indica el estado en el que está. No puede ser el mismo nombre que @link tramos
     */
    private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
    private final Map<String, String> estadoTramos; // nombre del tramo -> estado

    /**
     * La clase principal @link CarrilBiciManager crea mapas para poder compararlos con la información dada por teclado.
     */
    public CarrilBiciManager() {
        this.tramos = new HashMap<>();
        this.estadoTramos = new HashMap<>();
    }

    /**
     *
     * @param nombre tiene que ser no nula o da fallo
     * @param longitud tiene que ser no nula y no cero
     *                 Si alguna de estas dos condiciones no se cumple, salta error
     */
    public void añadirTramo(String nombre, double longitud) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
        }
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que cero");
        }
        tramos.put(nombre, longitud);
        estadoTramos.put(nombre, "En servicio");
    }

    /**
     * actualizarEstado mira si el tramo existe y le añade ese nombre y estado.
     *
     * @param nombre mira si @see tramos contiene el nombre añadido por teclado
     * @param nuevoEstado si no salta el error, se le añade a @link estadoTramos un nuevo valor
     * @throws NoSuchElementException si no detecta el @param nombre
     */
    public void actualizarEstado(String nombre, String nuevoEstado) {
        if (!tramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
        }
        estadoTramos.put(nombre, nuevoEstado);
    }

    /**
     * Cambia el nombre del método.
     * @param nombre nombre del estado
     * @param estado indica su estado, por ejemplo En servicio
     */
    public void cambiarEstado(String nombre, String estado) {
        actualizarEstado(nombre, estado);
    }

    /**
     * Consulta si existe el nombre y si es así, le da a @see estadoTramos ese nombre
     * @param nombre Nombre del tramo
     * @return devuelve el nombre del estado del tramo.
     * @throws NoSuchElementException si no existe el tramo indicado.
     */
    public String consultarEstado(String nombre) {
        if (!estadoTramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe");
        }
        return estadoTramos.get(nombre);
    }

    /**
     *  Crea un método que genera un mapa con el valor de la longitud de la ruta
     * @param longitudTotal contiene el valor de los tramos
     * @return devuelve dicho valor
     */
    public double longitudTotal() {
        return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Crea un mapa mediante una colección
     * @return le da el valor de la colección de los tramos a Map
     */
    public Map<String, Double> obtenerTramos() {
        return Collections.unmodifiableMap(tramos);
    }

    /**
     * Genera el informe que muestra por pantalla.
     * @link generarInforme junta todos los métodos y los une a @param sb.append
     * @return le añade el String generado por @see sb.append
     */
    public String generarInforme() {
        StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
        sb.append("===========================================\n");
        for (String nombre : tramos.keySet()) {
            sb.append("- ").append(nombre).append(" (")
              .append(tramos.get(nombre)).append(" km): ")
              .append(estadoTramos.get(nombre)).append("\n");
        }
        sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
        return sb.toString();
    }
}


