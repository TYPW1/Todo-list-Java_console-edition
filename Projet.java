import java.util.ArrayList;
import java.util.List;

public class Projet extends ElementListe {
    private List<Tâche> listeTaches;

    public Projet(String titre, String description) {
        super(titre, description);
        this.listeTaches = new ArrayList<>();
    }

    public void ajouterTache(Tâche tache) {
        listeTaches.add(tache);
    }

    public void supprimerTache(int index) {
        if (index >= 0 && index < listeTaches.size()) {
            listeTaches.remove(index);
        } else {
            System.out.println("Invalid index!");
        }
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Tasks in this project:");
        for (Tâche tache : listeTaches) {
            tache.afficher();
        }
    }
}
