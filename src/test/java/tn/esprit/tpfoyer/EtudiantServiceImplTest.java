package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllEtudiants() {
        // Arrange
        List<Etudiant> etudiants = Arrays.asList(
                new Etudiant(1L, "John", "Doe", 12345678L, new Date(), new HashSet<>()),
                new Etudiant(2L, "Jane", "Doe", 87654321L, new Date(), new HashSet<>())
        );
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void retrieveEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", 12345678L, new Date(), new HashSet<>());
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant result = etudiantService.retrieveEtudiant(1L);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void addEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant(123, "John", "Doe", 12345678L, new Date(), new HashSet<>());
        Etudiant savedEtudiant = new Etudiant(1L, "John", "Doe", 12345678L, new Date(), new HashSet<>());
        when(etudiantRepository.save(etudiant)).thenReturn(savedEtudiant);

        // Act
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIdEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void modifyEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", 12345678L, new Date(), new HashSet<>());
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.modifyEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void removeEtudiant() {
        // Arrange
        Long etudiantId = 1L;
        doNothing().when(etudiantRepository).deleteById(etudiantId);

        // Act
        etudiantService.removeEtudiant(etudiantId);

        // Assert
        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    void recupererEtudiantParCin() {
        // Arrange
        long cinEtudiant = 12345678L;
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", cinEtudiant, new Date(), new HashSet<>());
        when(etudiantRepository.findEtudiantByCinEtudiant(cinEtudiant)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.recupererEtudiantParCin(cinEtudiant);

        // Assert
        assertNotNull(result);
        assertEquals(cinEtudiant, result.getCinEtudiant());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(cinEtudiant);
    }
}
