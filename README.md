## Framework Architecture

- **DriverFactory** — ThreadLocal WebDriver management for parallel safety
- **ConfigReader** — Properties file reader with static block loading
- **BaseTest** — TestNG @BeforeMethod/@AfterMethod lifecycle hooks
- **WaitUtils** — Explicit wait wrappers + hover support
- **FrameworkConstants** — Centralized config keys and paths
- **Page Objects** — HomePage, LoginPage, ProductsPage, CartPage

## Test Coverage

| Test | Description |
|------|-------------|
| testValidLogin | Verifies successful login with valid credentials |
| testInvalidLogin | Verifies error message with invalid credentials |
| testProductSearch | Searches for a product and verifies results |
| testAddToCart | Adds product to cart and verifies cart page |

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver 4.x
- TestNG
- WebDriverManager