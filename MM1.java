class MM1 {

  // permet d'afficher la manière dont il faut utiliser le programme
  private static void printUsage() {
    System.err.println("UTILISATION : java MM1 lambda mu durée debug\n");
    System.err.println(" - lambda : \tparamètre pour la loi exponentielle pour les inter-arrivées");
    System.err.println(" - mu : \tparamètre pour la loi exponentielle pour les durées de service");
    System.err.println(" - durée : \tdurée à partir de laquelle on arrête de créer de nouveaux évènements");
    System.err.println(" - debug : \taffiche le détail des évènements si égal à 1");
    System.exit(1);
  }

  public static void main(String[] args) {

    // test du nombre d'arguments
    if (args.length != 4) printUsage();

    // récupération des arguments
    double lambda = Double.parseDouble(args[0]);
    double mu = Double.parseDouble(args[1]);
    double duree = Double.parseDouble(args[2]);
    boolean debug = args[3].equals("1");

    (new Ech(lambda, mu, duree, debug)).run();

    Stats stats = new Stats(lambda, mu, duree);
    stats.printResults();

    // creer objet Ech , ajouter premier evt, laisser tourner

    // lambda, mu, durée (à partir de laquelle on arrête de créer de nouveaux evts), debug (afficher evts si = 1)
  }
}