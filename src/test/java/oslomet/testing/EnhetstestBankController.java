package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DataAaccessLayer.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }


    //De som kommer under her har vi laget selv:
    @Test
    public void hentTransaksjoner_LoggetInn(){
        //arrange
        /*
            List<Transaksjon> transaksjonList = new ArrayList<>();
            Transaksjon betaling1 = new Transaksjon(1, "2514 23 1648", 200, "2020-02-23", "heihei", "Mamma", "1548 54 5656");
            transaksjonList.add(betaling1);
        */

        Konto konto1 = new Konto("12345678901", "1548545656", 5000, "Privat konto", "NOK", null);


        when(sjekk.loggetInn()).thenReturn("12345678901");
        when(repository.hentTransaksjoner(anyString(), anyString(), anyString())).thenReturn(konto1);

        //act
        Konto resultat = bankController.hentTransaksjoner("1548545656", "", "");

        //assert
        assertEquals(konto1, resultat);
    }

    @Test
    public void hentTransaksjoner_IkkeLoggetInn(){
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act - har egt. ikke noe å si siden vi ikke bruker noen av parameterne...
        Konto resultat = bankController.hentTransaksjoner("", "", "");

        //assert
        assertNull(resultat);
    }

    @Test
    public void hentSaldi_LoggetInn(){
        //arrange

    }

    @Test
    public void registrerBetaling_LoggetInn(){
        //arrange
        Transaksjon betaling = new Transaksjon(10, "2345678901", 599, "2021-05-16", "Sko", "1", "01987654321");

        when(sjekk.loggetInn()).thenReturn("25262315");
        when(bankController.registrerBetaling(betaling)).thenReturn("OK");
        //act
        String resultat = bankController.registrerBetaling(betaling);

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void registrerBetaling_IkkeLoggetInn(){
        //arrange
        Transaksjon betaling = new Transaksjon(10, "2345678901", 599, "2021-05-16", "Sko", "1", "01987654321");

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = bankController.registrerBetaling(betaling);

        //assert
        assertNull(resultat);
    }

    @Test
    public void utforBetaling_LoggetInn(){
        //arrange
        List<Transaksjon> transaksjonList = new ArrayList<>();
        Transaksjon betaling = new Transaksjon(10, "2345678901", 599, "2021-05-16", "Sko", "1","26252320");
        transaksjonList.add(betaling);

        when(sjekk.loggetInn()).thenReturn("25262315");
        when(repository.utforBetaling(anyInt())).thenReturn("OK");
        when(repository.hentBetalinger(anyString())).thenReturn(transaksjonList);


        //act
        List<Transaksjon> resultat = bankController.utforBetaling(10);

        //assert
        assertEquals(transaksjonList, resultat);
    }

    @Test
    public void utforBetaling_IkkeLoggetInn(){
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Transaksjon> resultat = bankController.utforBetaling(10);

        //assert
        assertNull(resultat);
    }
}

