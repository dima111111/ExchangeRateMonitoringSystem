ExchangeRateMonitoringSystem
=============================

This is a simple rate monitoring micro-service.

The rate monitoring service refers to the exchange rate service and sends a gif in response:

if the exchange rate against the ruble for today has become higher than yesterday, then we give a random rich from here <a href="https://giphy.com/search/rich">rich</a>

if lower - it broke from here <a href="https://giphy.com/search/broke">broke</a>

External services:

REST API of currency rates - <a href="https://docs.openexchangerates.org/">openexchangerates</a>

GIF REST API - <a href="https://developers.giphy.com/docs/api#quick-start-guide">giphy</a>

SERVICE FUNCTIONAL REQUIREMENTS
------------

Spring Boot 2 + Java / Kotlin service

Requests come to the HTTP endpoint, the currency code is sent there

Feign is used to interact with external services

All parameters (the currency in relation to which the rate looks, addresses of external services, etc.) are made in the settings

Tests are written for the service (for mocking external services, you can use @mockbean or WireMock)

Build must use Gradle

QUICK START
-----------

In your IDE: create project from version control.

Execute gradle task: 

•  bootRun - to run application (Open you browser at http://localhost:8080/ to see results)
  
•  test - to run all testes and check the results
  
