import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.sputnik.Carro;
import org.sputnik.Estacionamento;

public class Teste {

	@Test
    public void test() {
		
		Estacionamento estacionamento = new Estacionamento();
		estacionamento.addCarro(new Carro("gol", "ABC1234"));
		estacionamento.addCarro(new Carro("fox", "ABC3456"));
		
		assertEquals(estacionamento.getCarros().size(), 2);
		
		Carro carro = estacionamento.buscarCarroPlaca("ABC1234");
		
		assertEquals(carro.getModelo(), "gol");
		
	}

}
