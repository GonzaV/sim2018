import java.util.*;

public class ControladorDeTiempo {

    private GeneradorRandom genRandom = new GeneradorRandom();
    private List<Double> listaDeProximoDesperdicio = new ArrayList<>();
    private List<Double> listaDeTiempoProxPedido = new ArrayList<>();

    private double tiempoActual = 0;
    private double tiempoTotal = 0;


    public void setTiempoActual(double tiempo){

        tiempoActual = tiempo;

    }

    public void setTiempoTotal(double tiempo){

        tiempoTotal = tiempo;

    }

    public double getTiempoActual(){

        return tiempoActual;

    }

    public double getTiempoTotal(){

        return tiempoTotal;

    }

    public List<Double> getListaDeTiempoProxPedido() {
        return listaDeTiempoProxPedido;
    }

    public List<Double> getListaDeProximoDesperdicio() {
        return listaDeProximoDesperdicio;
    }

    public double getMenorListaDouble(List<Double> lista){

        int indexMenor = lista.indexOf(Collections.min(lista));
        return lista.get(indexMenor);
    }

    public double getMenorTppec(){

        return getMenorListaDouble(getListaDeTiempoProxPedido());

    }

    public double getMenorTpd(){

        return getMenorListaDouble(getListaDeProximoDesperdicio());

    }

    public double getTpll(){

        return genRandom.generarIA() + tiempoActual;

    }
}
