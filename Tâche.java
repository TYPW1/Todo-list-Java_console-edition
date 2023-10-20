import java.time.LocalDate;

public class Tâche extends ElementListe {
    private LocalDate dateEcheance;
    private int priorite;
    private boolean accomplie;

    public Tâche(String titre, String description, LocalDate dateEcheance, int priorite) {
        super(titre, description);
        this.dateEcheance = dateEcheance;
        this.priorite = priorite;
        this.accomplie = false;
    }

    public void toggleAccomplie() {
        this.accomplie = !this.accomplie;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Due Date: " + dateEcheance);
        System.out.println("Priority: " + priorite);
        System.out.println("Completed: " + accomplie);
    }
}
