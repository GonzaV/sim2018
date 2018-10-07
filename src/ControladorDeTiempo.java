public class ControladorDeTiempo {

    private int tiempoActual = 0;
    private int tiempoTotal = 0;

    public void setTiempoActual(int tiempo){

        tiempoActual = tiempo;

    }

    public void setTiempoTotal(int tiempo){

        tiempoTotal = tiempo;

    }

    public int getTiempoActual(){

        return tiempoActual;

    }

    public int getTiempoTotal(){

        return tiempoTotal;

    }

}
