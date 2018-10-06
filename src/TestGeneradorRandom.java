import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestGeneradorRandom {

    @Test
    public void generaLosNumerosEnElIntervalo(){

        GeneradorRandom generador = new GeneradorRandom();

        //Pruebo solo con rubia porque no me importan los maximos y minimos, el metodo se comporta igual sin importar los valores
        int duracionRubia = generador.getDiasDuracionRubia();

        assertTrue(generador.getValorMaximoRubia() >= duracionRubia, "Error, random is too high");
        assertTrue(generador.getValorMinimoRubia()  <= duracionRubia, "Error, random is too low");

    }

}
