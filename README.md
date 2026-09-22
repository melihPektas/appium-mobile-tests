# Appium Mobile Tests

![Android Tests](https://github.com/melihPektas/appium-mobile-tests/actions/workflows/android-tests.yml/badge.svg)

Android UI test automation with **Appium (UiAutomator2)**, **Java 17** and **TestNG**. Every push boots a real Android emulator in GitHub Actions, installs the app and runs the suite end to end — from the product catalogue to a placed order.

The app under test is [My Demo App](https://github.com/saucelabs/my-demo-app-android), a public demo shopping app, downloaded in CI so the repository stays small.

## What this project demonstrates

| Area | How it is done here |
| --- | --- |
| Page Object Model | One class per screen (catalog, product, cart, login, shipping, payment, review, confirmation) plus a `HeaderBar` component; tests read like user stories |
| Reliable locators | Resource ids taken from the app itself, with `UiScrollable` fallback that scrolls an element into view before interacting |
| No sleeps | Explicit waits for every screen transition; assertions wait for the expected text instead of polling blindly |
| Isolated tests | A fresh Appium session per test method, so no test depends on the state another test left behind |
| Failure evidence | Screenshot of the failing screen, Surefire reports and the Appium server log kept as CI artifacts |
| Emulator in CI | API 30 x86_64 emulator with KVM acceleration and animations disabled; results summarised in the GitHub Actions job summary |
| Configurable | `APPIUM_URL` and `APP_PATH` come from the environment, so the same suite runs locally, in CI or against a device cloud |

## Test coverage

| Test | What it proves |
| --- | --- |
| `CatalogTest.catalogShowsProductsOnLaunch` | The catalogue loads with named products after a cold start |
| `CatalogTest.openingAProductShowsItsDetails` | Tapping a product opens the details of that same product |
| `CartTest.addingProductsUpdatesTheCart` | Quantity control, cart badge and cart contents stay in sync |
| `LoginTest.registeredUserCanLogIn` | A registered user logs in from the menu and returns to the catalogue |
| `LoginTest.loginRequiresAUsername` | An empty form shows the username validation message |
| `CheckoutTest.customerCanCompleteAPurchase` | Full journey: add to cart → log in at checkout → shipping → payment → review → order placed |

## Project structure

```
.
├── .github/workflows/android-tests.yml   # compile → emulator → run suite → summary
├── scripts/run-tests.sh                  # starts Appium, runs Maven, stops Appium
├── scripts/summary.py                    # Surefire XML → job summary table
├── src/main/java/.../driver              # session factory and configuration
├── src/main/java/.../pages               # page objects
├── src/main/java/.../data                # test users, address and card data
└── src/test/java/.../tests               # TestNG tests
```

## Running locally

Requires a running emulator or device, Android SDK platform-tools, Appium 3 with the UiAutomator2 driver, and Java 17.

```bash
npm install -g appium && appium driver install uiautomator2
mkdir -p apps && curl -L -o apps/mda.apk \
  https://github.com/saucelabs/my-demo-app-android/releases/download/2.2.0/mda-2.2.0-25.apk

appium &                 # start the Appium server
mvn test                 # run the whole suite
mvn test -Dgroups=smoke  # smoke tests only
```

## About

Built by **Ahmet Melih Pektaş**, QA Lead with 9+ years in test automation. I built web and mobile (iOS/Android) automation suites with Appium and Selenium at Borusan Makina and Trendyol, and help teams set up mobile test automation and CI pipelines as a freelancer.
