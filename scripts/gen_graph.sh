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

mkdir -p graph/data
awk '{if ($2 == 5) if ($3 == 6) print $0}' data.txt > graph/data/x-5-6.txt

f=graph/data/x-5-6.txt

printBold "GÉNÉRATION DES GRAPHES"

GRAPH_TITLE="Mesure du temps d'éxecution en fonction de la durée de simulation"
echo " - génération du graphe «$GRAPH_TITLE»"
img=graph/data/exec-duration.png

gnuplot << EOF
set grid
set logscale x

set style line 1 lc rgb '#0060ad' lt 1 lw 2 pt 7 pi -1 ps 1.5
set pointintervalbox 3

set xlabel "Durée de la simulation (paramètre)"
set ylabel "Temps d'éxecution (secondes)"
set title "${GRAPH_TITLE}"
plot \
    "${f}" using 1:4 with linespoints linestyle 1 title "Temps d'éxécution"
set terminal png
set output "${img}"
replot
EOF


GRAPH_TITLE="Comparaison de la valeur de «ro» en fonction de la durée de simulation"
echo " - génération du graphe «$GRAPH_TITLE»"
img=graph/data/ro.png

gnuplot << EOF
set grid
set logscale x
set xlabel "Durée de la simulation (paramètre)"
set ylabel "Valeur de «ro»"
set title "${GRAPH_TITLE}"
plot \
    "${f}" using 1:7 with linespoints lt 1 title "Résultat simulation" smooth bezier,\
    "${f}" using 1:12 with linespoints lt 2 title "Résultat théorique" smooth bezier
set terminal png
set output "${img}"
replot
EOF
