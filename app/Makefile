.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.7

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

.PHONY: build