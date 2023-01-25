package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.API.BankController;
import oslomet.testing.DataAaccessLayer.AdminRepository;
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
public class EnhetstestKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController kundeController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentAlle_LoggetInn(){

    }

    @Test
    public void hentAlle_IkkeLoggetInn(){

    }

    @Test
    public void lagreKunde_LoggetInn(){

    }

    @Test
    public void lagreKunde_IkkeLoggetInn(){

    }

    @Test
    public void endre_LoggetInn(){

    }

    @Test
    public void endre_IkkeLoggetInn(){

    }

    @Test
    public void slett_LoggetInn(){

    }

    @Test
    public void slett_IkkeLoggetInn(){

    }
}
