class Evt {

  private boolean isArrival; // false = départ, true = arrivée
  private double date; // heure de l'évènement
  private double birthDate; // heure d'arrivée
  private int clientId; // numéro du client

  /**
   * Constructeur d'évènement d'arrivée
   * @param date date de création de l'évènement
   * @param clientId id du client concerné
   */
  public Evt(double date, int clientId) {
    this.isArrival = true;
    this.date = date;
    this.birthDate = date;
    this.clientId = clientId;
  }

  /**
   * Constructeur d'évènement de départ
   * @param date date de création de l'évènement
   * @param clientId id du client concerné par l'évènement
   * @param birthDate heure à laquelle le client est arrivé
   */
  public Evt(double date, int clientId, double birthDate) {
    this.isArrival = false;
    this.date = date;
    this.birthDate = birthDate;
    this.clientId = clientId;
  }

  /**
   * Permet un affichage propre des évènements si `debug` = 1
   */
  public String toString() {
    String pad = "                       ";
    return "Date=" + date + pad.substring(Double.toString(date).length())
      + (isArrival ? "Arrivee" : "Depart") + "\t"
      + "client #" + clientId
      + (isArrival ? "" : "\tarrive a t=" + birthDate);
  }

  /**
   * Indique s'il s'agit d'un évènement d'arrivée (`true`) ou de départ (`false`)
   * @return s'agit-il d'un évènement d'arrivée ?
   */
  public boolean isArrival() {
    return isArrival;
  }

  /**
   * Retourne l'id du client
   * @return id du client
   */
  public int getClientId() {
    return clientId;
  }

  /**
   * Retourne la date de création de l'évènement
   * @return date de création de l'évènement
   */
  public double getDate() {
    return date;
  }
}
