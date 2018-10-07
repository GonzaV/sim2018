import java.util.*;

public class ControladorGeneral {

    /*VARIABLES TEMPORALES A CAMBIAR*/
    private int tppec = 0;
    private int tpd = 0;
    private int tpll = 0;
    private int ia = 0;
    private int ccs = 0;
    /* ---------------------------- */
    private ControladorDeTiempo contTiempo = new ControladorDeTiempo();
    private Scanner sc = new Scanner(System.in);
    private GeneradorRandom genRandom = new GeneradorRandom();
    private HashMap<String,Integer> mapaDeArrepentidos = new HashMap<>();
    private HashMap<String,Integer> mapaDeLitrosHistoricos = new HashMap<>();
    private HashMap<String,Integer> mapaDeStockNuevo = new HashMap<>();
    private HashMap<String,Integer> mapaDeStockViejo = new HashMap<>();
    private HashMap<String,Integer> mapaDeCantidades = new HashMap<>();
    private HashMap<String,Integer> mapaDeDiasEntrePedidos = new HashMap<>();
    private HashMap<String,Integer> mapaDeProximoDesperdicio = new HashMap<>();

    public void correrAlgoritmoPrincipal() {

        //TOMO EL MENOR DE TPPEC, TPD, ETC, para simplificar ahora no lo busco.

        if(tppec <= tpd){

            if(tppec <= tpll){

                contTiempo.setTiempoActual(tppec);
                //Aca ago TPPEC(i) <- T + N(i), es decir, T mas dias para la prox compra de (i)
                tppec = contTiempo.getTiempoActual() + 1;

                /*
                EL QUE ERA STOCK NUEVO AHORA ES VIEJO, EN ESTE CASO, COMO EJEMPLO, DICE RUBIA
                mapaDeStockViejo.put("rubia",mapaDeStockNuevo.get("rubia"));
                 */

                /*
                ACTUALIZO EL STOCK NUEVO DE (i) CON LO COMPRADO, EN ESTE CASO, COMO EJEMPLO, DICE RUBIA
                RECORDAR QUE EL QUE ERA NUEVO PASO A SER VIEJO POR LA COMPRA RECIENTE
                mapaDeStockNuevo.put("rubia", cantidadComprada);
                 */

                /*
                ACTUALIZO EL TOTAL HISTORICO DE CERVEZA MANEJADA
                mapaDeLitrosHistoricos.put("rubia", mapaDeLitrosHistoricos.get("rubia") + cantidadComprada);
                 */



                chequearTiempoFinal();

            }
            else{

                simularLlegadaCliente();

            }


        }
        else{



        }

    }

    private void simularLlegadaCliente() {

        String cervezaElegida = null;

        contTiempo.setTiempoActual(tpll);

        ia = genRandom.generarIA();

        tpll = contTiempo.getTiempoActual() + ia;

        //EN ESTE METODO DEBERIA AUMENTAR CTC(i) EN 1
        cervezaElegida = genRandom.generarEleccionCerveza();

        ccs = genRandom.generarCCS();

        if(mapaDeStockViejo.get(cervezaElegida) + mapaDeStockNuevo.get(cervezaElegida) >= ccs){

            if(mapaDeStockViejo.get(cervezaElegida) > 0){

                if(mapaDeStockViejo.get(cervezaElegida) >= ccs){

                    mapaDeStockViejo.put(cervezaElegida,mapaDeStockViejo.get(cervezaElegida) - ccs);

                }
                else{

                    mapaDeStockNuevo.put(cervezaElegida,mapaDeStockNuevo.get(cervezaElegida) + mapaDeStockViejo.get(cervezaElegida) - ccs);
                    mapaDeStockViejo.put(cervezaElegida,0);

                }

            }
            else{

                mapaDeStockNuevo.put(cervezaElegida,mapaDeStockNuevo.get(cervezaElegida) - ccs);

            }

        }
        else{

            mapaDeArrepentidos.put(cervezaElegida,mapaDeArrepentidos.get(cervezaElegida) + 1);

        }

        chequearTiempoFinal();

    }

    private void chequearTiempoFinal() {

        if(contTiempo.getTiempoActual() >= contTiempo.getTiempoTotal()){

            //TODO

        }
        else{

            correrAlgoritmoPrincipal();

        }

    }



    public void configInicial() {
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

        contTiempo.setTiempoTotal(duracionEnDias);

        mapaDeProximoDesperdicio.put("rubia",genRandom.getDiasDuracionRubia());
        mapaDeProximoDesperdicio.put("stout",genRandom.getDiasDuracionStout());
        mapaDeProximoDesperdicio.put("scottish",genRandom.getDiasDuracionScottish());
        mapaDeProximoDesperdicio.put("ipa",genRandom.getDiasDuracionIpa());

        tppec = genRandom.generarTPPEC();

        tpd = genRandom.generarTPD();

        tpll = genRandom.generarTPLL();

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
