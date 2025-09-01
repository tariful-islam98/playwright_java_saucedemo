# Playwright Automation Framework
## Features
- **Multi-Browser Support** (chromium, firefox, webkit, chrome) via config.properties
- BDD with Cucumber
- TestNG Test Management
- ExtentReports with Screenshots
- Dynamic Time-Stamped Reports
## Project Structure
```
src/test/
├── java/
│   └── com/practice/
│       ├── pages/          # Page classes
│       ├── stepdefinitions/          # Step definitions
│       ├── base/      # BaseTest, ReportManager
│       ├── utilities/     # Utility classes
│       └── runners/        # Test runners
└── resources/
    ├── features/           # .feature files
    ├── config.properties   # Browser/config settings
    └── testng.xml   # TestNG configuration
```
## Prerequisites
- Java 17 or higher
- Maven 3.6.3 or higher
- IDE (IntelliJ/Eclipse)
- Chrome/Firefox (for headed mode)
## Installation
1. Clone repository:
```bash
git clone https://github.com/tariful-islam98/playwright_java_saucedemo.git
```
2. Install dependencies:
```bash
mvn clean install
```
3. Install browsers:
```bash
# For Chromium/Chrome
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args=\install chromium\
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args=\install chrome\
# For Firefox
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args=\install firefox\
```
## Configuration
Edit `src/test/resources/config.properties`:
```properties
# Supported browsers: chromium, firefox, webkit, chrome
browser.name=chromium
# Execution mode
headless=false
# Application URL
base.url=https://www.saucedemo.com
# Browser dimensions
viewport.width=1920
viewport.height=1080
# Timeouts (milliseconds)
default.timeout=10000
```
## Running Tests
### Command Line
```bash
# Run all tests
mvn test
# Run specific browser
mvn test -Dbrowser.name=firefox
# Run specific tag
mvn test -Dcucumber.filter.tags=\@smoke\
# Headless mode
mvn test -Dheadless=true
```
### IDE Execution
1. Right-click feature file/scenario
2. Select \Run as TestNG Test\
## Reports
**Report Location:**
```
test-output/
├── ExtentReport_yyyy-MM-dd_HH-mm-ss.html  # Timestamped report
└── screenshots/             # Failure screenshots
```
**Report Features:**
- Interactive dashboard
- Scenario-level screenshots
- Browser/environment details
- Historical execution tracking
## Best Practices
1. **Page Object Model**
```java
public class LoginPage {
    private final Page page;
    
    public LoginPage(Page page) {
        this.page = page;
    }
}
```
2. **Tag Organization**
```feature
@regression @login
Scenario: Failed login attempt
    Given ...
```
3. **Parallel Execution**
```java
// In TestRunner.java
@DataProvider(parallel = true)
public Object[][] scenarios() {
    return super.scenarios();
}
```
## Troubleshooting
| Issue | Solution |
|-------|----------|
| Browser not installed | Run appropriate `mvn exec:java` install command |
| Screenshots missing | Check `test-output/screenshots` directory permissions |
| Config changes not reflected | Clean project with `mvn clean` |
| Viewport issues | Verify values in config.properties |
## FAQ
**Q: How to add new browsers?**  
A: Update `browser.name` from `config.properties`

**Q: Can I run in headless mode?**  
A: Yes, set `headless=true` in config or command line

**Q: How to customize reports?**  
A: Modify `ExtentReportManager.java`

**Q: Parallel execution?**  
A: Enable in TestRunner.java and use `mvn test -DthreadCount=3`
