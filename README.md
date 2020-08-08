# LibCAT

LibCAT is a program that tracks resources in a library catalog and notifies patrons when resources are available to check out.

GitHub Repo: https://github.com/metcs/met-cs665-assignment-project-nikki-t

## Use Case Scenario

LibCAT provides a way to track the state of library resources and notify patrons when a resource is available for check out. This allows patrons to place holds on resources and they will be notified when the resource is ready for them to check out. LibCAT currently has movies and books available in its catalog, and both electronic and analog or physical versions of these resource types are available.

## Current Iteration

- LibCAT reads from CSV files to simulate the retrieval of data from a database. Database functionality can be easily added in the database package.
- LibCAT tracks when resources are available, checked out, or on hold. It does not track overdue resources at this time.

## Design Patterns (Class Design)

### Builder Pattern

The Builder Pattern was used to construct complex library objects that would normally require a heavily parameterized constructor with nested objects to create. The Builder Pattern enables the LibCAT application to create different representations of a resource including the creation of different resource types (e.g. movies and books) using the same set of steps or algorithm. It also allows the LibCAT application to control the creation of objects to ensure that the result is valid. This was also applied to the creation of Patron objects which differ in nature from Resource objects so use a different interface.

The Director ("Director") class contains references to each concrete builder class and uses these references to control the construction of objects.

The ResourceBuilder interface ("Builder") specifies the operations required to build a resource. Because the Resource class is abstract, there is are MovieBuilder and BookBuilder interfaces that extend the ResourceBuilder to specify what is needed to create Book and Movie resources.

The ConcreteMovieBuilder ("ConcreteBuilder") and ConcreteBookBuilder ("ConcreteBuilder") classes both implement the appropriate interfaces to create either a Movie resource ("ConcreteProduct") or a Book resource ("ConcreteProduct"). Both concrete builder classes return a Resource ("Product") instead of the specific resource type as this is what the LibraryCatalog will largely work with.

The Builder Pattern is used in a similar manner to create Patron objects. There is a PatronBuilder interface which specifies what is neccesary to create Patron objects. The ConcretePatronBuilder class produces Patron objects and the Patron class serves as the "Product" that is created.

See 'diagrams/LibCAT Class Diagram - Builder Pattern'.

### Bridge Pattern

A Resource object can currently take on of two formats: electonic or physical. So there can be electronic Books, physical Books, electonic Movies, and physical Movies. The Bridge Pattern was used to split what could potentially be a large class hierarchy into two different domains and favors aggregation to accomplish this.

The Format ("Implementor") interface specifies the operations that are needed to represent different format types.

The Electronic and Physical ("Concrete Implementors") classes implement the Format interface to provide the required attributes specific to the natures of each format.

The Resource ("Abstraction") class contains an attribute reference to a Format object and relies on the actual reference to contain the required attributes to represent a resource in either format type.

See 'diagrams/LibCAT Class Diagram - Bridge Pattern'.

### State Pattern

The State Pattern was used to track the different states of a Resource object. This allows each Resource object to alter its behavior when a state change occurs as each Resource state requires a different behavior. The State Pattern also allows each state to decide when it is appropriate to transition to a new state.

The Resource ("Context") class contains member attribute references to all concrete ResourceState classes. The Resource class uses these references when it calls the following methods: checkIn, checkOut, and placeHold. The referenced ResourceState object then takes care of the required behavior for each method.

The ResourceState ("State") interface class specifies the methods that each concrete state needs to implement. These methods are: checkIn, checkOut, getName, and placeHold.

The "Concrete State" classes are Available, CheckedOut, and OnHold. They all implement the ResourceState interface methods and provide an implementation that is specific to the state each represents. For example the Available state allows client code to place holds on resources and to check them out but does not let client code check resources in because they have already been checked in. Each state functions similarly for each method.

See 'diagrams/LibCAT Class Diagram - State Pattern'.

## Design Goals

The following is a list and brief description of design goals for this iteration of LibCAT.

