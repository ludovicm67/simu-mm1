# Analyse des résultats

Dans un premier temps, j'ai décidé de faire des graphes avec $lambda = 5$ et
$mu = 6$ en faisant varier la durée.

![Probabilité de clients avec attente en fonction de la durée de simulation](../graph/ro.png)

![Probabilité de clients sans attente en fonction de la durée de simulation](../graph/clients-wait.png)

![Temps moyen de séjour en fonction de la durée de simulation](../graph/time-mean.png)

On peut constater qu'on a des résultats de simulations vraiment très proches
des résultats théoriques dès que l'on a $durée = 100 000$, comme l'on peut très
bien le constater avec les étendus des données des différentes simulations.

Si on a une file instable, c'est-à-dire lorsque $lambda>mu$, le nombre de
clients se met à exploser et la simulation prends nettement plus de temps.

Si on approche les valeurs de lambda et de mu, le nombre de clients se met
à augmenter très rapidement.
