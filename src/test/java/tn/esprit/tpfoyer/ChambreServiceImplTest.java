package tn.esprit.tpfoyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChambreServiceImplTest {
    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllChambres() {
        // Given
        Chambre chambre1 = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);
        Chambre chambre2 = new Chambre(2L, 102L, TypeChambre.DOUBLE, null, null);
        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre1, chambre2));

        // When
        List<Chambre> chambres = chambreService.retrieveAllChambres();

        // Then
        assertNotNull(chambres);
        assertEquals(2, chambres.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveChambre() {
        // Given
        Chambre chambre = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        // When
        Chambre foundChambre = chambreService.retrieveChambre(1L);

        // Then
        assertNotNull(foundChambre);
        assertEquals(1L, foundChambre.getIdChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    void testAddChambre() {
        // Given
        Chambre chambre = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // When
        Chambre savedChambre = chambreService.addChambre(chambre);

        // Then
        assertNotNull(savedChambre);
        assertEquals(101L, savedChambre.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testModifyChambre() {
        // Given
        Chambre chambre = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // When
        Chambre updatedChambre = chambreService.modifyChambre(chambre);

        // Then
        assertNotNull(updatedChambre);
        assertEquals(101L, updatedChambre.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRemoveChambre() {
        // Given
        Long chambreId = 1L;
        doNothing().when(chambreRepository).deleteById(chambreId);

        // When
        chambreService.removeChambre(chambreId);

        // Then
        verify(chambreRepository, times(1)).deleteById(chambreId);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        // Given
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        Chambre chambre1 = new Chambre(1L, 101L, typeChambre, null, null);
        Chambre chambre2 = new Chambre(2L, 102L, typeChambre, null, null);
        when(chambreRepository.findAllByTypeC(typeChambre)).thenReturn(Arrays.asList(chambre1, chambre2));

        // When
        List<Chambre> chambres = chambreService.recupererChambresSelonTyp(typeChambre);

        // Then
        assertNotNull(chambres);
        assertEquals(2, chambres.size());
        verify(chambreRepository, times(1)).findAllByTypeC(typeChambre);
    }

    @Test
    void testTrouverChambreSelonEtudiant() {
        // Given
        long cin = 12345678L;
        Chambre chambre = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);
        when(chambreRepository.trouverChselonEt(cin)).thenReturn(chambre);

        // When
        Chambre foundChambre = chambreService.trouverchambreSelonEtudiant(cin);

        // Then
        assertNotNull(foundChambre);
        assertEquals(101L, foundChambre.getNumeroChambre());
        verify(chambreRepository, times(1)).trouverChselonEt(cin);
    }
}


