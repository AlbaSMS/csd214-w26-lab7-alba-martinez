## **7. Part E: Reflection**

**1. The Hidden ID: In your magazine-form.html, you included `<input type="hidden" th:field="*{id}" />`. What would happen if you forgot this line, and a user tried to "Edit" an existing magazine? Why?**

If the hidden ID field is missing, the form will not send the magazine’s existing ID back to the server, so Spring Data JPA will not recognize it as an existing record. Because of that, when the form is submitted, it will treat the magazine as a new entity and insert a new row instead of updating the existing one, which results in duplicate records rather than proper edits.

**2. The PRG Pattern: Why does our save method return `redirect:/magazines` instead of just returning the "magazine-list" HTML template directly?**

The method returns `redirect:/magazines` to follow the Post/Redirect/Get pattern, which ensures that after submitting a form, the browser performs a new GET request instead of staying on the POST result page. This prevents duplicate submissions if the user refreshes the page and also ensures the list is reloaded from the controller with the latest database data.

**3. Data Binding: What is the purpose of the `@ModelAttribute` annotation in your `@PostMapping` method? What manual work does it save you from doing?**

The `@ModelAttribute` annotation automatically binds form input values to a Java object by matching field names from the HTML form to the entity properties. This saves you from manually extracting each request parameter using `request.getParameter()` and setting each field one by one, which reduces boilerplate code and possible errors.

**4. Fragments: You used `th:replace="~{fragments/navbar :: header}"` on multiple pages. What is the primary benefit of this approach in a real-world application with 50+ HTML pages?**

The main benefit is reusability and maintainability because the navbar is defined once and reused across all pages. In a large application, this means any change to the navigation only needs to be made in one file, and it will automatically update everywhere, ensuring consistency and reducing duplication.

---

## **Lab 7: 20 Multiple Choice Questions**

**1. What does "SSR" stand for in the context of our Spring Boot application?**

a) Server-Side Rendering

**2. Which Spring MVC annotation is used to mark a class that returns HTML template names instead of raw JSON data?**

c) @Controller

**3. In the URL [http://localhost:8080/magazines/edit/5](http://localhost:8080/magazines/edit/5), what annotation is used to extract the number 5 in the Java Controller?**

b) @PathVariable

**4. What is the primary role of Thymeleaf in our application?**

c) To stitch dynamic Java data into static HTML templates before sending it to the browser

**5. If a user submits an HTML `<form method="post">`, which annotation should the receiving method in the Controller use?**

c) @PostMapping

**6. What does the Thymeleaf attribute `th:each="mag : ${magazines}"` do?**

b) It loops over a list called "magazines" and generates HTML elements for each item

**7. Why do we need a specific MagazineRepository if we already have a ProductRepository?**

b) To allow Spring Data JPA to access subclass-specific fields like publisher that are not in the parent entity

**8. In Spring MVC, what is the "Model"?**

c) A container used to pass data from the Controller to the View

**9. What does the `redirect:/` prefix do when returned by a Controller method?**

c) It tells the browser to send a new GET request to the specified URL

**10. What HTTP request type is considered unsafe for saving sensitive data because the data is visible in the URL?**

b) GET

**11. What does the `@ModelAttribute("magazine") MagazineEntity mag` annotation do?**

b) It binds form fields automatically to a Java object

**12. If you call `magazineRepository.save(mag)` and the ID is null, what does Spring Data JPA do?**

c) It performs an INSERT to create a new record in the database

**13. If you call `magazineRepository.save(mag)` and the ID is 3, what does Spring Data JPA do?**

c) It performs an UPDATE on the existing record with ID 3

**14. What does `th:replace="~{fragments/navbar :: header}"` accomplish?**

b) It replaces the current element with a reusable fragment

**15. What is the central "Front Controller" in Spring MVC?**

a) DispatcherServlet

**16. Which of the following is an example of a "Natural Template"?**

c) A Thymeleaf HTML file that can be opened directly in a browser

**17. What is the default port that Spring Boot uses?**

c) 8080

**18. In Thymeleaf, what syntax is used for dynamic URLs?**

b) @{/path}

**19. Why do we avoid an infinite loop in CommandLineRunner in web applications?**

b) It blocks the main thread and prevents the web server from starting

**20. What is the purpose of the Post/Redirect/Get (PRG) pattern?**

c) It prevents duplicate form submissions when refreshing or navigating back in the browser
