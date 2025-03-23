.DEFAULT_GOAL := build-run
.PHONY: build

setup:
	./gradlew wrapper --gradle-version 8.12

build:
	./gradlew clean build

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test

checkstyleMain:
	./gradlew checkstyleMain

checkstyleTest:
	./gradlew checkstyleTest

build-run: build run

report:
	./gradlew jacocoTestReport

