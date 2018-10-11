#!/bin/sh

CONTENT_ANALYSE=$(cat ./report/analyse.md)
CONTENT_PERFS=$(cat ./report/perfs.md)

CONTENT="$CONTENT_ANALYSE\n\n$CONTENT_PERFS"

# create a pdf file for the cover
cat "./report/cover.tex" \
  | xelatex > /dev/null && mv texput.pdf .report-cover.pdf

echo "$CONTENT" \
  | sed 's/\.\.\//\.\//g' \
  | pandoc -S --toc -o .report-content.pdf \
    -V lang=fr -V fontsize=12pt -V documentclass=report

# merge all needed pdf files
gs -dBATCH -dNOPAUSE -q -sDEVICE=pdfwrite -dAutoRotatePages=/None \
  -sOutputFile=report.pdf \
  .report-cover.pdf \
  .report-content.pdf

# clean temp files
rm -f .report-*.pdf texput* *.tex
