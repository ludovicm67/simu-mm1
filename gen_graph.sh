#!/bin/sh

RES_FILENAME="res.txt"

rm -f "$RES_FILENAME"

# $1=lambda : paramètre pour la loi exponentielle pour les inter-arrivées
# $2=mu : paramètre pour la loi exponentielle pour les durées de service
# $3=durée : durée à partir de laquelle on arrête de créer de nouveaux évènements
fetchResults() {
  CONTENT=$(time java MM1 "$1" "$2" "$3" 0  2>&1)
  UTIME=$(echo "$CONTENT" | awk '/user /{split($1, a, "user"); print a[1];}')

  # récupération des résultats théoriques
  STABLE=$(echo "$CONTENT" | awk '/lambda<mu/{print $NF;}')
  if [ "$STABLE" = "stable" ]; then
    STABLE=1
  else
    STABLE=0
  fi

  RO=$(echo "$CONTENT" | awk '/Prob file occupee/{print $NF;}')
  NBCA=$(echo "$CONTENT" | awk '/nombre de clients attendus/{print $NF;}')
  PSSA=$(echo "$CONTENT" | awk '/Prob de service sans attente/{print $NF;}')
  ESPNBC=$(echo "$CONTENT" | awk '/Esp nb clients/{print $NF;}')
  TMS=$(echo "$CONTENT" | awk '/Temps moyen de sejour \(1/{print $NF;}')

  # récupération des résultats de la simulation
  NBTC=$(echo "$CONTENT" | awk '/Nombre total de clients/{print $NF;}')
  PCSA=$(echo "$CONTENT" | awk '/Proportion clients sans attente/{print $NF;}')
  PCAA=$(echo "$CONTENT" | awk '/Proportion clients avec attente/{print $NF;}')
  DEBIT=$(echo "$CONTENT" | awk '/Débit =/{print $NF;}')
  NBMCS=$(echo "$CONTENT" | awk '/Nb moyen de clients dans systeme/{print $NF;}')
  TMDS=$(echo "$CONTENT" | awk '/Temps moyen de sejour =/{print $NF;}')

  echo "$3 $1 $2 $UTIME $NBTC $PCSA $PCAA $DEBIT $NBMCS $TMDS $STABLE $RO $NBCA $PSSA $ESPNBC $TMS" >> "$RES_FILENAME" 
}

for t in 10 100 1000 10000 100000 1000000 10000000 100000000; do
  for l in 2 5 15 30; do
    for m in 3 6 25; do
      if [ "$l" -le "$m" ]; then
        # lance 5 fois pour avoir un échantillon varié
        for i in `seq 5`; do
          fetchResults "$l" "$m" "$t"
        done
      fi
    done
  done
done

# CONTENU FICHIER GÉNÉRÉ :
# 1 - duree
# 2 - lambda
# 3 - mu
#
# 4 - durée d'éxécution
#
# RÉSULTATS SIMULATION
# 5 - nombre total de clients
# 6 - proportion clients sans attente
# 7 - proportion clients avec attente
# 8 - débit
# 9 - nb moyen de clients dans systeme
# 10- temps moyen de sejour
#
# RÉSULTATS THEORIQUES
# 11- stable ? (0 : non, 1 : oui)
# 12- ro (= prob file occupée)
# 13- nombre de clients attendus
# 14- proba service sans attente
# 15- espérance nombre de clients
# 16- temps moyen de séjour

exit 0
