import java.util.Random;

public class GeneradorRandom {

    private Random random = new Random();

    public double getDiasDuracionRubia(){

      return 20*random.nextFloat() + 20;

    }

    public double getDiasDuracionIpa(){

        return 14*random.nextFloat() + 22;

    }

    public double getDiasDuracionStout(){

        return 12*random.nextFloat() + 25;

    }

    public double getDiasDuracionScottish(){

        return 15*random.nextFloat() + 18;

    }

    public double generarIA() {

       float r = random.nextFloat();
       if(r == 1) r = (float) 0.99;
       //Divido por 1440 para pasarlo a dias, un dia tiene 1440 minutos
       return (380.91* (Math.pow(Math.pow(1/(1-r),1/397.15) - 1,1/1.7015))) / 1440;

    }

    public int generarTPD(int indiceEstilo){

        Random random = new Random();

        return random.nextInt();

    }

    public float generarCCS(){

        return (random.nextFloat() + 2)/5;

    }

    public int generarEleccionCerveza(){

        Float resultadoRandom = random.nextFloat();

        if( resultadoRandom <= 0.55){
            return 0;
        } else{
            if(resultadoRandom <= 0.75){
                return 1;
            } else {
                if( resultadoRandom <= 0.9){
                    return 2;
                } else {
                    return 3;
                }
            }
        }
    }

}
