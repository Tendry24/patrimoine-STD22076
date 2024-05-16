package school.hei.patrimoine.possession;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrainDeVieTest {
  @Test
  void train_de_vie_est_finance_par_compte_courant() {
    var au13mai24 = Instant.parse("2024-05-13T00:00:00.00Z");
    var compteCourant = new Argent("Compte courant", au13mai24, 600_000);

    var aLOuvertureDeHEI = Instant.parse("2021-10-26T00:00:00.00Z");
    var aLaDiplomation = Instant.parse("2024-12-26T00:00:00.00Z");
    var dateEntreDebutOuvertureEtDateCompteCourant = Instant.parse("2022-12-26T00:00:00.00Z");
    var vieEstudiantine = new TrainDeVie(
        "Ma super(?) vie d'etudiant",
        500_000,
        aLOuvertureDeHEI,
        aLaDiplomation,
        compteCourant,
        1);
    //TODO: assert something useful
    assertTrue(vieEstudiantine.getValeurComptable() == vieEstudiantine.valeurComptableFuture(dateEntreDebutOuvertureEtDateCompteCourant));
  }

  @Test
  void un_train_de_vie_financé_par_argent() {
    var au13mai24 = Instant.parse("2024-05-13T00:00:00.00Z");
    var financeur = new Argent("Espèces", au13mai24, 400_000);
    var debut = Instant.parse("2024-01-01T00:00:00.00Z");
    var fin = Instant.parse("2025-01-01T00:00:00.00Z");
    var trainDeVie = new TrainDeVie("Cours", 10_000, debut, fin, financeur, 1);

    var projectionDate = Instant.parse("2024-12-31T00:00:00.00Z");
    assertEquals(280_000, trainDeVie.projectionFuture(projectionDate).getFinancePar().getValeurComptable());
  }
}