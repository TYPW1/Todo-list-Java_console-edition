public class ElementListe {
    protected String titre;
    protected String description;

    public ElementListe(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    // This method will be overridden by child classes
    public void afficher() {
        System.out.println("Title: " + titre);
        System.out.println("Description: " + description);
    }
}