### Sufficiency

The first iteration of LibCAT aims to function and fulfill the use case through the implementation of multiple design patterns. The use of design patterns ensures the correctness of inter-package communication and behavior while fulfilling the primary requirements of resource creation and resource state behaviors.

### Modularity

High cohesion within packages and low coupling between packages was a major design goal for this iteration of LibCAT. This often manifests in the dependencies between the design patterns that were implemented.

Examples of this include:
- The LibraryCatalog class' use of a Director object to build Resource objects.
- Client code's ability to use the LibraryCatalog class to populate the catalog, populate a list of patrons and find specific resources by identifier. This then allows the client code to work directly with the resource which provides methods to check resources in, out and place them on hold.
- The Resource class serves as the "facade" for the resource package so client code, the builder package, and the state package only have to interact with the Resource class to use the resource package.
- The Builder Pattern classes in the builder package are not dependent on the State Pattern classes in the state package. This allows most classes to be responsible for only one functionality and also leads to greater understandability.

### Flexibility

One can add to the functionality of LibCAT in the following ways:

Adding a Resource:
- A new resource class needs to be created that extends the Resource class. Care should be taken when thinking about the new resource's electronic and physical formats but the Format interface provides general enough functionality that this should rarely be an issue.
- A new Builder interface that extends the ResourceBuilder interface needs to be written that specifies the operations needed to build the new resource which should be few as ResourceBuilder contains most operations.
- A new ConcreteBuilder class needs to be created that implements the new Builder interface and creates the new resource.
- The Director class needs a method added to it so that it knows to create the new resource.
- The LibraryCatalog class' createResourceList method will need to be adjusted to know when to create the new resource.
This seems fairly complex (one of the consequences of the Builder Pattern) but it is quite clear how to add a new resource type to LibCAT and each step is not complex in itself.

Adding a Resource State:
- The next iteration should have a OverDue state that notifies patrons of the resource's status at a certain date. To add a new OverDue state, one would need to create the OverDue state class that extends the ResourceState interface and implement all of the specified methods.
- The complexity lies in the OverDue behavior as a new method will need to be added to the ResourceState interface which is usually a sign of bad design but this is a consequence of the State Pattern. This new method will then need to be implemented in each ResourceState subclass. This method will check the date and notify the Patron when a resource is overdue. The OnHold and CheckOut state can make use of this method but it's not really applicable to the Avialable state.
- The Resource class will also need to be modified to incldue the new state and method.
This is also complex but the use of both patterns provide a clear way to make these changes and the changes are not complex as isolated tasks which reduces the possibility of introducing bugs. By the nature of being a pattern, both implementations provide a clear way to make change when it is needed.

### Understandability

The goal of understandability is fulfilled through the use of design patterns and the modular application of design patterns.

The Builder Pattern makes it clear that valid resource objects are being created by the Director class. Each ConcreteBuilder subclass only has one responsibility and that is to create a valid resource subclass object. The Director class then uses the ConcreteBuilder classes to control the construction of each resource object. The LibraryCatalog class is then only responsible for retrieving records from a database and uses the Director class to create objects from these records.

The State Pattern makes it clear what happens when a Resource object is in a specific state and what actions are taken when a Patron tries to check out, check in or place a hold on a resource. The State Pattern keeps the states separate from the Resource class which is also used by the Builder class and therefore helps to reduce complexity. The Resource class does not have to worry about what behaviors need to occur during different state changes.

### Code Duplication

Code duplication is reduced in the following ways:

