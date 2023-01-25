package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DataAaccessLayer.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestKontoController {

    @InjectMocks
    // denne skal testes
    private AdminKontoController kontoController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentAlleKonti_LoggetInn(){
        ArrayList<Konto>  konto = new ArrayList<>();
        List<Transaksjon> transaksjoner = new ArrayList<>();

        Konto enKonto = new Konto("01010110523", "12345678912",
                20000, "brukerkonto", "NOK", transaksjoner);

        konto.add(enKonto);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        //arrange
        when(repository.hentAlleKonti()).thenReturn(konto);

        //act
        List <Konto> resultat = kontoController.hentAlleKonti();

        //assert
        assertEquals(konto, resultat);
    }

    @Test
    public void hentAlleKonti_IkkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List <Konto> resultat = kontoController.hentAlleKonti();

        //assert
        assertNull(resultat);
    }

    @Test
    public void registrer_LoggetInn(){
        //arrange
        Konto enKonto = new Konto(
                "105010123456",
                "01010110523",
                720,
                "Lønnskonto",
                "NOK",
                null);

        //act
        String resultat = kontoController.registrerKonto(enKonto);

        //assert
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void registrer_IkkeLoggetInn(){
        //arrange
        Konto enKonto = new Konto(
                "105010123456",
                "01010110523",
                720,
                "Lønnskonto",
                "NOK",
                null);

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = kontoController.registrerKonto(enKonto);

        //assert
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void endreKonto_LoggetInn(){
        //arrange
        Konto konto1 = new Konto("12345678901", "4652132654", 12000, "Brukskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn("12345678901");

        when(repository.endreKonto(konto1)).thenReturn("OK");

        //act
        String resultat = kontoController.endreKonto(konto1);

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void endreKonto_IkkeLoggetInn(){
        //arrange
        Konto konto1 = new Konto("12345678901", "4652132654", 12000, "Brukskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = kontoController.endreKonto(konto1);

        //assert
        assertEquals("Ikke innlogget", resultat);

    }

    @Test
    public void slett_LoggetInn(){
        when(sjekk.loggetInn()).thenReturn("01010110523");

        //arrange
        Mockito.when(kontoController.slettKonto("01234567891")).thenReturn("OK");

        //act
        String resultat = kontoController.slettKonto("01234567891");

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void slett_IkkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String result = kontoController.slettKonto("01230110523");

        //assert
        assertEquals("Ikke innlogget", result);
    }
}
