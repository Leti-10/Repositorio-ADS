import org.junit.Test;
import org.sputnik.ItemCrochet;
import org.sputnik.Loja;

import static org.junit.Assert.assertEquals;

public class Teste {

    @Test
    public void test(){

        Loja loja = new Loja();
        loja.addItem(new ItemCrochet("Agulha 3.0mm", 15.00, 23));
        loja.addItem(new ItemCrochet("Novelo amigurumi", 18.00, 50));
        loja.addItem(new ItemCrochet("Agulha de tapeçaria", 2.50, 120));

        loja.venderItem("Agulha de tapeçaria", 5);
        ItemCrochet item = loja.buscarItemPorNome("Agulha de tapeçaria");

        assertEquals(115, item.getQuantidade());
    }

}
