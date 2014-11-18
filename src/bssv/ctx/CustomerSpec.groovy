package bssv.ctx

import bssv.meta.Ctx
import spock.lang.Specification

class CustomerSpec extends Specification {

    def ""() {
        given:
        def entity = new Ctx(id:"111")
        def email
        when:
        use(CustomerCategory) {
            email = entity.mail()
        }
        then:
        email == 'mail'
    }


}