# View Bounds
Requires and implicit view for converting one type into another
```scala
    def fnc[A <% B](x: A) = ???
```
which is equivalent to
```scala
    def fnc[A](x: A)(implicit ev: A => B) = ???
```


# Context Bounds
Requires and implicit value available with a given type
```scala
    def fnc[A: B](x: A) = ???
```
translation for the code above: there must be an implicit value of type B[A] available. 
The long version for the function is
```scala
    def fnc[A](x: A)(implicit ev: B[A]) = ???
```