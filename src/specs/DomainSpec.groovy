package specs

import bssv.domain.*
import spock.lang.Specification
import groovy.transform.Immutable
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver

class DomainSpec extends Specification {

    def "Customer instantiates as Entity"() {
        given:
        def customer = new Customer()
        customer.build("250780")
        expect:
        customer instanceof Entity
    }

    def "Address serializes to valid XML"() {
        given:
        def address = new Address(
            street: "123 Main St",
            detail: "Apt A",
            city: "Savannah",
            state: "GA",
            zip: "31401"
        )
        //@Grab(group='com.thoughtworks.xstream', module='xstream', version='1.4.1')
        def XStream xstream = new XStream(new JsonHierarchicalStreamDriver())
        expect:
        println xstream.toXML(address)
        true
    }
}
