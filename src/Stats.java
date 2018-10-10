class Stats {

  // paramètres passés en argument
  private double lambda;
  private double mu;
  private double duree;

  // attributs pour les stats; sont mis à jour directement depuis l'échéancier
  public static int clientId = 0;
  public static int nbNoWait = 0;
  public static int nbWait = 0;
  public static int nbClientsBefore = 0;
  public static double lifeTime = 0;

  /**
   * Constructeur permettant de transmettre les valeur des paramètres passés en
   * argument
   *
   * @param lambda paramètre pour la loi exponentielle pour les inter-arrivées
   * @param mu     paramètre pour la loi exponentielle pour les durées de service
   * @param duree  durée à partir de laquelle on arrête de créer de nouveaux
   *               évènements
   */
  public Stats(double lambda, double mu, double duree) {
    this.lambda = lambda;
    this.mu = mu;
    this.duree = duree;
  }

  /**
   * Affichage d'un titre `title` dans la console
   * @param title titre à afficher
   */
  private void printTitle(String title) {
    if (title.isEmpty()) return;
    String separator = "\n--------------------\n";
    System.out.print("\n" + separator + title + separator);
  }

  /**
   * Affichage des résultats théoriques
   */
  private void printTheoricalResults() {
    printTitle("RESULTATS THEORIQUES");
    String out = new String();
    double ro = lambda / mu;

    // stabilité
    if (lambda < mu) {
      out += "lambda<mu : file stable\n";
    } else {
      out += "lambda>=mu : file instable\n";
    }

    out += "ro (lambda/mu) = " + ro + "\n";
    out += "nombre de clients attendus (lambda x duree) = " + (lambda * duree) + "\n";
    out += "Prob de service sans attente (1 - ro) = " + (1 - ro) + "\n";
    out += "Prob file occupee (ro) = " + ro + "\n";
    out += "Debit (lambda) = " + lambda + "\n";
    out += "Esp nb clients (ro/1-ro) = " + (ro / (1 - ro)) + "\n";
    out += "Temps moyen de sejour (1/mu(1-ro)) = " + (1 / (mu * (1 - ro)));

    System.out.print(out);
  }

  /**
   * Affichage des résultats de la simulation
   */
  private void printSimulatedResults() {
    printTitle("RESULTATS SIMULATION");
    String out = new String();

    out += "Nombre total de clients = " + clientId + "\n";
    out += "Proportion clients sans attente = " + ((double)nbNoWait / (double)clientId) + "\n";
    out += "Proportion clients avec attente = " + ((double)nbWait / (double)clientId) + "\n";
    out += "Débit = " + (clientId / duree) + "\n";
    out += "Nb moyen de clients dans systeme = " + ((double)nbClientsBefore / (double)clientId) + "\n";
    out += "Temps moyen de sejour = " + (lifeTime / (double)clientId) + "\n";

    System.out.print(out);
  }

  /**
   * Affiche les résultats dans la console
   */
  public void printResults() {
    printTheoricalResults();
    printSimulatedResults();
  }
}
