package com.saintjean.operationalpha;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestUtilisateur {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        Utilisateur.users.clear();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testAjoutUtilisateurValide() throws EmailInvalidException {
        Utilisateur u1 = new Utilisateur(1, "Kouang", 25, "kouang@gmail.com", "677001122", "Yaoundé", 75000.0);
        Utilisateur.ajouter(u1);

        assertEquals(1, Utilisateur.users.size());
        assertTrue(Utilisateur.users.contains(u1));
        assertTrue(outContent.toString().contains("Utilisateur ajouté"));
    }

    @Test
    void testAjoutUtilisateurEmailInvalide() {
        Utilisateur u2 = new Utilisateur(2, "Ngono", 30, "ngono@gmail.com", "690112233", "Douala", 50000.0);

        Exception exception = assertThrows(EmailInvalidException.class, () -> {
            Utilisateur.ajouter(u2);
        });

        assertTrue(exception.getMessage().contains("Email invalide"));
        assertTrue(Utilisateur.users.isEmpty());
    }

    @Test
    void testSuppressionUtilisateurValide() throws Exception {
        Utilisateur u1 = new Utilisateur(3, "Tchatchoua", 28, "tchatchoua@gmail.com", "691223344", "Bafoussam", 82000.0);
        Utilisateur.ajouter(u1);
        Utilisateur.supprimer(3);

        assertTrue(Utilisateur.users.isEmpty());
        assertTrue(outContent.toString().contains("Utilisateur supprimé"));
    }

    @Test
    void testSuppressionUtilisateurInvalide() {
        Exception exception = assertThrows(SuppressionInvalidException.class, () -> {
            Utilisateur.supprimer(999);
        });

        assertTrue(exception.getMessage().contains("introuvable"));
    }

    @Test
    void testListerAucunUtilisateur() {
        Utilisateur.lister();
        assertTrue(outContent.toString().contains("Aucun utilisateur"));
    }

    @Test
    void testListerAvecUtilisateurs() throws EmailInvalidException {
        Utilisateur u1 = new Utilisateur(4, "Ewane", 22, "ewane@gmail.com", "670998877", "Ebolowa", 30000.0);
        Utilisateur u2 = new Utilisateur(5, "Fonkoua", 27, "fonkoua@gmail.com", "699887766", "Garoua", 64000.0);

        Utilisateur.ajouter(u1);
        Utilisateur.ajouter(u2);
        Utilisateur.lister();

        assertTrue(outContent.toString().contains("Liste des utilisateurs"));
        assertTrue(outContent.toString().contains("Ewane"));
        assertTrue(outContent.toString().contains("Fonkoua"));
    }

    @Test
    void testAfficherUtilisateurExistant() throws EmailInvalidException {
        Utilisateur u1 = new Utilisateur(6, "Aboubakar", 29, "aboubakar@gmail.com", "672112233", "Maroua", 58000.0);
        Utilisateur.ajouter(u1);
        Utilisateur.afficher(6);

        assertTrue(outContent.toString().contains("Détails utilisateur"));
        assertTrue(outContent.toString().contains("Aboubakar"));
    }

    @Test
    void testAfficherUtilisateurInexistant() {
        Utilisateur.afficher(404);
        assertTrue(outContent.toString().contains("Aucun utilisateur trouvé"));
    }
    
    @Test
    void testAnalyseSoldeGeneralPositif() throws Exception {
        Utilisateur u1 = new Utilisateur(1, "Biya", 25, "biya@cameroon.cm", "677001122", "Yaoundé", 75000.0);
        Utilisateur u2 = new Utilisateur(2, "Ngono", 30, "ngono@douala.cm", "690112233", "Douala", 50000.0);
        Utilisateur.ajouter(u1);
        Utilisateur.ajouter(u2);

        double total = Utilisateur.analyseSoldeGeneral();

        assertEquals(125000.0, total);
        assertTrue(outContent.toString().contains("💰 Solde général"));
    }

    @Test
    void testAnalyseSoldeGeneralNegatif() throws Exception {
        Utilisateur u1 = new Utilisateur(3, "Aboubakar", 28, "aboubakar@maroua.cm", "699223344", "Maroua", -30000.0);
        Utilisateur u2 = new Utilisateur(4, "Tchatchoua", 32, "tchatchoua@bafoussam.cm", "691887766", "Bafoussam", -20000.0);
        Utilisateur.ajouter(u1);
        Utilisateur.ajouter(u2);

        Exception exception = assertThrows(NegativeGeneralBalanceException.class, () -> {
            Utilisateur.analyseSoldeGeneral();
        });

        assertTrue(exception.getMessage().contains("Solde général négatif"));
    }

    @Test
    void testAnalyseSoldeGeneralVide() throws Exception {
        double total = Utilisateur.analyseSoldeGeneral();
        assertEquals(0.0, total);
        assertTrue(outContent.toString().contains("Solde général"));
    }

    // -------------------- TESTS getUtilisateurLePlusRiche() --------------------

    @Test
    void testGetUtilisateurLePlusRiche() throws Exception {
        Utilisateur u1 = new Utilisateur(5, "Ewane", 22, "ewane@ebolowa.cm", "670998877", "Ebolowa", 35000.0);
        Utilisateur u2 = new Utilisateur(6, "Fonkoua", 27, "fonkoua@garoua.cm", "699887766", "Garoua", 64000.0);
        Utilisateur u3 = new Utilisateur(7, "Mbah", 29, "mbah@bamenda.cm", "677221100", "Bamenda", 42000.0);

        Utilisateur.ajouter(u1);
        Utilisateur.ajouter(u2);
        Utilisateur.ajouter(u3);

        Utilisateur plusRiche = Utilisateur.getUtilisateurLePlusRiche();

        assertNotNull(plusRiche);
        assertEquals("Fonkoua", plusRiche.getNom());
        assertEquals(64000.0, plusRiche.getSoldePersonnel());
        assertTrue(outContent.toString().contains("👑 Utilisateur le plus riche"));
    }

    @Test
    void testGetUtilisateurLePlusRicheListeVide() {
        Utilisateur plusRiche = Utilisateur.getUtilisateurLePlusRiche();
        assertNull(plusRiche);
        assertTrue(outContent.toString().contains("Aucun utilisateur enregistré"));
    }
}
