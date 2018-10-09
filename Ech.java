import java.util.LinkedList;

class Ech {
  private LinkedList<Evt> list = new LinkedList<>();

  private double lambda;
  private double mu;
  private double duree;
  private boolean debug;
  private double lastDeparture = 0;

  public Ech(double lambda, double mu, double duree, boolean debug) {
    this.lambda = lambda;
    this.mu = mu;
    this.duree = duree;
    this.debug = debug;
  }

  private void addClient() {
    addClient(0);
  }

  private void addClient(double time) {
    Evt client = new Evt(time, Stats.clientId++); // nouveau client
    addEventToList(client);
  }

  private void addEventToList(Evt e) {
    int index = list.size();

    // on trouve le bon index
    while (index > 0 && list.get(index - 1).getDate() > e.getDate()) index--;

    list.add(index, e);
  }

  public void run() {
    addClient(); // ajout du premier client

    double time;
    Evt e;

    int nbBefore;

    while (!list.isEmpty()) {
      e = list.pollFirst();
      if (debug) System.out.println(e);

      if (e.isArrival()) { // si c'est une arrivée
        nbBefore = 0;
        for (int i = 0; i < list.size(); i++) {
          // s'il y a un départ avant
          if (!list.get(i).isArrival()) {
            nbBefore++;
          }
        }
        if (nbBefore > 0) Stats.nbWait++;
        else Stats.nbNoWait++;

        Stats.nbClientsBefore += nbBefore;

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
