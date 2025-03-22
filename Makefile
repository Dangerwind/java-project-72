run-dist:
	make -C app run-dist

run:
	make -C app run

build:
	make -C app build

test:
	make -C app test

report:
	make -C app report

checkstyleMain:
	make -C app checkstyleMain

checkstyleTest:
	make -C app checkstyleTest

setup:
	make -C app setup

build-run:
	make -C app build-run

.PHONY: build