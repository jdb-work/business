package bssv.ops
/*
//SAX html scanning/parsing with cyberneko
import org.cyberneko.html.parsers.SAXParser
def href = new XmlParser(new SAXParser()).parse("https://www.heroku.com/")
href.depthFirst().A['@href'].findAll{ if(it.endsWith(".com")) println it }
*/
/*
//Predicate example
abstract class Greeter {
    abstract String getName()
    void greet() {
        println "Hello, $name"
    }
}
interface Predicate<T> { boolean accept(T obj) }
boolean doFilter(String s) { s.contains('G') }
Predicate filter = this.&doFilter
assert filter.accept('Groovy') == true
Greeter greeter = GroovySystem.&getVersion
greeter.greet()
*/

assert "GetCustomer" instanceof GString

