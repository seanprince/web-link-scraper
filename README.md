# web-link-scraper
Simple REST API to locate hyperlinks in a single supplied URL.

The API is defined and documented in Swagger - see https://app.swaggerhub.com/apis/seanprince/Web-Link-Scraper/v1

The Jsoup HTML parser (see https://jsoup.org/) was used to speed the implementation. Jsoup was chosen as it is widely used (1007 usages) and actively being developed recently (last release Nov 17).

Assumptions about the requirement:
* Use of the verb 'locate' in the task specification implies information about where the link resides in the HTML document should also be returned by the API. I've chosen to return the CSS selector for the link to meet this requirement.

To build and run tests:
1. In IntelliJ:
    * Open the project at \<src checkout root\>/web-link-scraper
    * Open the Maven Projects windows by clicking View | Tool Windows | Maven Projects
    * Double-click web-link-scraper \> Lifecycle \> install
    
    Or, from a command prompt:
    * Go to folder \<src checkout root\>/web-link-scraper
    * Run command **mvn install**

To run the REST API:
1. Download and install Tomcat 8 (see https://tomcat.apache.org/tomcat-8.0-doc/setup.html)
2. In IntelliJ:
    * Edit the provided wls-rest-service Run Configuration by selecting Run | Edit Configurations...
    * On the Server tab, click Configure next to Application server
    * Ensure that the Tomcat home folder is set to the folder where you've installed Tomcat
    * Start Tomcat by clicking Run | Run wls-rest-service. The endpoint will be http://localhost:8086/wls-rest/v1/links 
	
If this was a 'real' development task, I would additionally: 
* Write automated acceptance testing of the REST API, eg using POSTMan scripts
* Enhance the scraper to be kind to the sites it was scraping by:
    * Caching previously scraped sites within a configurable period
    * Add a configurable time-out to cope with long document download times 
	
Possible enhancements (that go beyond the scope of the requirement):
* Add 'deep' link scraping to a configurable level
* Paging and limiting results
* Sorting results
