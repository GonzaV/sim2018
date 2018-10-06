import java.util.HashMap;
import java.util.Scanner;

public class Main {


    public static void main(String args[]){

        GeneradorDeTiempos genTiempos = new GeneradorDeTiempos();
        float tppec = 0; //Tiempo proxima preparacion estilo cerveza
        int duracionEnDias = 0;
        Scanner sc = new Scanner(System.in);
        HashMap<String,Integer> mapaDeCantidades = new HashMap<>();
        HashMap<String,Integer> mapaDeDiasEntrePedidos = new HashMap<>();

        System.out.println("Ingrese cantidad de cerveza del tipo Ipa que desea comprar en cada pedido");
        mapaDeCantidades.put("ipa", sc.nextInt());
        System.out.println("Ingrese cantidad de cerveza del tipo Stout que desea comprar en cada pedido");
        mapaDeCantidades.put("stout", sc.nextInt());
        System.out.println("Ingrese cantidad de cerveza del tipo Scottish que desea comprar en cada pedido");
        mapaDeCantidades.put("scottish", sc.nextInt());
        System.out.println("Ingrese cantidad de cerveza del tipo Rubia que desea comprar en cada pedido");
        mapaDeCantidades.put("rubia", sc.nextInt());

        System.out.println("Ingrese cada cuantos dias se hara un nuevo pedido de Ipa");
        mapaDeDiasEntrePedidos.put("ipa", sc.nextInt());
        System.out.println("Ingrese cada cuantos dias se hara un nuevo pedido de Stout");
        mapaDeDiasEntrePedidos.put("stout", sc.nextInt());
        System.out.println("Ingrese cada cuantos dias se hara un nuevo pedido de Scottish");
        mapaDeDiasEntrePedidos.put("scottish", sc.nextInt());
        System.out.println("Ingrese cada cuantos dias se hara un nuevo pedido de Rubia");
        mapaDeDiasEntrePedidos.put("rubia", sc.nextInt());

        System.out.println("Ingrese la duracion en dias de la simulacion, debe ser mayor a 180");
        duracionEnDias = sc.nextInt();

        while(duracionEnDias <= 180){

            System.out.println("El valor debe ser mayor a 180, por favor ingrese otro valor");
            duracionEnDias = sc.nextInt();

        }

        tppec = genTiempos.proximoTiempoPreparacion();









    }

}
