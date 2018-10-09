class Utile {
  // tirage loi exponentielle
  static public double tirageExpo(double param, double random) {
    return (-Math.log(1 - random)) / param;
  }

  // tirage variable aléatoire (résultat compris entre 0 et 1)
  static public double tirageVa() {
    return Math.random();
  }
}
