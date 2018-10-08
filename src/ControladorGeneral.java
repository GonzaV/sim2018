import javafx.scene.control.TextInputControl;

import java.util.*;

public class ControladorGeneral {

    /*
    private int INDICEIPA = 0;
    private int INDICESTOUT = 1;
    private int INDICESCOTTISH = 2;
    private int INDICERUBIA = 3;
    */

    private double ccs = 0;
    private double menorTppec = 0;
    private double menorTpd = 0;
    private double tpll = 0;

    private ControladorDeTiempo contTiempo = new ControladorDeTiempo();
    private Scanner sc = new Scanner(System.in);
    private GeneradorRandom genRandom = new GeneradorRandom();

    private List<Double> listaDeArrepentidos = Arrays.asList(0.0,0.0,0.0,0.0);
    private List<Double> cantidadTotalClientes = Arrays.asList(0.0,0.0,0.0,0.0);

    private List<Double> listaDeLitrosHistoricos = Arrays.asList(0.0,0.0,0.0,0.0);
    private List<Double> listaDeLitrosDescartadosHistoricos = Arrays.asList(0.0,0.0,0.0,0.0);

    private List<Double> listaDeProximoVencimientoStockNuevo = Arrays.asList(0.0,0.0,0.0,0.0);

    private List<Double> listaDeStockNuevo = new ArrayList<>();
    private List<Double> listaDeStockViejo = new ArrayList<>();

    private List<Double> listaDeCantidades = new ArrayList<>();
    private List<Double> listaDiasEntrePedidos = new ArrayList<>();

    private List<String> nombresEstilos = Arrays.asList("Ipa", "Stout", "Scottish", "Rubia");

    int i = 0;

    public void correrAlgoritmoPrincipal() {

        i++;
        System.out.println(i);

        menorTppec = contTiempo.getMenorTppec();

        menorTpd = contTiempo.getMenorTpd();

        if (menorTppec <= menorTpd) {

            if (menorTppec <= tpll) {

                this.simularPedido();

            } else {

                this.simularLlegadaCliente();

            }
        } else {

            if (tpll <= menorTpd) {

                this.simularLlegadaCliente();

            } else {

                this.simularVencimiento();

            }
        }

        chequearTiempoFinal();
    }

    private void simularPedido() {

        int cervezaPedida = contTiempo.getListaDeTiempoProxPedido().indexOf(menorTppec);

        System.out.println("Nueva reposición de stock de " + this.nombresEstilos.get(cervezaPedida));

        //Si todos los elementos tienen el mismo valor, tira -1
        if(cervezaPedida == -1){
             cervezaPedida = 0;
        }

        contTiempo.setTiempoActual(menorTppec);

        //Aca hago TPPEC(i) <- T + N(i), es decir, T mas dias para la prox compra de (i)
        contTiempo.getListaDeTiempoProxPedido().set(cervezaPedida, contTiempo.getTiempoActual() + listaDiasEntrePedidos.get(cervezaPedida));

        //EL QUE ERA STOCK NUEVO AHORA ES VIEJO
        listaDeStockViejo.set(cervezaPedida, listaDeStockNuevo.get(cervezaPedida));

        //ACTUALIZO EL STOCK NUEVO DE (i) CON LO COMPRADO. RECORDAR QUE EL QUE ERA NUEVO PASO A SER VIEJO POR LA COMPRA RECIENTE
        listaDeStockNuevo.set(cervezaPedida, listaDeCantidades.get(cervezaPedida));

        //ACTUALIZO EL TOTAL HISTORICO DE CERVEZA MANEJADA
        listaDeLitrosHistoricos.set(cervezaPedida, listaDeLitrosHistoricos.get(cervezaPedida) + listaDeCantidades.get(cervezaPedida));

        //GENERO EL PROXIMO VENCIMIENTO PARA EL STOCK QUE ACABO DE REPONER
        double vencimiento = genRandom.generarTPD(cervezaPedida);

        //GUARDO EN LA LISTA AUXILIAR ESTE PROXIMO VENCIMIENTO PARA NO PISAR EL VENCIMIENTO DEL STOCK ANTERIOR
        this.listaDeProximoVencimientoStockNuevo.set(cervezaPedida, contTiempo.getTiempoActual() + vencimiento);
    }

    private void simularVencimiento() {

        int cervezaVencida = contTiempo.getListaDeProximoDesperdicio().indexOf(menorTpd);

        System.out.println("Vencimiento de la cerveza " + this.nombresEstilos.get(cervezaVencida));

        contTiempo.setTiempoActual(menorTpd);

        //ACUMULO POR CADA ESTILO DE CERVEZA EL TOTAL DE LITROS DESCARTADOS
        listaDeLitrosDescartadosHistoricos.set(cervezaVencida, this.listaDeLitrosDescartadosHistoricos.get(cervezaVencida) + this.listaDeStockViejo.get(cervezaVencida));

        //SE VENCE EL STOCK VIEJO
        this.listaDeStockViejo.set(cervezaVencida, 0.0);

        //EL PROXIMO DESPERDICIO ES EL DEL STOCK QUE HASTA EL MOMENTO ES EL NUEVO
        contTiempo.getListaDeProximoDesperdicio().set(cervezaVencida, this.listaDeProximoVencimientoStockNuevo.get(cervezaVencida));
    }