- Resource inheritance hierarchy: The Resource class is an abstract class that includes all common functionality for library resource objects. Code duplication is reduced in Resource subclasses as they only need to be considered with the details specific to the nature of the resource they are representing.
- Resource aggregation of Format (Bridge Pattern): Resource objects aggregate Format objects depending on whether they are electronic or physical resources. The Format interface specifies what each format type should represent. This reduces the need to subclass each Resource type for each format type.
- Build interface inheritance hierarchy: The ResourceBuilder interface provides a common specification for all ResourceBuilder subtypes. The MovieBuilder and BookBuilder interfaces then only need to specify the details needed to create books and movies.
- The above point does not reduce the code duplication present in the ConcreteMovieBuilder and ConcreteBookBuilder classes as they both must ensure they implement the ResourceBuilder interface and either the MovierBuilder or BookBuilder interfaces.
- Code duplication is also not reduced in the relationship between concrete builders and concrete resources but this is the nature of the Builder Pattern. The Builder Pattern must build each of these resources and so must be able to use each of the concrete resource methods to do so. The benefit of this duplication is that the Builder Pattern ensures concrete resource objects are built correctly and are valid resource objects. The Director class also assists in this effort.

## Thoughts & Questions

- There was some inconsistency in how different developers implemented the Builder Pattern when I completed my research on the pattern. I choose to have the concrete builder classes use the resource member attribute methods to "build" the resource and then return the resource with the getResult method. I also provided a reset method to reset the reference to the resource member attribute where some implementations of the getResult method jsut returned a new object. This however meant a heavily paramterized constructor which seemed to defeat the purpose and benefit of the Builder Pattern.
- I placed references in the Director class to each builder and concrete builder type. Some implementations accepted a builder object reference as a parameter to their construction methods. I decided this made it cleaner in the LibraryCatalog class as it does not have to have any knowledge of the builders or concrete builders to use the director's construction methods. I did however allow one to set the different concrete builders using set methods in the Director class.
- I decided to implement a getResult method for each concrete builder and have it return a "Resource". This seemed like a good practice at it is programming to an interface or abstraction. This would require casting at some point if one wanted to display the specifics of a Book or Movie object. This was the only place that I thought casting would be necessary and it did not seem to be too terrible of a decision so I decided to keep the functionality as is. Are there any other unintended consequences you canthink of?
- I put newline characters in the logging to make it clear what was printing to the console which helped to demo the program. Ideally the newlines and logging with cleaned up to only log key points of functionality.

# How to compile the project

We use Apache Maven to compile and run this project.

You need to install Apache Maven (https://maven.apache.org/)  on your system.

Type on the command line:

```bash
mvn clean compile
```

# How to create a binary runnable package


```bash
mvn clean compile assembly:single
```


# How to run

```bash
mvn -q clean compile exec:java -Dexec.executable="edu.bu.met.cs665.Main" -Dlog4j.configuration="file:log4j.properties"
```

We recommand the above command for running the project.

Alternativly, you can run the following command. It will generate a single jar file with all of the dependencies.

```bash
mvn clean compile assembly:single

java -Dlog4j.configuration=file:log4j.properties -classpath ./target/JavaProjectTemplate-1.0-SNAPSHOT-jar-with-dependencies.jar  edu.bu.met.cs665.Main
```


# Run all the unit test classes.


```bash
mvn clean compile test

```

# Using Findbugs

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn findbugs:gui
```

or


```bash
mvn findbugs:findbugs
```


For more info about FindBugs see

http://findbugs.sourceforge.net/

And about Maven Findbug plugin see
https://gleclaire.github.io/findbugs-maven-plugin/index.html


You can install Findbugs Eclipse Plugin

http://findbugs.sourceforge.net/manual/eclipse.html



SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.


# Run Checkstyle

CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style.
You can change it to other styles like sun checkstyle.

To analyze this example using CheckStyle run

```bash
mvn checkstyle:check
```

This will generate a report in XML format


```bash
target/checkstyle-checker.xml
target/checkstyle-result.xml
```

and the following command will generate a report in HTML format that you can open it using a Web browser.

```bash
mvn checkstyle:checkstyle
```

```bash
target/site/checkstyle.html
```


# Generate  coveralls:report

You can find more info about coveralls

https://coveralls.io/

```bash
mvn -DrepoToken=YOUR-REPO-TOCKEN-ON-COVERALLS  cobertura:cobertura coveralls:report
```
