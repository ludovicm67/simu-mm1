import java.util.LinkedList;

class Ech {

  // la liste chaînée d'évènements représentant l'échéancier
  private LinkedList<Evt> list = new LinkedList<>();

  // paramètres passés en argument
  private double lambda;
  private double mu;
  private double duree;
  private boolean debug;

  // pour maintenir la date du départ précédent
  private double lastDeparture = 0;

  /**
   * Constructeur pour l'échéancier
   *
   * @param lambda paramètre pour la loi exponentielle pour les inter-arrivées
   * @param mu     paramètre pour la loi exponentielle pour les durées de service
   * @param duree  durée à partir de laquelle on arrête de créer de nouveaux
   *               évènements
   * @param debug  affiche le détail des évènements si égal à 1
   */
  public Ech(double lambda, double mu, double duree, boolean debug) {
    this.lambda = lambda;
    this.mu = mu;
    this.duree = duree;
    this.debug = debug;
  }

  /**
   * permet d'ajouter un client pour une date = 0
   */
  private void addClient() {
    addClient(0);
  }

  /**
   * Permet d'ajouter un client pour une date = `time`
   * @param time date de l'évènement d'arrivée du client
   */
  private void addClient(double time) {
    Evt client = new Evt(time, Stats.clientId++); // nouveau client
    addEventToList(client);
  }

  /**
   * Permet d'ajouter en bonne position un évènement dans l'échéancier
   * @param e évènement à ajouter à la liste représentant l'échéancier
   */
  private void addEventToList(Evt e) {
    int index = list.size();

    // on trouve le bon index
    while (index > 0 && list.get(index - 1).getDate() > e.getDate()) index--;

    list.add(index, e);
  }

  /**
   * Permet de mettre en route l'échéancier
   *  1 - ajoute un évènement d'arrivée pour le premier client
   *  2 - traitement de l'échéancier tant qu'il n'est pas vide
   */
  public void run() {
    addClient(); // ajout du premier client

    double time;
    Evt e;

    int nbBefore;

    // traitement de l'échéancier
    while (!list.isEmpty()) {

      // récupère le premier évènement de l'échéancier et le retire de la liste
      e = list.pollFirst();
      if (debug) System.out.println(e); // l'affiche si on est en mode "debug"

      // si c'est une arrivée
      if (e.isArrival()) {


        nbBefore = 0;
        for (int i = 0; i < list.size(); i++) {
          // s'il y a un départ avant, c'est qu'il y a déjà des clients dans la file
          if (!list.get(i).isArrival()) {
            nbBefore++;
          }
        }
        if (nbBefore > 0) Stats.nbWait++; // ce client doit attendre
        else Stats.nbNoWait++; // ce client n'a pas eu d'attente

        Stats.nbClientsBefore += nbBefore; // ajout du nombre de client avant lui dans la file

        // on ajoute un nouveau client
        time = e.getDate() + Utile.tirageExpo(lambda, Utile.tirageVa());
        if (duree >= time) {
          addClient(time);
        }

        // on prévoit le départ de notre client actuel
        time = Math.max(e.getDate(), lastDeparture);
        time += Utile.tirageExpo(mu, Utile.tirageVa());
        lastDeparture = time;

        Evt departure = new Evt(time, e.getClientId(), e.getBirthDate());
        addEventToList(departure);
      }
    }
  }
}
