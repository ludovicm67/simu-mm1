#!/bin/bash

printBold() {
  echo -e "\e[1m$1\e[0m"
}

printBold "STATISTIQUES CONCERNANT LA GÉNÉRATION DES DONNÉES"
SIMU_TOTAL_TIME=$(awk '{sum+=$4} END{print sum/60}' data.txt)
SIMU_AWG_EXEC=$(awk '{sum+=$4; nblines+=1} END{print sum/nblines}' data.txt)
echo " - durée nécessaire pour générer les data : $SIMU_TOTAL_TIME minutes"
echo " - durée moyenne par éxécution : $SIMU_AWG_EXEC secondes"
echo " - nombre d'éxécutions au total : $(cat data.txt | wc -l)"