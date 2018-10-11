Simulation d'une file M/M/1
===========================

## Les différentes classes

Les différentes classes Java imposées sont les suivantes :

  - `Evt` : classe représentant un évènement
  - `Ech` : classe représentant l'échéancier. Les évènements sont stockés dans
    une `LinkedList`. Le traitement est effectué avec la méthode `run`.
  - `Stats` : permet l'affichage et le calcul des statistiques de la simulation
    et des résultats théoriques.
  - `Utile` : classe qui contient une méthode pour le tirage aléatoire et une
    autre pour la loi exponentielle.

## Quelques commandes

Pour compiler le projet : `make` (ou `make build`)

Pour éxecuter le projet avec :
  - comme paramètre pour la loi exponentielle pour les inter-arrivées = 5
  - comme paramètre pour la loi exponentielle pour les durées de service = 6
  - comme paramètre de durée = 1000
il suffit de lancer la commande `make run`.

Un simple coup de `make run-debug` permet d'afficher le détail des différents
évènements traités par l'échéancier avec les mêmes valeurs de paramètres que
ceux utilisés par `make run`.

Pour vérifier que les résultats obtenus sont corrects, j'ai pris soin de rédiger
quelques tests, qui peuvent être exécutés avec `make test`, et devrait
retourner un résultat de la forme suivante si tout s'est bien passé :

```
TEST DE L'ORDRE DES ÉVÈNEMENTS
OK.
TEST DE L'ORDRE DES DÉPARTS
OK.
PRÉSENCE DE TOUS LES CLIENTS (ARRIVEE)
OK.
PRÉSENCE DE TOUS LES CLIENTS (DEPART)
OK.
```

Une fois le projet compilé avec `make`, il est possible de générer un fichier
de données appelé `data.txt` avec l'aide de la commande `make gen-data`.
Cependant, il faut bien prendre en compte que la durée de génération est
relativement longue, de l'ordre de l'heure. Ne pas hésiter à se chercher un bon
café et de faire une petite balade pendant ce temps ! Pour éviter d'avoir à
attendre, j'ai inclus dans ce dépôt un fichier `data.txt` généré.

Une fois les données générées, il est possible de générer des graphes avec
la commande `make gen-graph`. Les graphs générés se trouveront alors dans un
nouveau dossier appelé `graph`.

Pour générer le rapport, un simple coup de `make report` fait le job !

Pour générer l'archive de rendu, il suffira de lancer `make dist`, et une
archive au format `tar.gz` fera son apparition !

Pour nettoyer l'ensemble des fichiers générés et retrouver le dépôt dans son
état original, un simple `make clean` et le tour est joué.