    private void simularLlegadaCliente() {

        System.out.println("Atendiendo un cliente...");

        int cervezaElegida = genRandom.generarEleccionCerveza();

        contTiempo.setTiempoActual(tpll);

        tpll = contTiempo.getTpll();

        ccs = genRandom.generarCCS();

        this.cantidadTotalClientes.set(cervezaElegida, this.cantidadTotalClientes.get(cervezaElegida) + 1);

        if (listaDeStockViejo.get(cervezaElegida) + listaDeStockNuevo.get(cervezaElegida) >= ccs) {

            if (listaDeStockViejo.get(cervezaElegida) > 0) {

                if (listaDeStockViejo.get(cervezaElegida) >= ccs) {

                    listaDeStockViejo.set(cervezaElegida, listaDeStockViejo.get(cervezaElegida) - ccs);

                } else {

                    listaDeStockNuevo.set(cervezaElegida, listaDeStockNuevo.get(cervezaElegida) + listaDeStockViejo.get(cervezaElegida) - ccs);
                    listaDeStockViejo.set(cervezaElegida, 0.0);

                }
            } else {

                listaDeStockNuevo.set(cervezaElegida, listaDeStockNuevo.get(cervezaElegida) - ccs);

            }
        } else {

            System.out.println("El cliente no pudo ser atendido por falta de cerveza!!! " + "Tipo" + cervezaElegida);
            listaDeArrepentidos.set(cervezaElegida, listaDeArrepentidos.get(cervezaElegida) + 1);

        }
    }

    private void chequearTiempoFinal() {

        if (contTiempo.getTiempoActual() >= contTiempo.getTiempoTotal()) {

            this.calculoEImpresionDeResultados();

        } else {

            correrAlgoritmoPrincipal();

        }
    }

    private void calculoEImpresionDeResultados() {

        for(int i = 0; i < 4; i++) {

            double porcentajeVentasNoConcretadas = 0.0;
            double porcentajeArrepentimientos = 0.0;

            System.out.println("-----------------------------------------------");
            System.out.println("Resultados del estilo de cerveza " + this.nombresEstilos.get(i) + ":\n");

            for(int i2 = 0; i2 < listaDeLitrosDescartadosHistoricos.size(); i2++){
                System.out.println(listaDeLitrosDescartadosHistoricos.get(i2));
            }
            for(int i2 = 0; i2 < listaDeLitrosHistoricos.size(); i2++){
                System.out.println(listaDeLitrosHistoricos.get(i2));
            }
            porcentajeVentasNoConcretadas += (listaDeArrepentidos.get(i) / cantidadTotalClientes.get(i)) * 100;
            porcentajeArrepentimientos += (listaDeLitrosDescartadosHistoricos.get(i) / listaDeLitrosHistoricos.get(i)) *100;

            System.out.println("Porcentaje de ventas no concretadas: " + porcentajeVentasNoConcretadas);
            System.out.println("Porcentaje de arrepentimientos: " + porcentajeArrepentimientos);

            System.out.println("Variables de control:\n");

            System.out.println("Cantidad de litros de cerveza a preparar: " + this.listaDeCantidades.get(i));
            System.out.println("Cantidad de días entre un pedido y el siguiente : " + this.listaDiasEntrePedidos.get(i));
        }
    }

    public void configInicial() {
        System.out.println("Ingrese cantidad de cerveza del tipo Ipa que desea comprar en cada pedido");
        listaDeCantidades.add(sc.nextDouble());
        System.out.println("Ingrese cantidad de cerveza del tipo Stout que desea comprar en cada pedido");
        listaDeCantidades.add(sc.nextDouble());
        System.out.println("Ingrese cantidad de cerveza del tipo Scottish que desea comprar en cada pedido");
        listaDeCantidades.add(sc.nextDouble());
        System.out.println("Ingrese cantidad de cerveza del tipo Rubia que desea comprar en cada pedido");
        listaDeCantidades.add(sc.nextDouble());

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

        while (duracionEnDias <= 1) {

            System.out.println("El valor debe ser mayor a 180, por favor ingrese otro valor");
            duracionEnDias = sc.nextDouble();

        }

        contTiempo.setTiempoTotal(duracionEnDias);

        inicializarListas();

        tpll = contTiempo.getTpll();

    }

    private void inicializarListas() {
        contTiempo.getListaDeProximoDesperdicio().add(genRandom.getDiasDuracionIpa());
        contTiempo.getListaDeProximoDesperdicio().add(genRandom.getDiasDuracionStout());
        contTiempo.getListaDeProximoDesperdicio().add(genRandom.getDiasDuracionScottish());
        contTiempo.getListaDeProximoDesperdicio().add(genRandom.getDiasDuracionRubia());

        listaDeStockViejo.add(10.0);
        listaDeStockViejo.add(10.0);
        listaDeStockViejo.add(10.0);
        listaDeStockViejo.add(10.0);

        listaDeStockNuevo.add(20.0);
        listaDeStockNuevo.add(20.0);
        listaDeStockNuevo.add(20.0);
        listaDeStockNuevo.add(20.0);
    }

}
