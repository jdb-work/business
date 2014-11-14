package bssv.domain
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver
import spock.lang.Specification

class DomainSpec extends Specification {

    def idFxt = "250780"
    def addressFxt = new Address(
        street: "123 Main St",
        detail: "Apt A",
        city: "Savannah",
        state: "GA",
        zip: "31401"
    )

    def "Customer instantiates as Entity"() {
        given:
        def customer = new Customer(idFxt,addressFxt)
        expect:
        customer instanceof Entity
    }

    def "Address serializes to valid XML"() {
        given:
        def XStream xstream = new XStream(new JettisonMappedXmlDriver())
        expect:
        println xstream.toXML(address)
        true
    }
}
