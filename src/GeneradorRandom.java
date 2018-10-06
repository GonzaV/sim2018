import java.util.Random;

public class GeneradorRandom {

    private int valorMinimoRubia = 20;
    private int valorMaximoRubia = 42;
    private int valorMinimoIpa = 15;
    private int valorMaximoIpa = 36;
    private int valorMinimoStout = 18;
    private int valorMaximoStout = 40;
    private int valorMinimoScottish = 23;
    private int valorMaximoScottish = 40;

    public int getDiasDuracionRubia(){

      return getDiasDuracion(valorMinimoRubia,valorMaximoRubia);

    }

    public int getDiasDuracionIpa(){

        return getDiasDuracion(valorMinimoIpa, valorMaximoIpa);

    }

    public int getDiasDuracionStout(){

        return getDiasDuracion(valorMinimoStout,valorMaximoStout);

    }

    public int getDiasDuracionScottish(){

        return getDiasDuracion(valorMinimoScottish,valorMaximoScottish);

    }

    public float getProbaProximaVenta(){

        Random random = new Random();
        //El metodo nextFloat() devuelve por def un numero entre 0.0 y 1.0 (conjunto cerrado)
        float probabilidad = random.nextFloat();

        return probabilidad;

    }

    private int getDiasDuracion(int valorMinimo,int valorMaximo){

        Random random = new Random();
        int diasDuracion = random.nextInt(valorMaximo - valorMinimo) + valorMinimo;

        return diasDuracion;

    }


    public int getValorMinimoRubia() {
        return valorMinimoRubia;
    }

    public int getValorMaximoRubia() {
        return valorMaximoRubia;
    }

    public int getValorMinimoIpa() {
        return valorMinimoIpa;
    }

    public int getValorMaximoIpa() {
        return valorMaximoIpa;
    }

    public int getValorMinimoStout() {
        return valorMinimoStout;
    }

    public int getValorMaximoStout() {
        return valorMaximoStout;
    }

    public int getValorMinimoScottish() {
        return valorMinimoScottish;
    }

    public int getValorMaximoScottish() {
        return valorMaximoScottish;
    }
}
