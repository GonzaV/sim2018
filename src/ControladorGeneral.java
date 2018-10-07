import java.util.*;

public class ControladorGeneral {

    private int INDICEIPA = 0;
    private int INDICESTOUT = 1;
    private int INDICESCOTTISH = 2;
    private int INDICERUBIA = 3;

    private double ccs = 0;
    private double menorTppec = 0;
    private double menorTpd = 0;
    private double tpll = 0;

    private ControladorDeTiempo contTiempo = new ControladorDeTiempo();
    private Scanner sc = new Scanner(System.in);
    private GeneradorRandom genRandom = new GeneradorRandom();

    private HashMap<String,Integer> mapaDeArrepentidos = new HashMap<>();
    private List<Double> listaDeLitrosHistoricos = new ArrayList<>();
    private List<Double> listaDeStockNuevo = new ArrayList<>();
    private List<Double> listaDeStockViejo = new ArrayList<>();
    private List<Double> listaDeCantidades = new ArrayList<>();
    private List<Double> listaDiasEntrePedidos = new ArrayList<>();



    public void correrAlgoritmoPrincipal() {

        if(menorTppec <= menorTpd){

            if(menorTppec <= tpll){

                int indiceAcambiar = contTiempo.getListaDeTiempoProxPedido().indexOf(menorTppec);

                contTiempo.setTiempoActual(menorTppec);

                //Aca hago TPPEC(i) <- T + N(i), es decir, T mas dias para la prox compra de (i)
                contTiempo.getListaDeTiempoProxPedido().set(indiceAcambiar,contTiempo.getListaDeTiempoProxPedido().get(indiceAcambiar) + listaDiasEntrePedidos.get(indiceAcambiar));

                //EL QUE ERA STOCK NUEVO AHORA ES VIEJO
                listaDeStockViejo.set(indiceAcambiar, listaDeStockViejo.get(indiceAcambiar) + listaDeStockNuevo.get(indiceAcambiar));

                //ACTUALIZO EL STOCK NUEVO DE (i) CON LO COMPRADO. RECORDAR QUE EL QUE ERA NUEVO PASO A SER VIEJO POR LA COMPRA RECIENTE
                listaDeStockNuevo.set(indiceAcambiar,listaDeCantidades.get(indiceAcambiar));

                //ACTUALIZO EL TOTAL HISTORICO DE CERVEZA MANEJADA
                listaDeLitrosHistoricos.set(indiceAcambiar, listaDeLitrosHistoricos.get(indiceAcambiar) + listaDeCantidades.get(indiceAcambiar));

                chequearTiempoFinal();

            }
            else{

                simularLlegadaCliente();

            }


        }
        else{

            if(tpll <= menorTpd){

                simularLlegadaCliente();

            }
            else{

                contTiempo.setTiempoActual(menorTpd);



            }

        }

    }

    private void simularLlegadaCliente() {

        int cervezaElegida = null;

        contTiempo.setTiempoActual(tpll);

        tpll = contTiempo.getTpll();

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
        listaDeCantidades.add(sc.nextDouble());
        System.out.println("Ingrese cantidad de cerveza del tipo Stout que desea comprar en cada pedido");
        listaDeCantidades.put(sc.nextDouble());
        System.out.println("Ingrese cantidad de cerveza del tipo Scottish que desea comprar en cada pedido");
        listaDeCantidades.put(sc.nextDouble());
        System.out.println("Ingrese cantidad de cerveza del tipo Rubia que desea comprar en cada pedido");
        listaDeCantidades.put(sc.nextDouble());

        double leido = 0;

        System.out.println("Ingrese cada cuantos dias se hara un nuevo pedido de Ipa");
        leido = sc.nextDouble();
        contTiempo.getListaDeTiempoProxPedido().add(leido);
        listaDiasEntrePedidos.add(leido);
        System.out.println("Ingrese cada cuantos dias se hara un nuevo pedido de Stout");
        leido = sc.nextDouble();
        contTiempo.getListaDeTiempoProxPedido().add(leido);
        listaDiasEntrePedidos.add(leido);
        System.out.println("Ingrese cada cuantos dias se hara un nuevo pedido de Scottish");
        leido = sc.nextDouble();
        contTiempo.getListaDeTiempoProxPedido().add(leido);
        listaDiasEntrePedidos.add(leido);
        System.out.println("Ingrese cada cuantos dias se hara un nuevo pedido de Rubia");
        leido = sc.nextDouble();
        contTiempo.getListaDeTiempoProxPedido().add(leido);
        listaDiasEntrePedidos.add(leido);

        System.out.println("Ingrese la duracion en dias de la simulacion, debe ser mayor a 180");
        double duracionEnDias = sc.nextDouble();

        while(duracionEnDias <= 180){

            System.out.println("El valor debe ser mayor a 180, por favor ingrese otro valor");
            duracionEnDias = sc.nextDouble();

        }

        contTiempo.setTiempoTotal(duracionEnDias);

        contTiempo.getListaDeProximoDesperdicio().add(genRandom.getDiasDuracionIpa());
        contTiempo.getListaDeProximoDesperdicio().add(genRandom.getDiasDuracionStout());
        contTiempo.getListaDeProximoDesperdicio().add(genRandom.getDiasDuracionScottish());
        contTiempo.getListaDeProximoDesperdicio().add(genRandom.getDiasDuracionRubia());

        menorTppec = contTiempo.getMenorTppec();

        menorTpd = contTiempo.getMenorTpd();

        tpll = contTiempo.getTpll();

    }


}
