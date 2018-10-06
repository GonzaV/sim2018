public class GeneradorDeTiempos {

    public float ultimoTiempoPreparacion = 0;

    public float proximoTiempoPreparacion(){

        ultimoTiempoPreparacion += 1.23;

        int horas = (int) ultimoTiempoPreparacion;
        float minutos = ultimoTiempoPreparacion - horas;

        if(minutos >= 0.60){

            horas += 1;
            minutos -= 60;

            ultimoTiempoPreparacion = horas + minutos;

            return ultimoTiempoPreparacion;

        }
        else {

            return ultimoTiempoPreparacion;

        }

    }

}
