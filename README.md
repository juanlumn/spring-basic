# Spring Basics

To understand this example we need to answer some questions:

* What are the beans?

Beans are all the objects managed by Spring.<br />
The way you tell Spring that a class is a Bean is by adding the annotation `@Component` at the top of the class.<br />
The most common component annotations are:
1. `@Component`: Generic Component.
2. `@Repository`: Encapsulating storage, retrieval and search behaviour typically from a relational DB.
3. `@Service`: Business Service Facade.
4. `@Controler`: Controller in MVC pattern.

* What are the dependencies of a Bean?

A Java class has a dependency on another class, if it uses an instance of this class. We call this a class dependency. We use the tag `@Autowired` to tell Spring that it's a dependency of the Bean.<br />
In our example `SortAlgorithm` is a dependency of `BinarySearchServiceImpl. 

* Where to search for Beans?

SpringBoot will automatically scan the packages and subpackages where the main application class is present.<br />
So there is no need to define the component scan by default.<br />
If you need Spring to search in a different package you can set it with the tag `@ComponentScan`:
```
@SpringBootApplication
@ComponentScan("the.name.of.the.package")
public class BasicApplication {

	public static void main(String[] args) {
		...
	}
}
```

* What is the Bean Scope?

By default, the Bean scope is Singleton. There are 4 types:
1. Singleton: One instance per Spring Context
2. Prototype: New Bean when requested for it.
3. Request: One Bean per HTTP request.
4. Session: One Bean per HTTP session.
To change the scope of a Bean you can set it by using the tag `@Scope`:
```
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BinarySearchServiceImpl implements BinarySearchService {

    @Autowired
    @Qualifier("quick")
    private final SortAlgorithm sortAlgorithm;
    ...
}
```

* What happens if there are more than one dependency candidate for being Autowired?

In the example we have `BubbleSortAlgorithm` and `QuickSortAlgorithm` which are annotated as `@Component` and both can be injected as dependency in `BinarySearchServiceImpl` as both implements `SortAlgorithm`.
<br />
<br />
We can face the error: 
```
***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of constructor in com.juanlumn.spring.basic.service.binarysearch.BinarySearchServiceImpl required a single bean, but 2 were found:
- bubbleSortAlgorithm: defined in file [/home/juan/IdeaProjects/basic/target/classes/com/juanlumn/spring/basic/service/sort/BubbleSortAlgorithm.class]
- quickSortAlgorithm: defined in file [/home/juan/IdeaProjects/basic/target/classes/com/juanlumn/spring/basic/service/sort/QuickSortAlgorithm.class]
```
To avoid this you can use the tag `@Primary` to give preference to one of the components among the others:
```
@Component
@Primary
public class BubbleSortAlgorithm implements SortAlgorithm {
     ...
}
```
Another option is to use `@Autowired` by name so Spring will look for a Component which matches the name provided, for example:
```
@Component
public class BinarySearchServiceImpl implements BinarySearchService {

    @Autowired
    private SortAlgorithm quickSortAlgorithm;
    ...
}
```
Keep in mind that the tag `@Primary` has more priority than Autowire by name. So if there is a Component tagged as `@Primary` and the name provided is different the `@Primary` will be loaded instead.<br />
We can also use the `@Qualifier` tag:
```
@Component
@Qualifier("quick")
public class QuickSortAlgorithm implements SortAlgorithm {
    ...
}

@Component
public class BinarySearchServiceImpl implements BinarySearchService {

    @Autowired
    @Qualifier("quick")
    private final SortAlgorithm sortAlgorithm;
    ...
}
```

* How are the dependencies injected?

There are 3 ways to inject dependencies:
1. Constructor Injection.
2. Setter Injection.
3. No Setter or Constructor Injection.

The Constructor injection is when we set the dependency in the constructor of the class:
```
@Component
public class BinarySearchServiceImpl implements BinarySearchService {

    @Autowired
    private SortAlgorithm sortAlgorithm;

    public BinarySearchServiceImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }
    ...
}
```
The Setter Injection is when we use a Setter in the class:
```
@Component
public class BinarySearchServiceImpl implements BinarySearchService {

    @Autowired
    private SortAlgorithm sortAlgorithm;

    public void setSortAlgorithm(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }
    ...
}
```
No Setter or Constructor Injection is when we just Autowire the dependencies:
```
@Component
public class BinarySearchServiceImpl implements BinarySearchService {

    @Autowired
    private SortAlgorithm sortAlgorithm;
    ...
}
```
In fact both Setter Injection and No Setter or Constructor are the same as Spring will generate the Setter if not set.