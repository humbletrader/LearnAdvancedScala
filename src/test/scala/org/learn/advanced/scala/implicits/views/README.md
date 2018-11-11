Implicit views are used in two situations: 
 * If an expression doesn’t meet the type expected by the compiler, the compiler will look for an implicit view that would make it meet the expected type. 
 An example of this would be passing a value of type Int to a function that expects a String would require an implicit view of String => Int in scope.                           
 * Given a selection e.t, where selection means a member access, such that e’s type doesn’t have a member t, the compiler will look for an implicit view that will apply to e and whose resulting type contains a member t. 
 If we try to call method foo on a String, then the compiler will look for an implicit view from String that can be used to make the expression compile. 
 The expression "foo".foo() would require an implicit view like the following: 
 ```scala 
 implicit def stringToFoo(x : String) = new { 
    def foo() : Unit = println("foo") 
 }```