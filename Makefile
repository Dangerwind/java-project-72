.PHONY: build

lint: # Проверить кодстайл
	./app/gradlew -p ./app checkstyleMain
	./app/gradlew -p ./app checkstyleTest

clean: # Очистить дистрибутив
	./app/gradlew -p ./app clean

build: clean # Установить зависимости и собрать дистрибутив
	./app/gradlew -p ./app installDist

test: build # Собрать дистрибутив и запустить тесты
	./app/gradlew -p ./app build

test-report: test # Подготовить покрытие тестов
	./app/gradlew -p ./app jacocoTestReport

run: build # Запустить дистрибутив
	/.app/build/install/app/bin/app

