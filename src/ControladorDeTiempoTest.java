import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ControladorDeTiempoTest {

    private ControladorDeTiempo contTiempo;

    @Before
    public void setUp(){
        contTiempo = new ControladorDeTiempo();
    }

    @Test
    public void puedeDarElMinimoDeUnaListaDeDoubles(){
        double valor = contTiempo.getMenorListaDouble(Arrays.asList(1.1, 5.6, 6.9, 0.5, 1.8));
        assertEquals(0.5, valor, 0.01);
    }

    @Test
    public void siSonTodosIgualesDevuelveElPrimero(){
        List<Double> lista = Arrays.asList(1.1, 1.1, 1.1, 1.1, 1.1);
        int indexMenor = lista.indexOf(Collections.min(lista));
        assertEquals(0, indexMenor);
    }
}
