package ohtu.ohtuvarasto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10); // tilavuus on 10
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
		assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

	@Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysYliTilavuuden() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

	@Test
	public void negLisays() {
		varasto.lisaaVarastoon(-2);
		assertTrue(varasto.getSaldo()==0);
	}

	@Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(8);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenYliSaldonTyhjentaaVaraston() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(234);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }

	@Test
	public void negKonstr() {
		varasto = new Varasto(-2);
		assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
	}

	@Test
	public void negAlkuSaldo() {
		varasto = new Varasto(15, -3);
		assertTrue(varasto.getSaldo() == 0);
	}

	@Test
	public void suurempiAlkuSaldo() {
		varasto = new Varasto(5, 10);
		assertTrue("Saldo vituilleen", varasto.getTilavuus()==5);
	}

	@Test
	public void lisaaTyhjaan() {
		varasto = new Varasto(0, 0);
		varasto.lisaaVarastoon(3);
		assertTrue(varasto.getTilavuus() == 0 && varasto.getSaldo() == 0);
	}

	@Test
	public void otaKaikki() {
		varasto.lisaaVarastoon(10);
		varasto.otaVarastosta(10);
		assertTrue(varasto.getSaldo() == 0);
	}

	@Test
	public void otaYliSaldon() {
		varasto.lisaaVarastoon(5);
		double otettu = varasto.otaVarastosta(6);
		assertTrue(varasto.getSaldo() == 0);
		assertTrue(otettu == 5);
	}

	@Test
	public void luoTaysi() {
		varasto = new Varasto(12, 12);

		assertTrue(varasto.paljonkoMahtuu() == 0 && varasto.getTilavuus()==12);
	}

	@Test
	public void testTilavuus() {
		assertTrue(varasto.getTilavuus()==10);
	}

	@Test
	public void otaNegatiivinen() {
		varasto.lisaaVarastoon(5);
		double otettu = varasto.otaVarastosta(-3);
		assertTrue(varasto.getSaldo()==5);
		assertTrue(otettu == 0);
	}

	@Test
	public void fubarTest() {
		assertTrue("An extra test to see if jenkins starts test when commited.", true);
	}

}