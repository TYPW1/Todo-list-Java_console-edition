import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Gestionnaire implements Serializable {
    private List<ElementListe> elements;

    public Gestionnaire() {
        this.elements = new ArrayList<>();
    }

    public void ajouterElement(ElementListe element) {
        elements.add(element);
    }

    public void supprimerElement(int index) {
        if (index >= 0 && index < elements.size()) {
            elements.remove(index);
        } else {
            System.out.println("Invalid index!");
        }
    }

    public void afficherToutesLesTaches() {
        System.out.println("========== ALL TASKS ==========");
        for (ElementListe element : elements) {
            if (element instanceof Tâche) {
                element.afficher();
                System.out.println("-----------------------------");
            }
        }
    }
    
    public void afficherTousLesProjets() {
        System.out.println("========== ALL PROJECTS ==========");
        for (ElementListe element : elements) {
            if (element instanceof Projet) {
                element.afficher();
                System.out.println("-----------------------------");
            }
        }
    }
    
    public List<Tâche> getToutesLesTaches() {
        List<Tâche> taches = new ArrayList<>();
        for (ElementListe element : elements) {
            if (element instanceof Tâche) {
                taches.add((Tâche) element);
            }
        }
        return taches;
    }
    
    public List<Projet> getTousLesProjets() {
        List<Projet> projets = new ArrayList<>();
        for (ElementListe element : elements) {
            if (element instanceof Projet) {
                projets.add((Projet) element);
            }
        }
        return projets;
    }    


    // ... Additional methods for displaying tasks based on criteria will be added here ...
}
