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
        List<Konto> saldi = new ArrayList<>();
        Konto konto1 = new Konto("01010110523",
                "105010123456",
                720,
                "Lønnskonto",
                "NOK",
                null);

        saldi.add(konto1);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentSaldi(anyString())).thenReturn(saldi);

        //act
        List<Konto> resultat = bankController.hentSaldi();

        //assert
        assertEquals(saldi, resultat);
    }

    @Test
    public void hentSaldi_IkkeLoggetInn(){
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Konto> resultat = bankController.hentSaldi();

        //assert
        assertNull(resultat);
    }

    /*
    Kommentar for "endreKundeInfo_LoggetInn" og "endreKundeInfo_IkkeLoggetInn" trengte en ekstra string value som var "Asker".
    Antar at innleggingen av "Asker" er ok utifra DataSQL hvor 3270 er lenket med Asker.
    */
    @Test
    public void endreKundeInfo_LoggetInn(){
        //arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene",
                "Jensen",
                "Askerveien 22",
                "3270",
                "Asker",
                "22224444",
                "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("Login Successful");

        //act
        String resultat = bankController.endre(enKunde);

        //assert
        assertEquals("Login Successful",resultat);

    }

    @Test
    public void endreKundeInfo_IkkeLoggetInn(){
        //arrange
        Kunde enKunde = new Kunde(
                "01010110523",
                "Lene",
                "Jensen",
                "Askerveien 22",
                "3270",
                "Asker",
                "22224444",
                "HeiHei");

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = bankController.endre(enKunde);

        //assert
        assertNull(resultat);
    }

    @Test
    public void hentBetalinger(){
    //arrange
        ArrayList<Transaksjon> transaksjoner = new ArrayList<>();

        Transaksjon betaling1 = new Transaksjon(10, "1234567891", 1234,
                "2020.02.02", "jippi", "1", "105010123456" );

        Transaksjon betaling2 = new Transaksjon(11, "1234567892", 567,
                "2021.12.12", "hurra", "1", "105010123456" );

        Transaksjon betaling3 = new Transaksjon(12, "1234567893", 890,
                "2022.02.02", "gratulerer", "0", "105010123456" );

        transaksjoner.add(betaling1);
        transaksjoner.add(betaling2);
        transaksjoner.add(betaling3);

        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.hentBetalinger("105010123456")).thenReturn(transaksjoner);

        //act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        //assert
        assertEquals(transaksjoner, resultat);
    }

    @Test
    public void hentBetalinger_Feil(){

        //act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        //assert
        assertNull(resultat);
    }

    @Test
    public void registrerBetaling_LoggetInn(){
        //arrange
        Transaksjon betaling = new Transaksjon(10, "2345678901", 599, "2021-05-16", "Sko", "1", "01987654321");

        when(sjekk.loggetInn()).thenReturn("25262315");
        when(repository.registrerBetaling(betaling)).thenReturn("OK");
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


