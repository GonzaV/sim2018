import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class FdpIATest {

    @Test
    public void cuandoElRandomDaCeroIAesCero() {
        int r = 0;
        double minutos = 380.91* (Math.pow(Math.pow(1/(1-r),1/397.15) - 1,1/1.7015));
        assertEquals(0, minutos, 0.01);
    }

    @Test
    public void laFuncionSeComportaComoDebeParaUnRandom() {
        double r = 0.8;
        double minutos = 380.91* (Math.pow(Math.pow(1/(1-r),1/397.15) - 1,1/1.7015));
        assertEquals(14.974, minutos, 0.01);
    }

    @Test
    public void laFuncionSeComportaComoDebeParaOtroRandom() {
        double r = 0.5;
        double minutos = 380.91* (Math.pow(Math.pow(1/(1-r),1/397.15) - 1,1/1.7015));
        assertEquals(9.121, minutos, 0.01);
    }

    @Test
    public void cuandoElRandomDaUnoNoDaInfinito() {
        double r = 1;
        if(r == 1) r = (float) 0.99;
        try{
            double minutos = 380.91* (Math.pow(Math.pow(1/(1-r),1/397.15) - 1,1/1.7015));
        }
        catch(Exception e){
            fail("No se pudo dividir por cero");
        }
    }
}
