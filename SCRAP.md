SCRAP.md
========

```groovy
def rex = "(?=<address>)|(?<=</address>)"
def el = "city"
```

```groovy
def "Address"() {}
def "Amount"() {}
def "GetProperty"() {}
def "SetProperty"() {}
def "InvokeMethod"() {}
def "GetMetaClass"() {}
def "SetMetaClass"() {}
```
```groovy
package bssv

final class Address {
    def street
}

@Category(String)
class bssv.ops.CustomerOps {

    static String address(String self) {
        new Address(street: self)
    }

    static String id(String self) {
        String id = self ?: "250780"
    }

}
```

```groovy
@Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.0')
```

```groovy
package bssv
//@Grab(group='xpp3', module='xpp3_min', version='1.1.4c')
//@Grab(group='com.thoughtworks.xstream', module='xstream', version='1.3')
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute

@XStreamAlias("address")
class CustomerSpace {
    @XStreamAsAttribute
    @XStreamAlias('city')
    def city
}
final def msg = new CustomerSpace(city:'Savannah')

def stream = new XStream()
def obj
new File("john.xml").withOutputStream { out ->
    stream.toXML(msg, out)
}
/*
new File("john.xml").withInputStream { ins ->
    obj = stream.fromXML(ins)
}
*/
println obj
```