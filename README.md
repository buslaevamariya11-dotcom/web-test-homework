# Web-test-homework

![CI Status](https://github.com/buslaevamariya11-dotcom/web-test-homework/actions/workflows/gradle.yml/badge.svg)

Описание вашего проекта. Этот проект создан для выполнения домашнего задания по тестированию веб-приложений.

## Как запустить

1. Склонируйте репозиторий:
   `git clone https://github.com/buslaevamariya11-dotcom/web-test-homework.git`
2. Соберите проект:
   `./gradlew build`
3. Запустите тесты:
   `./gradlew test`

## Отчет о тестировании

В ходе автоматизации тестирования был обнаружен баг в тексте валидации поля "Фамилия и имя".

* **Тест**: `shouldShowErrorWhenNameIsInvalid`
* **Статус**: FAIL
* **Причина**: Орфографическая ошибка в приложении (написано "указаные" вместо "указаны").
* **Подробности**: См. [Issue #1](https://github.com/buslaevamariya11-dotcom/web-test-homework/issues/1)