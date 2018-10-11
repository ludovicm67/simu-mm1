.PHONY: build
build:
	javac ./src/*.java

.PHONY: run
run:
	cd src/ && java MM1 5 6 1000 0

.PHONY: run-debug
run-debug:
	cd src/ && java MM1 5 6 1000 1

.PHONY: test
test:
	cd src/ && ../scripts/tests.sh

.PHONY: gen-data
gen-data:
	cd src/ && ../scripts/gen_data.sh

.PHONY: gen-graph
gen-graph:
	./scripts/gen_graph.sh

.PHONY: clean
clean:
	rm -rf ./dist/
	rm -f ./src/*.class
	rm -rf ./graph/
	rm -f report.pdf
	rm -f tp-muller-ludovic.tar.gz

.PHONY: report
report:
	./scripts/report.sh

.PHONY: dist
dist: clean gen-graph report
	mkdir dist
	cp src/*java dist
	cp report.pdf dist
	cd dist/ && tar -zcvf ../tp-muller-ludovic.tar.gz * && cd ..
