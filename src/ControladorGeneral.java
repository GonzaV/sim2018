import java.util.*;

public class ControladorGeneral {

    private Scanner sc = new Scanner(System.in);
    private GeneradorRandom genRandom = new GeneradorRandom();
    private HashMap<String,Integer> mapaDeCantidades = new HashMap<>();
    private HashMap<String,Integer> mapaDeDiasEntrePedidos = new HashMap<>();
    private HashMap<String,Integer> mapaDeProximoDesperdicio = new HashMap<>();

    public void correrAlgoritmoPrincipal() {

        configInicial();

    }

    private void configInicial() {
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
        int duracionEnDias = sc.nextInt();

        while(duracionEnDias <= 180){

            System.out.println("El valor debe ser mayor a 180, por favor ingrese otro valor");
            duracionEnDias = sc.nextInt();

        }

        mapaDeProximoDesperdicio.put("rubia",genRandom.getDiasDuracionRubia());
        mapaDeProximoDesperdicio.put("stout",genRandom.getDiasDuracionStout());
        mapaDeProximoDesperdicio.put("scottish",genRandom.getDiasDuracionScottish());
        mapaDeProximoDesperdicio.put("ipa",genRandom.getDiasDuracionIpa());

    }

    public String getClaveMenorDiasDuracion(){

        int min = Collections.min(mapaDeProximoDesperdicio.values());
        String clave = null;

        for(Map.Entry entry: mapaDeProximoDesperdicio.entrySet()){
            if(min == (int)entry.getValue()){
                clave = (String)entry.getKey();
                break; //Porque es un mapa one to one
            }
        }

        return clave;

    }


}
