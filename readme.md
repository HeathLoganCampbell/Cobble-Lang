# Cobble Lang
A small self-made scripting language made for fun trying to use Extreme Programming practices

## Inspirations

### Java
* I love java's type system because it makes reasoning about a program easier
* I love java's classes (minus a bit of boilerplate)
* I love how maven makes you write tests in the project you write the code for.
* I love how java's IDEs auto import files when you code
* I love how java's IDEs do auto complete
* I love how java's IDEs have shortcuts .var .for 
* I like annotation based event systems
* I don't like how magic annotations are sometimes
* I like Lombok Getters & Setters even tho elegant objects are really against them
* I like how java with maven does module management
* I like exception bubbling
* I like exposing exceptions in the method signature 
* I don't like the flow of exceptions tho with try catch
* I love brackets to declare scope
* I'm not too big of a fan of inheritance
* I do like interfaces, but I think they are too busy at times and too much work prefer them auto generated
* I like how java does enums, but it's probably better to do some kind of value objects layer?

### Javascript
* I love how it runs anywhere
* I'm not a fan of passing functions into functions
* I love npm for quickly installing packages
* I like how it's single threaded a lot of the time
* I like how it can be run on lamba functions online

### Lua
* I love how it maps to c code so easily
* I like how few keywords it has
* I don't like how it does event drive code

### Python
* I do like how clean code can look without brackets on if statements and using depth of tabs instead

### Other
* I like the principle of immutability in functions
* I like the idea of domain driven design
* I like the idea of test driven development (starting with behaviours)
* Nomads are cool
* Nulls suck

## Keywords
* var
* func
* return
* if 
* module
* type

## Concepts
* Constructors are Uppercase for the first letter
* methods are camel case

## Example code

``` 
// App.cbbl
import Console
import Users

func run() 
{
    var name = "Heath"
    Console.writeLine("Welcome " + name + " you are a " + getRole(name))
    var randomUser = Users.getUser("randomUser")
}

func getRole(string username) -> string
{
    if username.equalsIgnoreCase("HeathLoganCampbell")
    {
        return "Admin";
    }
    else 
    {
        return "Memeber"
    }
}

// Report.cbbl
module Report
{
    
}

// Users.cbbl
import Database
import Console
import Events

module Users
{
    value Color {
        red: int,
        green: int,
        blue: int
    }

    type User {
        name: str,
        age: int,
    }

    func onLoad()
    {
    }
    
    handler func onDelete(UserDeleteEvent e)
    {
        Console.writeLine("Deleted User");
    } 
    
    expose func getUser(str username) -> User
    {
        // Database.Query("SELECT LIMIT 1 * FROM Users")
        return User(username, 2);
    }
    
    func onUnload()
    {
        
    }
}
```