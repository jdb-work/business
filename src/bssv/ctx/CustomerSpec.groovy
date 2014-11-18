package bssv.ctx

import bssv.meta.Ctx
import bssv.ops.ServiceInvoker
import spock.lang.Specification

class CustomerSpec extends Specification {

    def "customer field access via category"() {
        given:
        def meta = new Customer(id:"100") as Ctx
        meta.@email = "meta@customer.cat"
        def email = ""
        when:
        use(CustomerCategory) {
            email = meta.mail()
        }
        then:
        email == "meta@customer.cat"
    }

    def "inherited field access via category"() {
        given:
        def meta = new Ctx(id:"101")
        def _id = ""
        when:
        use(CustomerCategory) {
            _id = meta.@id
        }
        then:
        _id == "101"
    }

    def "service invoked by customer"() {
        given:
        def customer = new Customer(id:"206780")
        when:
        customer.call()
        println "tester: ${customer.req}"
        then:
        customer.resp != null
    }

}