class Evt {
  private boolean isArrival; // false = départ, true = arrivée
  private double date; // heure de l'évènement
  private double birthDate; // heure d'arrivée
  private int clientId; // numéro du client

  // constructeur d'Evt d'arrivée
  public Evt(double date, int clientId) {
    this.isArrival = true;
    this.date = date;
    this.birthDate = date;
    this.clientId = clientId;
  }

  // constructeur d'Evt de départ
  public Evt(double date, int clientId, double birthDate) {
    this.isArrival = false;
    this.date = date;
    this.birthDate = birthDate;
    this.clientId = clientId;
    Stats.lifeTime += date - birthDate;
  }

  // affichage d'un Evt sous forme de chaîne de caractères (si debug = 1)
  public String toString() {
    String pad = "                       ";
    return "Date=" + date + pad.substring(Double.toString(date).length())
      + (isArrival ? "Arrivee" : "Depart") + "\t"
      + "client #" + clientId
      + (isArrival ? "" : "\tarrive a t=" + birthDate);
  }

  public boolean isArrival() {
    return isArrival;
  }

  public int getClientId() {
    return clientId;
  }

  public double getDate() {
    return date;
  }

  public double getBirthDate() {
    return birthDate;
  }
}