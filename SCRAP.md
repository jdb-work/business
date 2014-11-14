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
class CustomerOps {

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