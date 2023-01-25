package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DataAaccessLayer.AdminRepository;
import oslomet.testing.Models.Konto;
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

    }

    @Test
    public void hentAlleKonti_IkkeLoggetInn(){

    }

    @Test
    public void registrer_LoggetInn(){

    }

    @Test
    public void registrer_IkkeLoggetInn(){

    }

    @Test
    public void endreKonto_LoggetInn(){
        //arrange
        Konto konto1 = new Konto("12345678901", "4652132654", 12000, "Brukskonto", "NOK", null);

        when(sjekk.)
        //act

        //assert
    }

    @Test
    public void endreKonto_IkkeLoggetInn(){

    }

    @Test
    public void slett_LoggetInn(){

    }

    @Test
    public void slett_IkkeLoggetInn(){

    }
}
