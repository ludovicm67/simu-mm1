# Performances du simulateur

Concernant les performances, j'ai fait en sorte que le programme mette le moins
de temps possible pour s'éxecuter, afin d'avoir des résultats plus rapidement
pour pouvoir constituer des jeux de données intéressants.

En terme d'optimisations, une concerne l'insertion dans la liste chaînée.
En effet, l'échéancier est représenté par une liste chaînée, dans laquelle il
faut insérer les évènements dans l'ordre chronologique : il faut donc qu'ils
soient triés. Or, lors du traitement d'une arrivée, il faut gérer l'arrivée
du prochain client et le départ du client que l'on traite actuellement, et rien
ne garantie qu'un de ces évènement se produise avant l'autre, ou même avant
l'un des évènements déjà présent dans la liste. Mais en terme de probabilité, il
est plus courant que le nouvel évènement doit être inséré en fin de liste, c'est
la raison pour laquelle lorsque j'insère un nouvel évènement, je commence par
vérifier en fin de liste si le dernier évènement ne serait pas ultérieur à
celui que je souhaite insérer, et je remonte ainsi la liste jusqu'à trouver la
bonne position.

Un autre point important, est que lors du traitement de l'échéancier, je me suis
rendu compte que je ne faisais que traiter les évènements d'arrivée; j'ai donc
fait en sorte de ne pas insérer les évènements de départ dans l'échéancier, et
j'ai pu constater des gains de performance non négligeables. En effet sur ma
machine personnelle je suis passé d'un peu moins de dix secondes à un peu plus
de six secondes, et sur Turing, de 22-24 secondes à 14-15 secondes en moyenne,
lorsque j'utilise les paramètres lambda = 5, mu = 6, durée = 10 000 000 sans
afficher les informations de debug. Un gain d'un peu moins de 30%.

Concernant les temps d'éxecution j'ai pu mesurer une linéarité du temps
d'éxecution comme on peut le constater sur le graphe suivant :
![Temps d'éxecution en fonction de la durée](../graph/exec-duration.png)

L'axe des abscisses utilise une échelle logarithmique, la courbe ayant une
allure d'une courbe exponentielle, cela confirme la linéarité du temps
d'éxecution en fonction de la durée passée en paramètre.
