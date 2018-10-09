#!/bin/bash

printInfo() {
  echo -e "\e[1m$1\e[0m"
}

checkResults() {
  RES=$(diff --color <(echo "$1") <(echo "$2") | grep '>' | wc -l)
  if [ "$RES" -eq 0 ]; then
    echo -e "\e[32mOK.\e[0m"
  else
    echo -e "\e[31mKO. Présence de $RES différences.\e[0m"
  fi
}

################################################################################

printInfo "TEST DE L'ORDRE DES ÉVÈNEMENTS"

CONTENT=$(javac *.java && java MM1 5 6 1000 1 | sed -n '/^Date=/p' | sed 's/^Date=//' | grep -v "client #0")
EXPECTED=$(echo "$CONTENT" | sort -n)
checkResults "$CONTENT" "$EXPECTED"

################################################################################

printInfo "TEST DE L'ORDRE DES DÉPARTS"

CONTENT=$(javac *.java && java MM1 5 6 1000 1 | sed -n '/^Date=/p' | grep 'Depart' | sed 's/.*Depart[[:space:]]client #//')
EXPECTED=$(echo "$CONTENT" | sort -n)
checkResults "$CONTENT" "$EXPECTED"

################################################################################

printInfo "PRÉSENCE DE TOUS LES CLIENTS (ARRIVEE)"

CONTENT=$(javac *.java && java MM1 5 6 1000 1 | sed -n '/^Date=/p' | grep 'Arrivee' | sed 's/.*Arrivee[[:space:]]client #//'  | awk '{print $1}' | sort -n)
LAST=$(echo "$CONTENT" | awk 'END{print}')
EXPECTED=$(seq 0 "$LAST")
checkResults "$CONTENT" "$EXPECTED"

################################################################################

printInfo "PRÉSENCE DE TOUS LES CLIENTS (DEPART)"

CONTENT=$(javac *.java && java MM1 5 6 1000 1 | sed -n '/^Date=/p' | grep 'Depart' | sed 's/.*Depart[[:space:]]client #//'  | awk '{print $1}' | sort -n)
LAST=$(echo "$CONTENT" | awk 'END{print}')
EXPECTED=$(seq 0 "$LAST")
checkResults "$CONTENT" "$EXPECTED"
