package bssv

import spock.lang.Specification
import sun.invoke.empty.Empty

class bssvSpec extends Specification {

    def 'address.city == "Paris" for customer: 250780'() {
        expect:

        use (CustomerOps) {
            assert address == Empty as Map<String, String>
            assert amount  == Empty as Map<String, String>
            assert id instanceof String
        }
    }
}

@Category(String)
class CustomerOps {
    def id() { "250780" }
    //def rex = "(?=<address>)|(?<=</address>)"
    //def el = "city"
    def address() { Empty as Map<String,String> }
    def amount() { Empty as Map<String,String> }
}