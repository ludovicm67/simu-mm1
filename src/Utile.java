class Utile {

  /**
   * Tirage pour la loi expenentielle
   * @param param  paramètre de la loi exponentielle
   * @param random nombre aléatoire
   */
  static public double tirageExpo(double param, double random) {
    return (-Math.log(1 - random)) / param;
  }

  /**
   * Tirage d'une variable aléatoire, le résultat est compris entre 0 et 1
   */
  static public double tirageVa() {
    return Math.random();
  }
}
