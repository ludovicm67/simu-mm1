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
