# **Отчет по итогам тестирования**
## Краткое описание ##
Выполнено тестирование комплексного сервиса, взаимодействующего с СУБД и API Банка.

Приложение тестировалось по двум способам оплаты:

* Обычная оплата по дебетовой карте
* Уникальная технология: выдача кредита по данным банковской карты

Заявлена поддержка двух СУБД:

* MySQL
* PostgreSQL


## Количество тест-кейсов ##

Выполнено 38 тест-кейсов

## % успешных/не успешных ##
* 24 успешных
* 14 не успешных 

![Отчет Allure](C:\Users\shura\Desktop\bags\Безымянный15)

## Общие рекомендации ##

* Устранить выявленные баги;
* Визуально отделить способы оплаты;
* Кнопки "Купить" и "Купить в кредит" при нажатии должны изменять цвет, означающий активность выбранного способа оплаты;
* Доработать функциональность кнопки "Продолжить", пока все поля не будут заполнены, стояла блокировка;
* Предупреждения "Неверный формат" сделать более информативными.