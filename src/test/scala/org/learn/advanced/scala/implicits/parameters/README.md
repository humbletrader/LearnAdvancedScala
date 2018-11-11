# Where does scala look for implicits ? (Implicit resolution)

The Scala Language Specification declares two rules for looking up entities marked as implicit:                  
 * The implicit entity binding is available at the lookup site with no prefix—that is, not as foo.x but only x.                           
 * If there are no available entities from this rule, then all implicit members on objects belong to the implicit scope of an implicit parameter’s type.
 
The implicit scope of a type T is the set of companion objects for all types associated with the type T—that is, there’s a set of types that are associated with T. 
All of the companion objects for these types are searched during implicit resolution. 
The Scala Language Specification defines association as any class that’s a base class of some part of type T. 
The parts of type T are:                        
 * The subtypes of T are all parts of T. If type T is defined as A with B with C, then A, B, and C are all parts of the type T and their companion objects will be searched during implicit resolution for type T.                           
 * If T is parameterized, then all type parameters and their parts are included in the parts of type T. For example, an implicit search for the type List[String] would look in List’s companion object and String’s companion object.                           
 * If T is a singleton type T, then the parts of the type p are included in the parts of type T. This means that if the type T lives inside an object, then the object itself is inspected for implicits. 
 * If T is a type projection S#T, then the parts of S are included in the parts of type T. This means that if type T lives in a class or trait, then the class or trait’s companion objects are inspected for implicits.                  